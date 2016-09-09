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
            var selectedCode = {};
            selectedCode.sectionName = section.sectionName;
            selectedCode.codes = [];
            delete code.$$hashKey;
            selectedCode.codes.push(code);

            
            if($(event.target).parent().parent().parent().parent().children('div').length == 1){
                $(event.target).parent().parent().parent().parent().remove();
            }
            $(event.target).parent().parent().parent().remove();
            if($(event.target).attr('id') == "on"){
                var temp = $scope.acceptedCode;
                var codeLength;
                var temp2 = [];
                var dummyElement;
                var i = 0;
                if(temp){
                    codeLength = temp.length;
                    for(i=0; i<codeLength; i++){
                        if(temp[i].sectionName == selectedCode.sectionName){
                            dummyElement = selectedCode;
                            break;
                        }
                    }
                    /*to check for existing element*/
                    if(i == codeLength){
                        temp.push(selectedCode);
                    }else{
                        temp[i].codes.push(selectedCode.codes[0]);
                    }
                }else{
                    codeLength = 1;
                    temp = [];
                    temp.push(selectedCode);
                }
                $scope.acceptedCode = temp;
            }else{
                var temp = $scope.rejectedCode;
                var codeLength;
                var temp2 = [];
                var dummyElement;
                var i = 0;
                if(temp){
                    codeLength = temp.length;
                    for(i=0; i<codeLength; i++){
                        if(temp[i].sectionName == selectedCode.sectionName){
                            dummyElement = selectedCode;
                            break;
                        }
                    }
                    /*to check for existing element*/
                    if(i == codeLength){
                        temp.push(selectedCode);
                    }else{
                        temp[i].codes.push(selectedCode.codes[0]);
                    }
                }else{
                    codeLength = 1;
                    temp = [];
                    temp.push(selectedCode);
                }
                $scope.rejectedCode = temp;
            }
        }
    }]);