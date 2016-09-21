angular.module('WorkingPageController', ['ngSanitize'])
    .controller("workingPageCtrl",["$scope","$location","$http", function($scope,$location,$http){

        $scope.fileId = localStorage.getItem("clickedFileId");
        $scope.globalObj;

        

        $http({
            url: 'worklistPage/getFileContents?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.workingFileContent = data.data.data.replace(/class/g, 'value');
            var mark = document.getElementsByTagName('mark');
            for(var i=0; i<mark.length; i++){
                mark[i].addEventListener("click", function(ev){
                    $scope.clickedCode($(ev.target).attr('value'));
                });
            }
        },function(err) {
            console.log(err);
        });

        $http({
            url: 'workingPage/getCodes?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.globalObj = data.data;
            $scope.suggestedCode = data.data.suggestedCode;
            $scope.acceptedCode = data.data.acceptedCode;
            $scope.rejectedCode = data.data.rejectedCode;
            $scope.mayBeCode = data.data.mayBeCode;
        },function(err) {
            console.log(err);
        });

        $scope.clickedCode = function(codeId){

            alert(codeId);
        }
        $scope.clickedSuggestedCode = function(){
            var code = $(event.currentTarget).text();
            event.preventDefault();
            //$(".leftSideContent").find('mark').attr('value',code);
            var top = $(".leftSideContent").find('mark').attr('value',code).offset().top;
            debugger;
            $(".leftSideContent").animate({ 
                scrollTop: $(".leftSideContent").find('mark').attr('value',code).offset().top 
            }, 1000);
        }

        $scope.codeStatus = function(code,section,actionName){
            var selectedCode = {};
            selectedCode.sectionName = section.sectionName;
            selectedCode.codes = [];
            delete code.$$hashKey;
            selectedCode.codes.push(code);
            
            var action,codeActionType;
            
            if($(event.target).attr('id') == "on"){
                action = "Accept";
                codeActionType = $(".nav").find('li.active').attr('heading');
            }else{
                action = "Reject";
                codeActionType = $(".nav").find('li.active').attr('heading');
            }
            var requestedData = { };
            requestedData.allCodes = $scope.globalObj;
            requestedData.sectionName = selectedCode.sectionName;
            requestedData.action = action;
            requestedData.code = code;
            requestedData.codeActionType = codeActionType;
            
            $http({
                url: 'workingPage/codeAction', 
                method: "POST",
                dataType : "application/json",
                data : JSON.stringify(requestedData)
            }).then(function(data){ //make a get request to mock json file.
                $scope.globalObj = data.data;
                $scope.suggestedCode = data.data.suggestedCode;
                $scope.acceptedCode = data.data.acceptedCode;
                $scope.rejectedCode = data.data.rejectedCode;
                $scope.mayBeCode = data.data.mayBeCode;
            },function(err) {
                console.log("error while code  action");
            });
        }
    }]);