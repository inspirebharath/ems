admin_app.directive('leftMenu', function ($rootScope, $http, $location, $translate) {
    return {
        restrict: 'EA',
        templateUrl: 'views/leftMenu.html',
        scope: false,
        link:function($scope){
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
admin_app.controller('manageLeftMenuBtns', ['$scope', '$http', '$location','$translate','adminPortalDataService', function($scope, $http, $location, $translate, adminPortalDataService) {
	$scope.isCollapsed = false;
	$scope.isCollapsedSettings = false;
	$scope.showLeftMenu = true;
//	$scope.isAdminMenuShow = false;
//	$scope.isPartnerMenuShow = false;
//	var userType = JSON.parse(sessionStorage.getItem("login_details")).userType;
//	console.log(userType);
//	if(userType == "SUPER_ADMIN"){
//		$scope.isAdminMenuShow = true;
//		$scope.isPartnerMenuShow = true;
//	} else if(userType == "ADMIN"){
//		$scope.isAdminMenuShow = false;
//		$scope.isPartnerMenuShow = true;
//	} else if(userType == "PARTNER"){
//		$scope.isAdminMenuShow = false;
//		$scope.isPartnerMenuShow = false;
//	}
	if($location.$$path === "/login"){
		$scope.showLeftMenu = false;
	}
	$scope.navToManageEvents = function(pageFlag) {
		if(pageFlag == "search"){
			$location.url("/manageEventsSearch");
		}else if(pageFlag == "create"){
			$location.url("/manageEvents");
		}
			};
			$scope.navToManageAdmin = function(pageFlag) {
//				adminPortalDataService.setViewFlag(pageFlag);
				if(pageFlag == "search"){
					$location.url("/manageAdmin");
				} else if(pageFlag == "create"){
					$location.url("/manageAdminCreate");
				}
				
			};
            $scope.navToManagePartners = function(pageFlag) {
//				adminPortalDataService.setViewFlag(pageFlag);
				if(pageFlag == "search"){
					$location.url("/partnerSearch");
				} else if(pageFlag == "create"){
					$location.url("/managePartnerCreate");
				}
				
			};
			$scope.navToEventsGallery = function() {
				$location.url("/eventsGallery");
			};
//			$scope.getClass = function(path, parentMenu) {
//				var locationPath = "";
//				var newClass = "";
//				if (parentMenu === true) {
//					locationPath = $location.$$path;
//				} else {
//					locationPath = $location.path().substr(0, path.length);
//				}
//				if (path === "manageEAP") {
//					if (locationPath === "/manageEvents"
//							|| locationPath === "/manageAdmin") {
//						newClass = 'cutstomActive';
//					} else {
//						newClass = "";
//					}
//				} else if (locationPath === path) {
//					newClass = 'cutstomActive';
//				} else {
//					newClass = "";
//				}
//				return newClass;
//			};
			$(function() {

			    $('#side-menu').metisMenu();

			    $('#paypal-account').hide();
			    $('#no-paypal-account').hide();

			    $('#have-paypal-account').click( function () {
			            $('#paypal-account').show();
			            $('#no-paypal-account').hide();
			    });
			    
			    $('#dont-have-paypal-account').click( function () {
			            $('#paypal-account').hide();
			            $('#no-paypal-account').show();
			    });

			});

			//Loads the correct sidebar on window load,
			//collapses the sidebar on window resize.
			// Sets the min-height of #page-wrapper to window size
			$(function() {
			    $(window).bind("load resize", function() {
			        topOffset = 50;
			        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
			        if (width < 768) {
			            $('div.navbar-collapse').addClass('collapse');
			            topOffset = 100; // 2-row-menu
			        } else {
			            $('div.navbar-collapse').removeClass('collapse');
			        }

			        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
			        height = height - topOffset;
			        if (height < 1) height = 1;
			        if (height > topOffset) {
			            $("#page-wrapper").css("min-height", (height) + "px");
			        }
			    });

			    var url = window.location;
			    var element = $('ul.nav a').filter(function() {
			        return this.href == url || url.href.indexOf(this.href) == 0;
			    }).addClass('active').parent().parent().addClass('in').parent();
			    if (element.is('li')) {
			        element.addClass('active');
			    }
			});

}]);


