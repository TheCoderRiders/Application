angular.module('DoubtPageController', ['ngDialog'])
    .controller("doubtPageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        /* function to fetch predefined doubt list */
        $http({
            url: 'workingPage/getDocRejectionReasonList',
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data) { 
            $scope.doubtList = data.data;
        }, function(err) {
            console.log("Bucket Err: " + err);
        });

        /* click on close icon of popup */
        $scope.closePopup = function() {
            ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.doubtChecked = function(option){
            $(".rejectCodeComment").val(option.rejectionReasonDesc);          
        }

        /* click on Submit of doubt*/
        $scope.submitDoubt = function() {
            
        }

    }]);