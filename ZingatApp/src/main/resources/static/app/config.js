var app = angular.module('coderApp', ['ngRoute','ngSanitize','ngTagsInput','ngScrollable','checklist-model','ui.bootstrap','LoginController','LandingPageController','PopUpPageController','WorkingPageController','RejectDocPageController','RejectCodePageController','ProfilePageController','AdminPageController']);

app.config(['$routeProvider','$controllerProvider', function($routeProvider, $controllerProvider) {
    //$controllerProvider.allowGlobals();
    localStorage.setItem("checkFiled","");
    $routeProvider.
        when('/login', {
            templateUrl: 'app/login/loginPage.html',
            controller: 'loginCtrl'
        }).
        when('/landingPage', {
            templateUrl: 'app/landingPage/landingPage.html',
            controller: 'landingPageCtrl'
        }).
        when('/workingPage', {
            templateUrl: 'app/workingPage/workingPage.html',
            controller: 'workingPageCtrl'
        }).
        when('/profilePage', {
            templateUrl: 'app/profilePage/profilePage.html',
            controller: 'profilePageCtrl'
        }).
        when('/adminPage', {
            templateUrl: 'app/adminPage/adminPage.html',
            controller: 'adminPageCtrl'
        }).
        otherwise({redirectTo: '/login'});
}]);

