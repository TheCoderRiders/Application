angular.module('WorkingPageController', ['ngSanitize','ngScrollable','ngCookies','ngDialog'])
    
    .service("workingPageService", ["$http", function($http){
        this.setRequestParameter = function(){
            this.obj = arguments;
        }
        this.getRequestParameter = function(){
            return this;
        }
        this.updateGetCodes = function(requestedData,targetHeading,successCallback,errorCallback){
            $http({
                url: 'workingPage/codeAction', 
                method: "POST",
                dataType : "application/json",
                data : JSON.stringify(requestedData)
            }).then(successCallback,errorCallback);
        }
    }])

    .controller("workingPageCtrl",["$scope","$rootScope","$location","$http","ngDialog","$cookies","$timeout","$controller","workingPageService", function($scope,$rootScope,$location,$http,ngDialog,$cookies,$timeout,$controller,workingPageService){

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

        $rootScope.$on("codeActionEmit",function (event,data) {
            var targetHeading = data.targetHeading;
            delete data.targetHeading;
            var rejectedCodeClass = data.rejectedCode.code;
            var markLeftSide = $(".leftSideContent mark[class*='"+rejectedCodeClass+"']")
            $scope.globalObj = data;
            $scope.suggestedCode = data.suggestedCode;
            $scope.suggestedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            $scope.acceptedCode = data.acceptedCode;
            $scope.acceptedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            $scope.rejectedCode = data.rejectedCode;
            $scope.rejectedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            /*$scope.mayBeCode = data.mayBeCode;
            $scope.mayBeCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });*/
            if(targetHeading == "Suggested" && $scope.suggestedCode.length < 1){
                $scope.emptyData = true;
            }else if(targetHeading == "Coded Code" && $scope.acceptedCode.length < 1){
                $scope.emptyData = true;
            }else if(targetHeading == "Rejected" && $scope.rejectedCode.length < 1){
                $scope.emptyData = true;
            }/*else if(targetHeading == "MayBe" && $scope.mayBeCode.length < 1){
                $scope.emptyData = true;
            }*/
            $scope.acceptCode = false;
            $scope.rejectCode = false;
            ngDialog.close()
        });

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

        /* function called on tab changed */
        $scope.tabChanged = function(){
            $('[data-toggle="tooltip"]').tooltip();
            var clickedTabIndex = $(event.target).parent().attr('index');
            $scope.emptyData = false;
            if(clickedTabIndex == "0" && $scope.acceptedCode.length < 1 ){
                $scope.emptyData = true;
            }else if(clickedTabIndex == "1" && $scope.suggestedCode.length < 1){
                $scope.emptyData = true;
            }else if(clickedTabIndex == "2" && $scope.rejectedCode.length < 1){
                $scope.emptyData = true;
            }else{
                $(".searchCode").val("");
                $(".paginationBlock").hide();
                $(".codeSearchContainer").hide();
                $scope.emptyData = false;
            }
            $scope.$broadcast('content.reload');
        }

        /* get content of clicked file*/
        $http({
            url: 'worklistPage/getFileContents?fileId='+$scope.fileId+"&page=workingPage",
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
        }).then(function(data){ 
            $scope.globalObj = data.data;
            if(data.data.acceptedCode.length < 1){
                $scope.emptyData = true;
            }

            $scope.suggestedCode = data.data.suggestedCode;
            $scope.suggestedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            $scope.acceptedCode = data.data.acceptedCode;
            $scope.acceptedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            $scope.rejectedCode = data.data.rejectedCode;
            $scope.rejectedCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });
            /*$scope.mayBeCode = data.data.mayBeCode;
            $scope.mayBeCode.sort(function(a, b){
                var dateA = new Date(a.dos);
                var dateB = new Date(b.dos);
                return dateA-dateB;
            });*/
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
            }, 5000); 

            $(".rightSideContent").animate({ 
                scrollTop: elementRelativeTop
            }, 1000);
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
            }, 5000);    

            $(".leftSideContent").animate({ 
                scrollTop: elementRelativeTop
            }, 1000);
        }


        $scope.codeStatus = function(code,section,actionName){
            var selectedCode = {};
            selectedCode.sectionName = section.sectionName;
            selectedCode.codes = [];
            delete code.$$hashKey;
            selectedCode.codes.push(code);
            
            var action,codeActionType;
            var targetHeading = $(".nav li.active").attr('class').split(' ')[0];

            
            if($(event.target).attr('class') == "fa fa-check"){
                action = "Accept";
                codeActionType = $(".nav li.active").attr('class').split(' ')[0];
            }else{
                action = "Reject";
                codeActionType = $(".nav li.active").attr('class').split(' ')[0];
            }
            var requestedData = { };
            requestedData.allCodes = $scope.globalObj;
            requestedData.sectionName = selectedCode.sectionName;
            requestedData.action = action;
            requestedData.code = code;
            requestedData.dos = section.dos;
            requestedData.sign = section.sign;
            requestedData.codeActionType = codeActionType;

            if(action == "Reject"){
                workingPageService.setRequestParameter(requestedData,targetHeading);
                ngDialog.open({
                    template: 'app/rejectPage/rejectCodePage.html',
                    className: 'ngdialog-theme-default rejectCodeDocPopup',
                    scope: $scope
                })
            }else{
                workingPageService.updateGetCodes(requestedData,targetHeading,function(data){
                    $scope.globalObj = data.data;
                    $scope.suggestedCode = data.data.suggestedCode;
                    $scope.suggestedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $scope.acceptedCode = data.data.acceptedCode;
                    $scope.acceptedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $scope.rejectedCode = data.data.rejectedCode;
                    $scope.rejectedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $scope.mayBeCode = data.data.mayBeCode;
                    $scope.mayBeCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    if(targetHeading == "Suggested" && $scope.suggestedCode.length < 1){
                        $scope.emptyData = true;
                    }else if(targetHeading == "Coded Code" && $scope.acceptedCode.length < 1){
                        $scope.emptyData = true;
                    }else if(targetHeading == "Rejected" && $scope.rejectedCode.length < 1){
                        $scope.emptyData = true;
                    }/*else if(targetHeading == "MayBe" && $scope.mayBeCode.length < 1){
                        $scope.emptyData = true;
                    }*/
                    $scope.acceptCode = false;
                    $scope.rejectCode = false;
                },function(){

                });
            }

        }

        /* called on add code search input keypress */
        $scope.searchCode = function(searchTerm){
            $scope.searchText = searchTerm;
            searchedCode(searchTerm)
        }

        /* called on pagination changed*/
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
                    if(data.data.total > 0){
                        $scope.totalItems = data.data.total;
                        $scope.emptyData = false;
                        $(".paginationBlock").show();
                        $scope.selectedFile = 0;
                        $(".codeSearchContainer").show();
                        $scope.codeList = data.data.codes;
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
            setTimeout(function() {
                $('[data-toggle="tooltip"]').tooltip(); 
            }, 500);
        }

        

        $scope.documentStatusChange = function(action){
            var actionName = $(event.target).attr('value');
            var obj = {};
            var selectedBucket = $cookies.get("selectedBucket");
           
            if(selectedBucket != "Completed" && actionName != "REJECTED" && actionName != "SUBMIT"){
                actionName = "DRAFT";
            }else if(actionName != "REJECTED"){
                actionName = "SUBMIT";
            }
            obj.fileId = $scope.fileId;
            obj.status = actionName;
            if(actionName == "REJECTED"){
                $http({
                    url: 'workingPage/getDocRejectionReasonList', 
                    method: "GET",
                    headers: { 
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    }
                }).then(function(data){ 
                    workingPageService.setRequestParameter(obj);
                    ngDialog.open({
                        template: 'app/rejectPage/rejectDocPage.html',
                        className: 'ngdialog-theme-default'
                    });

                },function(err) {
                    console.log("Bucket Err: "+err);
                });
            }else{
                obj.docRejectionReason = null;
                $http({
                    url: "workingPage/documentStatusChange",
                    method: "POST",
                    data : JSON.stringify(obj),
                }).then(function(data){ 
                    $location.path('/landingPage');
                },function(err) {
                    console.log(err);
                });
            }
        }

        $scope.addCode = function(selectedCode,tags){
            var codeObj = {};
            var tempArr = [];
            var updateContent;
            
            var addedEvidence = $(".tag-item").find('span').text();
            if(tags){
                for(var i=0; i<tags.length; i++){
                    tempArr.push(tags[i].text);
                    var str1 = $(".leftSideContent .scrollable-right div")[0].innerHTML; 
                    var reg = new RegExp(tags[i].text, 'g') 
                    var newstr = str1.replace(reg, "<mark class='"+selectedCode.code+"'>"+tags[i].text+"</mark>"); 
                    /*for first matched element*/
                    /*updateContent = $(".leftSideContent .scrollable-right div")[0].innerHTML.replace(tags[i].text,"<mark class='"+selectedCode.code+"'>"+tags[i].text+"</mark>");
                    */
                    //$(".leftSideContent .scrollable-right div")[0].innerHTML = updateContent;
                    /* for all globally matched element*/
                    //updateContent = $(".leftSideContent .scrollable-right div")[0].innerHTML.replace("/"+tags[i].text+"/g","<mark class='"+selectedCode.code+"'>"+tags[i].text+"</mark>");
                    updateContent = newstr;
                    $(".leftSideContent .scrollable-right div")[0].innerHTML = updateContent;
                }
            }
            codeObj.code = selectedCode.code;
            codeObj.desc = selectedCode.desc;
			codeObj.token = tempArr;
            var requestedData = {};
            requestedData.allCodes = $scope.globalObj;
            requestedData.sectionName = "Added Code";
            requestedData.action = "AddCode";
            requestedData.code = codeObj;
            requestedData.codeActionType = "New";
            requestedData.dos = "";
            requestedData.sign = "";
            requestedData.token = tempArr;
            if(addedEvidence){
                $(".tags").removeClass('error');
                $http({
                    url: 'workingPage/codeAction', 
                    method: "POST",
                    dataType : "application/json",
                    data : JSON.stringify(requestedData)
                }).then(function(data){ 
                    $scope.globalObj = data.data;
                    $scope.suggestedCode = data.data.suggestedCode;
                    $scope.suggestedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $scope.acceptedCode = data.data.acceptedCode;
                    $scope.acceptedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $scope.rejectedCode = data.data.rejectedCode;
                    $scope.rejectedCode.sort(function(a, b){
                        var dateA = new Date(a.dos);
                        var dateB = new Date(b.dos);
                        return dateA-dateB;
                    });
                    $(".searchCode").val("")
                    $(".paginationBlock").hide();
                    $(".codeSearchContainer").hide();
                },function(err) {
                    console.log("error while adding code");
                });
            }else{
                $(".tags").addClass('error');
            }
            
        }

        $scope.addEvidence = function(){
            $(".individualEvidence").val('');
            $(".tags").removeClass('error');
            var currentTarget = $(event.target).parents('li').find('label');
            if($(currentTarget).hasClass("out")) {
                $(event.target).parents('li').find('span.addCodeAction i').removeClass('fa-angle-double-down');
                $(event.target).parents('li').find('span.addCodeAction i').addClass('fa-angle-double-up');
                $(currentTarget).addClass("in");
                $(currentTarget).removeClass("out");
            } else {
                $(event.target).parents('li').find('span.addCodeAction i').removeClass('fa-angle-double-up');
                $(event.target).parents('li').find('span.addCodeAction i').addClass('fa-angle-double-down');
                $(currentTarget).addClass("out");
                $(currentTarget).removeClass("in");
            }
        }

    }])
    