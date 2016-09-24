angular.module('LandingPageController', ['ngSanitize','ngDialog'])
    .controller("landingPageCtrl",["$scope","$http","$timeout","$location","ngDialog", function($scope,$http,$timeout,$location,ngDialog){
        /*Show First bucket as selected*/
        $scope.selected = 0;
        $scope.selectedFile = 0;
        $scope.showAction = false;
        $scope.checkFiled = [];
        $scope.imageSrc = '../../img/ascending.png';
        
        $scope.tempObj = {};
        //$scope.tempObj.bucketName = "New";
        $scope.tempObj.orderBy = "";
        $scope.tempObj.pageNumber = 1;
        $scope.tempObj.isAsc = true;


        $scope.totalItems = 164;
        $scope.currentPage = 1;

          $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
          };

          $scope.pageChanged = function() {
            console.log('Page changed to: ' + $scope.currentPage);
          };

          $scope.maxSize = 5;
          $scope.bigTotalItems = 175;
          $scope.currentPage = 1;

        
        $timeout(function() {
          angular.element('ul').find('li.active').trigger('click')
        }, 500);
        
        
        $http({
            url: 'worklistPage/bucketInfo', 
            method: "GET",
            headers: { 
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data){ //make a get request to mock json file.
            $scope.listGroup = data.data.buckets;
            $scope.group = data.data.sortParams;
            $scope.tempObj.orderBy = data.data.sortParams.selectedOption.id;
            $scope.action = data.data.actions;
            fetchList();
        },function(err) {
            console.log("Bucket Err: "+err);
        });


        /*Autosuggesstion for search*/
        $scope.loadTags = function(query) {
            return $http.get('json/tags.json');
        };
        
        
        /* function called on Bucket clicked */
        $scope.changeBucket = function(event,index) {
            
            $scope.tempObj.bucketName = $(event.currentTarget).attr('id');
            $scope.tempObj.orderBy = $('#groupBy option:selected').attr('value');
            $scope.tempObj.pageNumber = "1";
            $scope.tempObj.isAsc = true;
            $scope.selected = index; 
            $scope.selectedFile = 0;
            fetchList();
        };
        

       function fetchList(){
            $http.get('worklistPage/getFileDetails?bucketName='+$scope.tempObj.bucketName+'&orderBy='+$scope.tempObj.orderBy+'&pageNumber=1&isAsc='+$scope.tempObj.isAsc,{
                headers: { 
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }).success(function(data){ //make a get request to mock json file.
                $scope.roles = data;
                /*for(var i=0; i<data.length; i++){
                    $scope.roles[i].checkBoxVisible = true;
                }*/
                $timeout(function() {
                  angular.element('ul').find('li.selectedItem').trigger('click')
                }, 100);
               
            }).error(function(err){
                console.log(err);
            })
       }
            
          $scope.user = {
            roles: []
          };
        
          $scope.checkAll = function() {
              if($scope.mycheckbox == true){
                  $scope.showAction = true;
                  $scope.user.roles = angular.copy($scope.roles);
              }else{
                  $scope.user.roles = [];
                  $scope.showAction = false;
              }
          };
        $scope.listCheckAll = function(){
            if($scope.user.roles.length > 0){
                $scope.showAction = true;
            }else{
                $scope.showAction = false;
            }
        }

        /*function called on action drop down*/
        $scope.getCheckedFile = function(mySelect){
            ngDialog.open({ template: 'app/landingPage/popupTemplate.html', controller: 'popupPageCtrl',className: 'ngdialog-theme-default' });

            var assigneeObj = {};
            assigneeObj.id = mySelect.id;
            assigneeObj.name = mySelect.name;
           
            var tempAssignee = {};
            tempAssignee.assignee = assigneeObj
            
            var tempArray = [];
            var totalFiles = $scope.user.roles;
            for(var i = 0; i<totalFiles.length; i++){
                var tempObj = {};
                
                tempObj.fileId = totalFiles[i].fileId;
                tempObj.fileName = totalFiles[i].fileName;
                tempArray.push(tempObj)
                
            }            
            tempAssignee.checkFiles = tempArray;
            localStorage.setItem("checkFiled",JSON.stringify(tempAssignee));
            $scope.checkFiled.push(tempAssignee);
            /*var requestedObject = {};
            requestedObject.assignedUserId = "4";
            requestedObject.assignedUserName = "abhi_coder";
            requestedObject.fileId = tempAssignee.checkFiles[0].fileId;
            requestedObject.actionId = assigneeObj.id;
            console.log(requestedObject);*/

            
            /*$http({
                url: 'worklistPage/assignedTo', 
                method: "POST",
                data : JSON.stringify(requestedObject),
                headers: { 
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }).then(function(data){ //make a get request to mock json file.
               console.log(data);
            },function(err) {
                console.log("Bucket Err: "+err);
            });*/

        }
        
        /*drop down group by items*/
       /* $scope.group = {
            availableOptions: [
              {id: '1', name: 'Assignee Date'},
              {id: '2', name: 'File'},
              {id: '3', name: 'Status'}
            ],
            selectedOption: {id: '1', name: 'Assignee Date'} //This sets the default value of the select in the ui
        };*/
        
        /* function called on ascending/descending click */
        $scope.changeImageOrder = function(){
            debugger;
            if($scope.imageSrc == "../../img/ascending.png"){
                $scope.isAsc = false;
                $scope.imageSrc = "../../img/descending.png";
            }else{
                $scope.isAsc = true;
                $scope.imageSrc = "../../img/ascending.png";
            }
            $scope.tempObj.isAsc = $scope.isAsc;
            var selectedOption = {};
            selectedOption = $scope.group.selectedOption;
            console.log(selectedOption);
            fetchList();
        }
        
        /* function called on file clicked to display file details */
        $scope.getFileDetails = function($event,$index,fileDetails){
            $scope.selectedFile = $index;
            $scope.fileName = fileDetails.fileName;
            localStorage.setItem("clickedFileId",fileDetails.fileId);
            $http({
                url: 'worklistPage/getFileContents?fileId='+fileDetails.fileId, 
                method: "GET",
            }).then(function(data){ //make a get request to mock json file.
                $scope.fileContent = data.data.data.replace(/\n/g,"<br>");
            },function(err) {
                console.log(err);
            });

        }
        
        /* function called on group by dropdown */
        $scope.getGroupByOption = function(mySelect){
            $scope.tempObj.orderBy = mySelect.id;
            fetchList();
        }
        
        /* function called on mouse enter of userName to show login dropdown*/        
        $scope.openLoginDropDown = function(){
            var profilePopup = $(".loginHeader .login");
            
            if (profilePopup.length != 0) {
                $(".loginHeader").addClass("selected");

                setTimeout(function() {   
                    var profilePopupTopPos = $(".loginHeader.selected").height();                     
                    var profilePopupWidth = $(".loginHeader.selected").width();                     
                    $(".loginHeader .loginDropDown").css({"top":profilePopupTopPos,"width":profilePopupWidth,"display":"block"});
                    $(".loginDropDown").show();
                }, 100);
            }
        }

        /* function called on mouse leave of userName*/
        $scope.closeLoginDropDown = function(){
            $(".loginDropDown").css('display','none');
            $(".loginHeader").removeClass('selected');
        }
        
        /* function called on view click */
        $scope.redirectToWorking = function(){
            $location.path('/workingPage');
        }

        /* function called on profile click */
        $scope.redirectToProfile = function(){
            $location.path('/profilePage');
        }
        
        /* function called on logout click */
        $scope.redirectToLogin = function(){
            $location.path('/login');
        }
    }])