admin_app.controller("eventsSrchController", ['$scope', '$http', '$location','$translate','adminPortalDataService', 'webServiceUrl', function($scope, $http, $location, $translate, adminPortalDataService, webServiceUrl) {
	
	$scope.totalUsers = 0;
	$scope.usersPerPage = 0;
	$scope.eventPageSize = 2;
	$scope.pageIndex = 1;
	
    $scope.pageChanged = function(newPage) {
	    	console.log(newPage);
	    	$scope.pageIndex = newPage;
	    	$scope.searchEventsData("paging");	
	};
	
	$scope.searchEventsData = function(){
		
		var reqObj = {};
        var params = [];
            params = {
             "eventName": $scope.eventName,
             "eventLocation": $scope.eventLoc,
             "eventType":$scope.eventType,
             "eventCountry": $scope.eventCountry,
             "eventState": $scope.eventState,
             "eventCity": $scope.eventCity,
             "eventStatus": $scope.eventStatus,
             "eventOrganizersDTO": {}
            };
        var url = webServiceUrl.searchEvents;// URL TO BE CHECKED
        var reqType = "POST";
        reqObj.params = params;
        reqObj.url = url;
        reqObj.reqType = reqType;
		adminPortalDataService.ServiceCalls(reqObj, function(res, resData){
			console.log(res, resData);
			if(responseType == "success"){
            	var eventtInfoList = response.data.responseObj["admin-search-data"].adminInfoList;
            	$scope.noOfRecords = response.data.responseObj["admin-search-data"].paginationBean.noOfRecords;
                $scope.totalUsers = $scope.noOfRecords;
                $scope.usersPerPage = eventtInfoList.length;
                $scope.adminData = eventtInfoList;	
            }
		});
	};
	
	$scope.savePageIndex = function(){
        $scope.searchEventsData("paging");
    };

	$scope.safeApply = function(fn) {
		var phase = this.$root.$$phase;
		if (phase == '$apply' || phase == '$digest') {
			if (fn && (typeof (fn) === 'function')) {
				fn();
			}
		} else {
			this.$apply(fn);
		}
	};
	$scope.showEditDialog = function(id){
		console.log(id);
		$scope.adminItem = $scope.adminData.filter(function(elem){
			return elem.id == id;
		})
		console.log($scope.adminItem);
		
		$scope.UuserName = "sdfsdd";
		$scope.safeApply();
		$('#updateEventModal').modal('show');
	};
	$scope.showStatusDialog = function(){
		$("#statusChangeModal").modal('show');
	};
	$scope.showDeleteDialog = function(){
		$("#deleteEventModal").modal('show');
	};
	$scope.updateEventData = function(){
		
		$('#updateEventModal').modal('hide');
	};
}]);