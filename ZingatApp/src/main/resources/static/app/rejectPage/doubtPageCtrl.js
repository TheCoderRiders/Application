angular.module('DoubtPageController', ['ngDialog'])
    .controller("doubtPageCtrl", ["$scope", "$location", "$http", "ngDialog", "workingPageService", function($scope, $location, $http, ngDialog, workingPageService) {

        /* function to fetch predefined doubt list */
        $http({
            url: 'workingPage/getDocRejectionReasonList',
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data) { 
            $scope.doubtList = data.data;
        }, function(err) {
            console.log("Bucket Err: " + err);
        });

        /* click on close icon of popup */
        $scope.closePopup = function() {
            ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.doubtChecked = function(option){
            $(".doubtComment").val(option.rejectionReasonDesc);          
        }

        /* click on Submit of doubt*/
        $scope.submitDoubt = function() {
            
            var getObj = workingPageService.getRequestParameter().obj[0];
            if($(".doubtItem.ng-touched").attr('id') && $(".doubtComment").val().length > 0){
                var requestObj = {};
                requestObj.fileId = getObj.fileId;
                requestObj.status = getObj.status;
                requestObj.doubtItem = JSON.parse($(".doubtItem.ng-touched").attr('id'));
                requestObj.doubtItem.doubtItemReasonDesc = $(".doubtComment").val();
                $(".doubtError").hide()
                /*$http({
                    url: 'workingPage/documentStatusChange', 
                    method: "POST",
                    data : JSON.stringify(requestObj)
                }).then(function(data){ 
                    ngDialog.close();
                    $(".doubtError").hide();
                    $location.path('/landingPage');
                },function(err) {
                    console.log(err);
                });*/
            }else{
                $(".doubtError").show();
            }

        }

    }]);