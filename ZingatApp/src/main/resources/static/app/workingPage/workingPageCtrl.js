angular.module('WorkingPageController', ['ngSanitize','ngScrollbar','ngCookies','ngDialog'])
    
    .controller("workingPageCtrl",["$scope","$rootScope","$location","$http","ngDialog","$cookies","$timeout","$controller", function($scope,$rootScope,$location,$http,ngDialog,$cookies,$timeout,$controller){

        $scope.fileId = $cookies.get("clickedFileId");
        $scope.globalObj;
        $scope.acceptCode = false;
        $scope.rejectCode = false;
        $scope.emptyData = false;
        $scope.totalItems;
        $scope.maxSize = 5;
        $scope.currentPage = 1;
        $scope.perPageCount = 20;
        $scope.selectedFile = 0;
        $scope.searchCode;
        $scope.userName = $cookies.get("userName");
        $scope.clientName = $cookies.get("clientName");
        $scope.editRight = $cookies.get("editRight");
        setTimeout(function() {
            $('[data-toggle="tooltip"]').tooltip(); 
        }, 2000);


        /* function called on mouse enter of userName to show login dropdown*/
        $scope.openLoginDropDown = function() {
            var profilePopup = $(".loginHeader .login");
            if (profilePopup.length != 0 && !$(".loginHeader").hasClass('selected')) {
                $(".loginHeader").addClass("selected");
                setTimeout(function() {
                    var profilePopupTopPos = $(".loginHeader.selected").height();
                    var profilePopupWidth = $(".loginHeader.selected").width();
                    $(".loginHeader .loginDropDown").css({
                        "top": profilePopupTopPos,
                        "width": profilePopupWidth,
                        "display": "block"
                    });
                    $(".loginDropDown").show();
                }, 100);
            } else {
                $(".loginDropDown").css('display', 'none');
                $(".loginHeader").removeClass('selected');
            }
        }

        /* function called on profile click */
        $scope.redirectToProfile = function() {
            $location.path('/profilePage');
        }

        /* function called on logout click */
        $scope.redirectToLogin = function() {
           $location.path('/login');
        }

        /* funnction called on hospital name click */
        $scope.redirectToLandingPage = function(){
            $location.path('/landingPage');
        }

        /* funnction called on back button click */
        $scope.redirectAfterDraftToLandingPage = function(){
            $http({
                url: 'workingPage/documentStatusChange/?fileId='+$scope.fileId+'&status=DRAFT', 
                method: "GET",
            }).then(function(data){ 
                $location.path('/landingPage');
            },function(err) {
                console.log(err);
            });
        }

        /* function called on tab changed */
        $scope.tabChanged = function(){
            $('[data-toggle="tooltip"]').tooltip();
            var clickedTabIndex = $(event.target).parent().attr('index');
            $scope.emptyData = false;
            if(clickedTabIndex == "0" && ($scope.suggestedCode ==  null || $scope.suggestedCode.length ==  0 || $scope.suggestedCode[0].codes.length < 1)){
                $scope.emptyData = true;
            }else if(clickedTabIndex == "1" && ($scope.acceptedCode ==  null || $scope.acceptedCode.length ==  0 || $scope.acceptedCode[0].codes.length < 1)){
                $scope.emptyData = true;
            }else if(clickedTabIndex == "2" && ($scope.rejectedCode ==  null || $scope.rejectedCode.length ==  0 || $scope.rejectedCode[0].codes.length < 1)){
                $scope.emptyData = true;
            }else if(clickedTabIndex == "3" && ($scope.mayBeCode ==  null || $scope.mayBeCode.length ==  0 || $scope.mayBeCode[0].codes.length < 1)){
                $scope.emptyData = true;
            }else{
                $(".searchCode").val("");
                $(".paginationBlock").hide();
                $(".codeSearchContainer").hide();
                $scope.emptyData = false;
            }
        }

        /* get content of clicked file*/
        $http({
            url: 'worklistPage/getFileContents?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.workingFileContent = data.data.data;
            $timeout(function() {
                var mark = document.getElementsByTagName('mark');
                for(var i=0; i<mark.length; i++){
                    mark[i].addEventListener("click", function(ev){
                        $scope.clickedCode($(ev.target).attr('class'));
                    });
                }
            }, 1000);
            
        },function(err) {
            console.log(err);
        });

        /* get codes of clicked file*/
        $http({
            url: 'workingPage/getCodes?fileId='+$scope.fileId, 
            method: "GET",
        }).then(function(data){ //make a get request to mock json file.
            $scope.globalObj = data.data;
            if(data.data.suggestedCode[0].codes.length < 1){
                $scope.emptyData = true;
            }
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
            
            $(".rightSideContent span").parent().removeClass("highlighted");
            $(".rightSideContent span[class*='"+codeId+"']").parent().addClass('highlighted');

            setTimeout(function(){
              $(".rightSideContent span[class*='"+codeId+"']").parent().removeClass('highlighted');
            }, 3000); 

            $(".rightSideContent").animate({ 
                scrollTop: elementRelativeTop
            }, 1500);
        }


        $scope.clickedSuggestedCode = function(){
            var code = $(event.currentTarget).text();
            event.preventDefault();
            var elementTop = $(".leftSideContent mark[class*='"+code+"']").offset().top;
            console.log("elementTop: "+elementTop);
            var divTop = $('.leftSideContent').offset().top;
            console.log("divTop: "+divTop);
            var elementRelativeTop = elementTop - divTop;
            console.log("elementRelativeTop: "+elementRelativeTop);

            $(".leftSideContent mark").removeClass("highlighted");
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
            var targetHeading = $(".nav li.active").attr('heading');
            
            
            if($(event.target).attr('class') == "fa fa-check"){
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


            if(action == "Reject"){
                ngDialog.open({
                    template: 'app/rejectPage/rejectCodePage.html',
                    className: 'ngdialog-theme-default rejectCodeDocPopup',
                    scope: $scope
                })
            }
            
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
            },function(err) {
                console.log("error while code  action");
            });
        }

        $scope.searchCode = function(searchTerm){
            $scope.searchText = searchTerm;
            searchedCode(searchTerm)
        }


        $scope.pageChanged = function(currentPage) {
            $scope.currentPage = currentPage;
            searchedCode($scope.searchText);
        };

        function searchedCode(searchTerm){
            if(searchTerm){
                $http({
                    url: 'workingPage/searchCode?key='+$scope.searchText+'&start='+$scope.currentPage, 
                    method: "GET",
                }).then(function(data){ 
                    $scope.totalItems = data.data.total;
                    if(data.data.total > 0){
                        $scope.emptyData = false;
                        $(".paginationBlock").show();
                        $scope.selectedFile = 0;
                        $(".codeSearchContainer").show();
                        $scope.codeList = data.data.codes
                        if($scope.selectedFile == 0){
                            $(".individualCode").text(data.data.codes[0].code);
                            $(".individualCodeDesc").text(data.data.codes[0].desc);
                        }
                    }else{
                        $scope.emptyData = true;
                        $(".paginationBlock").hide();
                        $(".codeSearchContainer").hide();
                    }
                },function(err) {
                    console.log(err);
                });
            }else{
                $(".paginationBlock").hide();
                $(".codeSearchContainer").hide();
                $scope.emptyData = false;
            }
        }

        $scope.documentStatusChange = function(){
            var actionName = $(event.target).attr('value');
            var obj = {};
            obj.fileId = $scope.fileId;
            obj.status = actionName;


            $http({
              url: 'workingPage/getDocRejectionReasonList', 
              method: "GET",
              headers: { 
                  'Content-Type': 'application/json',
                  'Accept': 'application/json'
              }
            }).then(function(data){ //make a get request to mock json file.
                obj.data = data.data;
                ngDialog.open({
                    template: 'app/rejectPage/rejectDocPage.html',
                    className: 'ngdialog-theme-default'
                });

            },function(err) {
                console.log("Bucket Err: "+err);
            });

            
            /*$http({
                url: 'workingPage/documentStatusChange/?fileId='+$scope.fileId+'&status='+actionName, 
                method: "GET",
            }).then(function(data){ 
                $location.path('/landingPage');
            },function(err) {
                console.log(err);
            });*/
        }

        $scope.getCodeDesc = function($event, $index, code){
            $scope.selectedFile = $index;
            $(".descContainer").find("div.individualCode").text(code.code);
            $(".descContainer").find("div.individualCodeDesc").text(code.desc);
        }

        $scope.addCode = function($event){
            var codeObj = {};
            var tempArr = [];
            var searchText = $(".searchCode").val();
            tempArr.push(searchText);
            codeObj.code = $("ul li.selectedItem").find('label.individualCode').text();
            codeObj.desc = $("ul li.selectedItem").find('label.codeDesc').text();
            var requestedData = {};
            requestedData.allCodes = $scope.globalObj;
            requestedData.sectionName = "Added Code";
            requestedData.action = "AddCode";
            requestedData.code = codeObj;
            requestedData.codeActionType = "New";
            requestedData.token = tempArr;

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
                $(".searchCode").val("")
                $(".paginationBlock").hide();
                $(".codeSearchContainer").hide();
            },function(err) {
                console.log("error while adding code");
            });
        }
    }])
    