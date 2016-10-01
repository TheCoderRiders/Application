angular.module('ProfilePageController', [])
    .controller("profilePageCtrl",["$scope","$location","$http", "$cookies", function($scope,$location,$http,$cookies){

      $scope.userName = $cookies.get("userName");
      $scope.clientName = $cookies.get("clientName");
      getUserProfile();

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

      /* function called on mouse leave of userName*/
      $scope.closeLoginDropDown = function() {
          $(".loginDropDown").css('display', 'none');
          $(".loginHeader").removeClass('selected');
      }

      /* function called on logout click */
      $scope.redirectToLogin = function() {
          $location.path('/login');
      }

      /* function called on hospital name click */
      $scope.redirectToLandingPage = function(){
          $location.path('/landingPage');
      }

      function getUserProfile(){
        $http({
            url: 'profilePage/getProfile',
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(function(data) { //make a get request to mock json file.
            $scope.userObj = data.data;
        }, function(err) {
            console.log("Profile Err: " + err);
        });
      }

      $scope.cancelProfileChanges = function(){
        getUserProfile();  
        $scope.isGenderInvalid = false;
        $scope.isemailInvalid = false;
        $scope.isPassInvalid = false;
        $scope.isRePassInvalid = false;
        $scope.isfirstNameInvalid = false;
        $scope.islastNameInvalid = false;
      }

      $('.dateOfBirth').datepicker({
        container: '.rangeDropDown',
        autoclose: true,
        todayHighlight: true,
        orientation: "top left",
        zIndexOffset : 9999,
        hide: function() {
          this.remove();
        },
        show: function(e) {
          console.log(e);
        }
      })

      $('[data-toggle="tooltip"]').tooltip(); 

      $scope.updateProfile = function(userObj){
          //console.log(userObj);
          $scope.isfirstNameInvalid = $scope.checkName(userObj.firstName);
          $scope.islastNameInvalid = $scope.checkName(userObj.lastName);

          if(userObj.gender == ""){
            $scope.isGenderInvalid = "Please select a Gender";
          }else{
            $scope.isGenderInvalid = false;
          }
          if(userObj.email == ""){
            $scope.isemailInvalid = "Email should not be null";
          }else if(!/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i.test(userObj.email)){
            $scope.isemailInvalid = "Please enter a valid email address";
          }else{
            $scope.isemailInvalid = false;
          }
        
          if(userObj.password == "") {
            $scope.isPassInvalid = "Please enter a password";
          }else if(!/^(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/i.test(userObj.password)){
            $scope.isPassInvalid = "Password doesn't meet criteria";
            $(".passwordHelp i").tooltip('show');
          }else{
            $(".passwordHelp i").tooltip('hide');
            $scope.isPassInvalid = false;
          }

          if(userObj.repassword == "") {
            $scope.isRePassInvalid = "Please enter a password";
          }else if(userObj.repassword != userObj.password){
            $scope.isRePassInvalid = "Password doesn't match";
          }else{
            $scope.isRePassInvalid = false;
          }

          if(!$scope.isfirstNameInvalid && !$scope.islastNameInvalid && !$scope.isemailInvalid && !$scope.isGenderInvalid && !$scope.isPassInvalid && !$scope.isRePassInvalid){
            alert("posted data successfully");
            $http({
                url: 'profilePage/saveProfile', 
                method: "POST",
                dataType : "application/json",
                data : JSON.stringify(userObj)
            }).then(function(data){ //make a get request to mock json file.
                
            },function(err) {
                console.log("error while saving profile");
            });
          }else{
            //alert("please fill mandatory fields");
          }

      }
      $scope.checkName = function(val) {
        if(val == "")
          return "Name should not be null";
        else if(val.length < 3 )
          return "Minimum length is 3";
        else if(val.length > 30)
          return "Maximum length is 25";
        else if(!/^[a-zA-Z]+$/.test(val))
          return "Name must contains only characters";

        return false;
      }
      

  }]);