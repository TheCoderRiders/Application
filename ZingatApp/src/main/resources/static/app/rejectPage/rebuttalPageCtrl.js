angular.module('RebuttalPageController', ['ngDialog'])
    .controller("rebuttalPageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        /* function to fetch predefined doubt list */
        $http({
            url: 'workingPage/getRebuttalList',
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
            $(".rebuttalComment").val(option.rebuttalDesc);          
        }

        /* click on Submit of doubt*/
        $scope.submitDoubt = function() {
 
            var getObj = workingPageService.getRequestParameter().obj[0];
            if($(".rebuttalItem.ng-touched").attr('id') && $(".rebuttalComment").val().length > 0){
                var requestObj = {};
                requestObj.fileId = getObj.fileId;
                requestObj.status = "REBUTTAL";
                requestObj.doubtRebuttalInfo = {}
                requestObj.doubtRebuttalInfo.doubtRebuttalType = "REBUTTAL";
                requestObj.doubtRebuttalInfo.doubtRebuttalId = JSON.parse($(".rebuttalItem.ng-touched").attr('id'));
                requestObj.doubtRebuttalInfo.doubtRebuttalDisplay = $(".rebuttalItem.ng-touched").attr('title');
                requestObj.doubtRebuttalInfo.doubtRebuttalDesc = $(".rebuttalComment").val();
                requestObj.fileContents = $(".leftSideContent .actualFileText")[0].innerHTML;
                $(".doubtError").hide();
                $http({
                    url: 'workingPage/documentStatusChange', 
                    method: "POST",
                    data : JSON.stringify(requestObj)
                }).then(function(data){ 
                    ngDialog.close();
                    $(".doubtError").hide();
                },function(err) {
                    console.log(err);
                });
            }else{
                $(".doubtError").show();
            }

        }

    }]);