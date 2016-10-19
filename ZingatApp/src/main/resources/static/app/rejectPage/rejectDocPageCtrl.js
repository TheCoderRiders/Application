angular.module('RejectDocPageController', ['ngDialog'])
    .controller("rejectDocPageCtrl",["$scope","$location","$http","ngDialog","workingPageService", function($scope,$location,$http,ngDialog,workingPageService){

        $http({
          url: 'workingPage/getDocRejectionReasonList', 
          method: "GET",
          headers: { 
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          }
        }).then(function(data){ 
            $scope.rejectionDocList = data.data;
        },function(err) {
            console.log("Bucket Err: "+err);
        });

        /* called on cancel click */
        $scope.closePopup = function(){
          ngDialog.close();
        }

        /* click on radio button clicked */
        $scope.rejectDocChecked = function(option){
            $(".rejectDocComment").val(option.rejectionReasonDesc)          
        }

        /* called on document submit clicked*/
        $scope.rejectDoc = function(){
          var getObj = workingPageService.getRequestParameter().obj[0];
          var requestObj = {};
          requestObj.fileId = getObj.fileId;
          requestObj.status = getObj.status;
          debugger;
          requestObj.docRejectionReason = JSON.parse($(".rejectionDoc.ng-touched").attr('id'));
          requestObj.docRejectionReason.rejectionReasonDesc = $(".rejectDocComment").val();
          console.log(requestObj);
           $http({
                url: 'workingPage/documentStatusChange', 
                method: "POST",
                data : JSON.stringify(requestObj)
            }).then(function(data){ 
                ngDialog.close();
                $location.path('/landingPage');
            },function(err) {
                console.log(err);
            });
        }

      }
]);