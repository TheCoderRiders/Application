angular.module('PopUpPageController', ['ngDialog'])
  .controller("popupPageCtrl",["$scope","$location","$http","ngDialog","$route","$cookies", function($scope,$location,$http,ngDialog,$route,$cookies){
      
      $scope.selectUser = {};
      var checkedFile = JSON.parse($cookies.get("checkFiled"));
      var actionId = checkedFile.assignee.id;
      $http({
          url: 'worklistPage/getUsersForAllocation?actionId='+actionId, 
          method: "GET",
          headers: { 
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          }
      }).then(function(data){ //make a get request to mock json file.
          var arrUserList = [];
          $.each(data.data, function(index, user) {
              var prod = {};
              prod['value'] = user.username;
              prod['id'] = user.userId;
              prod['label'] = user.username;
              arrUserList.push(prod);
          });
          
          $( "#tags" ).autocomplete({
              source: arrUserList,
              select : function( event, ui ) {
                $scope.requestedObject = {};
                $scope.requestedObject.assignedUserId = ui.item.id
                $scope.requestedObject.assignedUserName = ui.item.value;
                var temp = [];
                for(var i=0; i<checkedFile.checkFiles.length; i++){
                  temp.push(checkedFile.checkFiles[i].fileId);
                }
                $scope.requestedObject.fileId = temp;
                $scope.requestedObject.actionId = actionId;
              },
          });

          $("#tags").on( "autocompletechange", function( event, ui ){ });
      },function(err) {
          console.log("Bucket Err: "+err);
      });

      $scope.assignFile = function(requestedObject){
        $http({
            url: 'worklistPage/assignedTo', 
            method: "POST",
            data : JSON.stringify(requestedObject),
            headers: { 
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data){ //make a get request to mock json file.
           ngDialog.close();
           $route.reload();
           console.log(data);
        },function(err) {
            console.log("Bucket Err: "+err);
        });
      }
  }
]);