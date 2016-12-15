angular.module('DownloadPageController', ['ngDialog'])
    .controller("downloadPageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        $scope.downloadOption = false
        $scope.downLoadOption = "PDF";
        /* click on close icon of popup */
        $scope.closePopup = function() {
            ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.downloadOptionChecked = function(option){
            $scope.downLoadOption = option;
        }

        /* click on Submit of Download*/
        $scope.downloadFile = function() {
            var getObj = workingPageService.getRequestParameter().obj[0];
            var downloadObj = {};
            downloadObj.fileId = getObj.fileId;
            downloadObj.fileType = $scope.downLoadOption;
            window.open('/downloadPdf?fileId='+downloadObj.fileId+'&fileType='+downloadObj.fileType);
            ngDialog.close();
        }

    }]);