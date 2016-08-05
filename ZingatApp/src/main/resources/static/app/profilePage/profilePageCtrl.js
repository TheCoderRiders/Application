angular.module('ProfilePageController', [])
    .controller("profilePageCtrl",["$scope","$location","$http", function($scope,$location,$http){

      $scope.userObj = {
           userName : "Abhi",
           userRole : "Coder",
           firstName : "harshal",
           lastName : "sharma",
           gender : "Male",
           dob : "11-27-1991",
           address1 : "Mushi",
           address2 : "Wakad",
           city : "Pune",
           state : "Maharashtra",
           country : "India",
           zipcode : "411057",
           email : "abhijeet.chikhalikar@gmail.com",
           contactNo : "9876543210",
           password : "",
           repassword : ""
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
          }else{
            alert("please fill mandatory fields");
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
      $scope.cancelProfileChanges = function(){
        debugger;
        $scope.userObj = {
           userName : "Abhi",
           userRole : "Coder",
           firstName : "harshal",
           lastName : "sharma",
           gender : "Male",
           dob : "11-27-1991",
           address1 : "Mushi",
           address2 : "Wakad",
           city : "Pune",
           state : "Maharashtra",
           country : "India",
           zipcode : "411057",
           email : "abhijeet.chikhalikar@gmail.com",
           contactNo : "9876543210",
           password : "",
           repassword : ""
      }
      }
  }]);