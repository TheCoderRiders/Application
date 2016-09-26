angular.module('WorkingPageController', ['ngSanitize','ngScrollbar'])
    .controller("workingPageCtrl",["$scope","$location","$http", function($scope,$location,$http){

        $scope.fileId = localStorage.getItem("clickedFileId");
        $scope.globalObj;
        $scope.acceptCode = false;
        $scope.rejectCode = false;
        $scope.totalItems;
        $scope.maxSize = 5;
        $scope.currentPage = 1;
        $scope.perPageCount = 20;

        $http({
            url: 'worklistPage/getFileContents?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.workingFileContent = data.data.data;
            var mark = document.getElementsByTagName('mark');
            for(var i=0; i<mark.length; i++){
                mark[i].addEventListener("click", function(ev){
                    $scope.clickedCode($(ev.target).attr('class'));
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
            var elementTop = $(".rightSideContent span[class*='"+codeId+"']").offset().top;
            var divTop = $('.rightSideContent').offset().top;
            var elementRelativeTop = elementTop - divTop;
            
            $(".rightSideContent span[class*='"+codeId+"']").addClass('highlighted');

            setTimeout(function(){
              $(".rightSideContent span[class*='"+codeId+"']").removeClass('highlighted');
            }, 3000); 

            $(".rightSideContent").animate({ 
                scrollTop: elementRelativeTop
            }, 1500);
        }
        $scope.clickedSuggestedCode = function(){
            var code = $(event.currentTarget).text();
            event.preventDefault();
            var elementTop = $(".leftSideContent mark[class*='"+code+"']").offset().top;
            var divTop = $('.leftSideContent').offset().top;
            var elementRelativeTop = elementTop - divTop;

            $(".leftSideContent mark[class*='"+code+"']").addClass('highlighted');

            setTimeout(function(){
              $(".leftSideContent mark[class*='"+code+"']").removeClass('highlighted');
            }, 3000);    

            $(".leftSideContent").animate({ 
                scrollTop: elementRelativeTop
            }, 1500);
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

        $scope.searchCode = function(searchTerm){
            $http({
                url: 'workingPage/searchCode?key='+searchTerm+'&start='+$scope.currentPage, 
                method: "GET",
            }).then(function(data){ 
                $scope.totalItems = data.data.total;
                if(data.data.total > 0){
                    $(".paginationBlock").show();
                }else{
                    $(".paginationBlock").hide();
                }
                
            },function(err) {
                console.log(err);
            });
        }

        $scope.setPage = function(pageNo) {
            $scope.currentPage = pageNo;
        };

        $scope.pageChanged = function(page) {
            console.log('Page changed to: ' + $scope.currentPage);
            $scope.searchCode($scope.currentPage);
        };

        $scope.documentStatusChange = function(){
            var actionName = $(event.target).attr('value');
            $http({
                url: 'workingPage/documentStatusChange/?fileId='+$scope.fileId+'&status='+actionName, 
                method: "GET",
            }).then(function(data){ 
                $location.path('/landingPage');
            },function(err) {
                console.log(err);
            });
        }

    }]);