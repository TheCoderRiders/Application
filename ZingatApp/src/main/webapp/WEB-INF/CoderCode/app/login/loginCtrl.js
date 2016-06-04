angular.module('LoginController', [])
    .controller("loginCtrl",["$scope","$location", function($scope,$location){
        $scope.processLogin = function(){
            var userName = $scope.userName;
            var userPassword = $scope.userPassword;
            if(userName == 'a' && userPassword == 'a'){
                $location.path('/landingPage');
            }
        }
    }]);