admin_app.controller("ErrorPopupController", ['$scope', 'adminPortalDataService', function($scope, adminPortalDataService){
	adminPortalDataService.setErrorObjContext($scope);
}]);