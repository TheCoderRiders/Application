angular.module('LoginController', [])
    .controller("loginCtrl",["$scope","$location","$http", function($scope,$location,$http){
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
                //$scope.data = response; //Assign data received to $scope.data
                if (data.name) {
                    $scope.authenticated = true;
                } else {
                    $scope.authenticated = false;
                }
            })
                .error(function(err){
                    console.log(err);
                    $scope.authenticated = false;
                })

            /* if(userName == 'a' && userPassword == 'a'){
             $location.path('/landingPage');
             }*/
        }
    }]);