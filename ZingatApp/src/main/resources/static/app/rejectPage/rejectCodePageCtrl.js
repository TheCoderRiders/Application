angular.module('RejectCodePageController', ['ngDialog'])
    .controller("rejectCodePageCtrl",["$scope","$location","$http","ngDialog", function($scope,$location,$http,ngDialog){
        
        $http({
          url: 'workingPage/getCodeRejectionReasonList', 
          method: "GET",
          headers: { 
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          }
        }).then(function(data){ //make a get request to mock json file.
            $scope.rejectionCodeList = data.data;
            console.log($scope.rejectionCodeList);
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