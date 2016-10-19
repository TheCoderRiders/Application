angular.module('RejectCodePageController', ['ngDialog'])
    .controller("rejectCodePageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        $http({
            url: 'workingPage/getCodeRejectionReasonList',
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data) { 
            $scope.rejectionCodeList = data.data;
            console.log($scope.rejectionCodeList);
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
                debugger;
                $scope.globalObj = data.data;
                $scope.suggestedCode = data.data.suggestedCode;
                $scope.acceptedCode = data.data.acceptedCode;
                $scope.rejectedCode = data.data.rejectedCode;
                $scope.mayBeCode = data.data.mayBeCode;
                if(targetHeading == "Suggested" && $scope.suggestedCode.length < 1){
                    $scope.emptyData = true;
                }else if(targetHeading == "Accepted" && $scope.acceptedCode.length < 1){
                    $scope.emptyData = true;
                }else if(targetHeading == "Rejected" && $scope.rejectedCode.length < 1){
                    $scope.emptyData = true;
                }else if(targetHeading == "MayBe" && $scope.mayBeCode.length < 1){
                    $scope.emptyData = true;
                }
                $scope.acceptCode = false;
                $scope.rejectCode = false;
                ngDialog.close();
            },function(){

            });
        }

    }]);