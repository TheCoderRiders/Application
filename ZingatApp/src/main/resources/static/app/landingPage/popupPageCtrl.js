angular.module('PopUpPageController', ['ngDialog'])
  .controller("popupPageCtrl",["$scope","$location","$http","ngDialog", function($scope,$location,$http,ngDialog){
      
      $scope.selectUser = {};

      $http({
          url: 'worklistPage/getUsersForAllocation?actionId=111', 
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
          var checkedFile = JSON.parse(localStorage.getItem("checkFiled"));
          $( "#tags" ).autocomplete({
              source: arrUserList,
              select : function( event, ui ) {
                $scope.requestedObject = {};
                $scope.requestedObject.assignedUserId = ui.item.id
                $scope.requestedObject.assignedUserName = ui.item.value;
                $scope.requestedObject.fileId = checkedFile.checkFiles[0].fileId;
                $scope.requestedObject.actionId = $("#mySelect option:selected").attr('value');
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
           console.log(data);
        },function(err) {
            console.log("Bucket Err: "+err);
        });
      }
  }
]);