angular.module('DownloadPageController', ['ngDialog'])
    .controller("downloadPageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        $scope.downloadOption = false
        /* click on close icon of popup */
        $scope.closePopup = function() {
            ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.downloadOptionChecked = function(option){
                
        }

        /* click on Submit of Download*/
        $scope.downloadFile = function() {
            var getObj = workingPageService.getRequestParameter().obj[0];
            var downloadObj = {};
            downloadObj.fileId = getObj.fileId;
            window.open('/downloadPdf?fileId='+downloadObj.fileId)
            ngDialog.close();

            /*$http({
                url: '/downloadPdf?fileId='+downloadObj.fileId,
                method: "GET",
                data : JSON.stringify(downloadObj),
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/pdf'
                }
            }).then(function(data) { 

            }, function(err) {
                
            });*/

        }

    }]);