angular.module('RejectDocPageController', ['ngDialog'])
    .controller("rejectDocPageCtrl",["$scope","$location","$http","ngDialog", function($scope,$location,$http,ngDialog){
        
        $http({
          url: 'workingPage/getDocRejectionReasonList', 
          method: "GET",
          headers: { 
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          }
        }).then(function(data){ //make a get request to mock json file.
            $scope.rejectionDocList = data.data;
            console.log($scope.rejectionDocList);
        },function(err) {
            console.log("Bucket Err: "+err);
        });

        $scope.closePopup = function(){
          ngDialog.close();
        }

        $scope.rejectCode = function(){
            console.log("called");
        }

      }
]);