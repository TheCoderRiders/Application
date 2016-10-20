angular.module('RejectCodePageController', ['ngDialog'])
    .controller("rejectCodePageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        /* function to fetch code rejection list */
        $http({
            url: 'workingPage/getCodeRejectionReasonList',
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data) { 
            $scope.rejectionCodeList = data.data;
        }, function(err) {
            console.log("Bucket Err: " + err);
        });

        /* click on close icon of popup */
        $scope.closePopup = function() {
            ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.rejectCodeChecked = function(option){
            $(".rejectCodeComment").val(option.rejectionReasonDesc);          
        }

        /* click on Submit of code rejection*/
        $scope.rejectCode = function() {
            var checkedRadio = JSON.parse($(".rejectedCode.ng-touched").attr('ng-value'));
            var targetHeading = workingPageService.getRequestParameter().obj[1];
            var requestData = workingPageService.getRequestParameter().obj[0];
            requestData.code.rejectionReasonListId = checkedRadio.rejectionReasonListId;
            requestData.code.rejectionReasonDisplay = checkedRadio.rejectionReasonDisplay;
            requestData.code.rejectionReasonDesc = $(".rejectCodeComment").val();

            workingPageService.updateGetCodes(requestData,targetHeading,function(data){
                data.data.targetHeading = targetHeading;
                $scope.$emit("codeActionEmit",data.data);
            },function(){

            });
        }

    }]);