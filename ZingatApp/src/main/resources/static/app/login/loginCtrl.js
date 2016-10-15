angular.module('LoginController', ['ngCookies'])
    .controller("loginCtrl",["$scope","$location","$http","$cookies", function($scope,$location,$http,$cookies){
        $scope.processLogin = function(){
            var credentials=new Object();
            credentials.userName = $scope.userName;
            credentials.userPassword = $scope.userPassword;

            var headers = credentials ? {authorization : "Basic "
                + btoa(credentials.userName + ":" + credentials.userPassword)
            } : {};

            $http.get('user',{headers : headers}, {
                headers: { 'Content-Type': 'application/json',
                    'Accept': 'application/json'}
            }).success(function(data){ //make a get request to mock json file.
                if (data.username) {
                    $cookies.put("userName",data.username);
                    $cookies.put("userId",data.userId);
                    $cookies.put("clientName",data.clientName);
                    $scope.authenticated = true;
                    $location.path('/landingPage');
                } else {
                    $scope.authenticated = false;
                }
            })
            .error(function(err){
                console.log(err);
                $scope.authenticated = false;
            })
        }
    }]);