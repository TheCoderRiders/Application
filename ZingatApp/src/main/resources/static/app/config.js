var app = angular.module('coderApp', ['ngRoute','ngSanitize','LoginController','LandingPageController']);

app.config(['$routeProvider','$controllerProvider', function($routeProvider, $controllerProvider) {
    //$controllerProvider.allowGlobals();
    $routeProvider.
        when('/login', {
            templateUrl: 'app/login/loginPage.html',
            controller: 'loginCtrl'
        }).
        when('/landingPage', {
            templateUrl: 'app/landingPage/landingPage.html',
            controller: 'landingPageCtrl'
        }).
        otherwise({redirectTo: '/login'});
}]);
