var app = angular.module('coderApp', ['ngRoute','ngSanitize','ngTagsInput','ngScrollbar','checklist-model','LoginController','LandingPageController','WorkingPageController']);

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
        when('/workingPage', {
            templateUrl: 'app/workingPage/workingPage.html',
            controller: 'workingPageCtrl'
        }).
        otherwise({redirectTo: '/login'});
}]);

app.service('landingPageService', function() {
  var productList = [];

  var addProduct = function(newObj) {
      productList.push(newObj);
  };

  var getProducts = function(){
      return productList;
  };

  return {
    addProduct: addProduct,
    getProducts: getProducts
  };

});
