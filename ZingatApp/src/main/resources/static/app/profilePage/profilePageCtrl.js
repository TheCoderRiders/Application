angular.module('ProfilePageController', [])
    .controller("profilePageCtrl",["$scope","$location","$http", function($scope,$location,$http){

        /*$scope.profileJSON ="{
              "address1": "Wakad",
              "address2": "Signature Heights",
              "city": "Pune",
              "contactNo": "9765738449",
              "country": "India",
              "dob": "Wed Nov 27 1991",
              "email": "harshalbasket24@gmail.com",
              "firstName": "Harshal",
              "gender": "Male",
              "lastName": "Sharma",
              "password": "P@ssw0rd@123",
              "repassword": "P@ssw0rd@123",
              "state": "Maharashtra",
              "zipcode": "411057"
            }";*/
          $scope.userObj = {
             userName : "Abhi",
             userRole : "Coder",
             firstName : "Abhijeet",
             lastName : "Chikhalikar",
             gender : "Male",
             dob : "11/27/1991",
             address1 : "Mushi",
             address2 : "Wakad",
             city : "Pune",
             state : "Maharashtra",
             country : "India",
             zipcode : "411057",
             email : "abhijeet.chikhalikar@gmail.com",
             contactNo : "9876543210",
             password : "P@ssw0rd@123",
             repassword : "P@ssw0rd@123"
          }
        $scope.updateProfile = function(userObj){
            console.log(userObj);

        }
    }]);