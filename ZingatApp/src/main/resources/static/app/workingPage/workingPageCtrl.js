angular.module('WorkingPageController', [])
    .controller("workingPageCtrl",["$scope","$location","$http", function($scope,$location,$http){

        $scope.fileId = localStorage.getItem("clickedFileId");

        $http({
            url: 'worklistPage/getFileContents?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.workingFileContent = data.data.data.replace(/\n/g,"<br>");
        },function(err) {
            console.log(err);
        });

        $http({
            url: 'workingPage/getCodes?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.suggestedCode = data.data.suggestedCode;
            $scope.acceptedCode = data.data.acceptedCode;
            $scope.rejectedCode = data.data.rejectedCode;
        },function(err) {
            console.log(err);
        });

        $scope.codeStatus = function(code,section,actionName){
            console.log(actionName);
            console.log(code);
            console.log(section);
            var temp = $scope.acceptedCode;
            

        }
        
    }]);