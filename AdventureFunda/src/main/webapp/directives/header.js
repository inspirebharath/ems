admin_app.directive('header', function ($location, $rootScope) {
    return {
        restrict: 'EA',
        templateUrl: 'views/header.html',
        link:function($scope){
        	$scope.isLogin = false;
        }
       };
});
admin_app.controller('headerController', function($scope, $location){
	$scope.userName = JSON.parse(sessionStorage.getItem("login_details")).userName;
	console.log($scope.userName)
    $scope.logOut = function(){
        
    };
    $scope.navSettings = function(){
        
    };
    $scope.navProfile = function(){
        $location.url("/userProfile");
    };
});