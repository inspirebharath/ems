admin_app.controller('manageLeftMenuBtns', function($scope, $http, $location) {
	$scope.navToManageEvents = function() {
		$location.url("/manageEvents");
	};
	$scope.navToManageAdmin = function() {

	};
	$scope.getClass = function(path, parentMenu) {
		var locationPath = "";
		var newClass = "";
		if (parentMenu === true) {
			locationPath = $location.$$path;
		} else {
			locationPath = $location.path().substr(0, path.length);
		}
		if (path === "manageEAP") {
			if (locationPath === "/manageEvents"
					|| locationPath === "/manageAdmin") {
				newClass = 'cutstomActive';
			} else {
				newClass = "";
			}
		} else if (locationPath === path) {
			newClass = 'active';
		} else {
			newClass = "";
		}
		return newClass;
	};
});