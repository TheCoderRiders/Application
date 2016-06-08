angular.module('LoginController', [])
    .controller("loginCtrl",["$scope","$location","$http", function($scope,$location,$http){
        $scope.processLogin = function(){
            var userName = $scope.userName;
            var userPassword = $scope.userPassword;
            
            /*$http.get('extras/data.json').success(function(response){ //make a get request to mock json file.
                $scope.data = response; //Assign data received to $scope.data
            })
            .error(function(err){
                //handle error
            })*/
            
            if(userName == 'a' && userPassword == 'a'){
                $location.path('/landingPage');
            }
        }
    }]);
