angular.module('AdminPageController', ['ngAnimate'])
    .controller("adminPageCtrl",["$scope","$location","$http", function($scope,$location,$http){
    	$scope.userList = [
		   {id:1, Name:"Product A",Price:"123", Quantity: 10},
		   {id:2, Name:"Product C",Price:"173", Quantity: 20},
		   {id:3, Name:"Product B",Price:"85", Quantity: 45},
		   {id:4, Name:"Product K",Price:"18", Quantity: 7},
		   {id:5, Name:"Product Q",Price:"73", Quantity: 47},
		   {id:6, Name:"Product X",Price:"5", Quantity: 34},
		   {id:7, Name:"Product T",Price:"192", Quantity: 99},
		   {id:8, Name:"Product K",Price:"103", Quantity: 57},
		   {id:9, Name:"Product L",Price:"82", Quantity: 84},
		   {id:10, Name:"Product N",Price:"47", Quantity: 62},
		   {id:11, Name:"Product Y",Price:"66", Quantity: 72},
		   {id:12, Name:"Product F",Price:"113", Quantity: 37}
		 ];

		  $scope.totalItems = 164;
		  $scope.currentPage = 1;

		  $scope.setPage = function (pageNo) {
		    $scope.currentPage = pageNo;
		  };

		  $scope.pageChanged = function() {
		    console.log('Page changed to: ' + $scope.currentPage);
		  };

		  $scope.maxSize = 5;
		  $scope.bigTotalItems = 175;
		  $scope.currentPage = 1;
     }]);