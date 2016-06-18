admin_app.directive("setRoles", function(adminPortalDataService){
	return{
		restrict:"A",
		link:function(){
			$scope.isAdminMenuShow = false;
			$scope.isPartnerMenuShow = false;
			var userType = JSON.parse(sessionStorage.getItem("login_details")).userType;
			console.log(userType);
			if(userType == "SUPER_ADMIN"){
				$scope.isAdminMenuShow = true;
				$scope.isPartnerMenuShow = true;
			} else if(userType == "ADMIN"){
				$scope.isAdminMenuShow = false;
				$scope.isPartnerMenuShow = true;
			} else if(userType == "PARTNER"){
				$scope.isAdminMenuShow = false;
				$scope.isPartnerMenuShow = false;
			}
		}
	};
});