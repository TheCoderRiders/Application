angular.module('LandingPageController', ['ngSanitize', 'ngDialog','ngCookies'])
    .controller("landingPageCtrl", ["$scope", "$http", "$timeout", "$location", "ngDialog","$cookies","$route", function($scope, $http, $timeout, $location, ngDialog,$cookies,$route) {
        /*Show First bucket as selected*/
        $scope.selected = 0;
        $scope.selectedFile = 0;
        $scope.showAction = false;
        $scope.checkFiled = [];
        $scope.editRight = false;

        $('[data-toggle="tooltip"]').tooltip();

        $scope.tempObj = {};
        $scope.perPageCount = 20;
        $scope.tempObj.orderBy = "";
        $scope.tempObj.isAsc = true;
        $scope.userName = $cookies.get("userName");
        $scope.clientName = $cookies.get("clientName");
        fetchBucketList();

        $scope.setPage = function(pageNo) {
            $scope.currentPage = pageNo;
        };

        $scope.pageChanged = function() {
            $scope.tempObj.pageNumber = $scope.currentPage
            console.log('Page changed to: ' + $scope.currentPage);
            $scope.selectedFile = 0;
            fetchList();
        };

        $scope.maxSize = 4;
        $scope.totalItems;
        $scope.currentPage = 1;


        function fetchBucketList() {
            $http({
                url: 'worklistPage/bucketInfo',
                method: "GET",
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }).then(function(data) { //make a get request to mock json file.
                $scope.listGroup = data.data.buckets;
                $scope.group = data.data.sortParams;
                $scope.tempObj.orderBy = data.data.sortParams.selectedOption.id;
                $scope.action = data.data.actions;
                $timeout(function() {
                    angular.element('ul').find('li.active').trigger('click')
                }, 500);
            }, function(err) {
                console.log("Bucket Err: " + err);
            });

        }


        /*Autosuggesstion for search*/
        $scope.loadTags = function(query) {
            return $http.get('json/tags.json');
        };

        /* function called on lrefresh click*/
        $scope.refreshList = function() {
            fetchBucketList();
        };

        /* function called on Bucket clicked */
        $scope.changeBucket = function(event, index) {
            $scope.tempObj.bucketName = $(event.currentTarget).attr('id');
            $scope.tempObj.orderBy = $('#groupBy option:selected').attr('value');
            $scope.currentPage = 1;
            $scope.tempObj.pageNumber = "1";
            $scope.tempObj.isAsc = true;
            $scope.selected = index;
            $scope.selectedFile = 0;
            $scope.showAction = false;
            fetchList();
        };


        function fetchList() {
            $http.get('worklistPage/getFileDetails?bucketName=' + $scope.tempObj.bucketName + '&orderBy=' + $scope.tempObj.orderBy + '&pageNumber=' + $scope.currentPage + '&isAsc=' + $scope.tempObj.isAsc, {
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }).success(function(data) { //make a get request to mock json file.
                $scope.roles = data.fileDetailsList;
                for(var i=0; i<data.fileDetailsList.length; i++){
                    if(data.fileDetailsList[i].checkBoxVisible){
                        $(".inputDiv").css('visibility','inherit');
                        $scope.showAllCheckbox = true;              
                    }else{
                        $(".inputDiv").css('visibility','hidden');
                        $scope.showAllCheckbox = true;
                    }
                }
                $timeout(function() {
                    angular.element('ul').find('li.selectedItem').trigger('click')
                }, 100);
                $scope.totalItems = angular.element('ul').find('li.active span').text().trim();

            }).error(function(err) {
                console.log(err);
            })
        }

        $scope.user = {
            roles: []
        };

        $scope.checkAll = function() {
            if ($scope.mycheckbox == true) {
                $scope.showAction = true;
                $scope.user.roles = angular.copy($scope.roles);
            } else {
                $scope.user.roles = [];
                $scope.showAction = false;
            }
            $(".actionDropDown").css('display', 'none');
            $(".inputDiv").removeClass('selected');
        };
        $scope.checkAllFiles = function(){
            $scope.showAction = true;
            $scope.mycheckbox = true;
            $scope.user.roles = angular.copy($scope.roles);
            $(".actionDropDown").css('display', 'none');
            $(".inputDiv").removeClass('selected');
        }
        $scope.uncheckAllFiles = function(){
            $scope.showAction = false;
            $scope.mycheckbox = false;
            $scope.user.roles = [];
            $(".actionDropDown").css('display', 'none');
            $(".inputDiv").removeClass('selected');
        }
        $scope.listCheckAll = function() {
            if ($scope.user.roles.length > 0) {
                $scope.showAction = true;
            } else {
                $scope.showAction = false;
            }
        }

        /*function called on action drop down*/
        $scope.getCheckedFile = function(mySelect) {
            var assigneeObj = {};
            assigneeObj.id = mySelect.id;
            assigneeObj.name = mySelect.name;

            var tempAssignee = {};
            tempAssignee.assignee = assigneeObj

            var tempArray = [];
            var totalFiles = $scope.user.roles;
            for (var i = 0; i < totalFiles.length; i++) {
                var tempObj = {};
                tempObj.fileId = totalFiles[i].fileId;
                tempObj.fileName = totalFiles[i].fileName;
                tempArray.push(tempObj)

            }
            tempAssignee.checkFiles = tempArray;
            $cookies.put("checkFiled", JSON.stringify(tempAssignee));
            $scope.checkFiled.push(tempAssignee);

            ngDialog.open({
                template: 'app/landingPage/popupTemplate.html',
                controller: 'popupPageCtrl',
                className: 'ngdialog-theme-default'
            });

        }

        /* function called on ascending/descending click */
        $scope.changeOrder = function() {
            if ($(event.target).hasClass("fa-sort-amount-asc")) {
                $(event.target).removeClass("fa-sort-amount-asc");
                $(event.target).addClass("fa-sort-amount-desc");
                $scope.isAsc = false;
            } else {
                $(event.target).removeClass("fa-sort-amount-desc");
                $(event.target).addClass("fa-sort-amount-asc");
                $scope.isAsc = true;
            }
            $scope.tempObj.isAsc = $scope.isAsc;
            var selectedOption = {};
            selectedOption = $scope.group.selectedOption;
            console.log(selectedOption);
            fetchList();
        }

        /* function called on file clicked to display file details */
        $scope.getFileDetails = function($event, $index, fileDetails) {
            $scope.selectedFile = $index;
            $scope.fileName = fileDetails.fileName;
            $cookies.put("clickedFileId", fileDetails.fileId);
            $http({
                url: 'worklistPage/getFileContents?fileId=' + fileDetails.fileId,
                method: "GET",
            }).then(function(data) { //make a get request to mock json file.
                $scope.fileContent = data.data.data;
                if(data.data.fileMode == "Edit"){
                    $scope.editRight = true;
                    $cookies.put("editRight",true);
                }else{
                    $scope.editRight = false;
                    $cookies.put("editRight",false);
                }
            }, function(err) {
                console.log(err);
            });

        }

        /* function called on group by dropdown */
        $scope.getGroupByOption = function(mySelect) {
            $scope.tempObj.orderBy = mySelect.id;
            fetchList();
        }

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

        /* function called on mouse enter of action to show action dropdown*/
        $scope.openActionDropDown = function(){
            var actionPopup = $(".inputDiv");
            if (actionPopup.length != 0 && !$(".inputDiv").hasClass('selected')) {
                $(".inputDiv").addClass("selected");
                setTimeout(function() {
                    var profilePopupTopPos = $(".inputDiv.selected").height();
                    var profilePopupWidth = $(".inputDiv.selected").width();
                    $(".leftSorter .actionDropDown").css({
                        "top": profilePopupTopPos,
                        "width": profilePopupWidth+0.5,
                        "display": "block"
                    });
                    $(".actionDropDown").show();
                }, 100);
            } else {
                $(".actionDropDown").css('display', 'none');
                $(".inputDiv").removeClass('selected');
            }
        }


        /* function called on mouse leave of userName*/
        $scope.closeLoginDropDown = function() {
            $(".loginDropDown").css('display', 'none');
            $(".loginHeader").removeClass('selected');
        }

        /* function called on mouse leave of action*/
        $scope.closeActionDropDown = function(){
            $(".actionDropDown").css('display', 'none');
            $(".inputDiv").removeClass('selected');
        }

        /* function called on view click */
        $scope.redirectToWorking = function() {
            $location.path('/workingPage');
        }

        /* function called on profile click */
        $scope.redirectToProfile = function() {
            $location.path('/profilePage');
        }

        /* function called on logout click */
        $scope.redirectToLoginPage = function() {
            /*$http({
              url: '/logout',
              method : 'GET',
              headers: {
                  'Content-Type': 'application/json',
                  'Accept': 'application/json'
              }
            }).then(function(data) { 

            }, function(err) {
                console.log("Profile Err: " + err);
            });*/
            $location.path('/login');
        }

        /* function called on hospital name click */
        $scope.redirectToLandingPage = function(){
            $route.reload();
        }
    }])