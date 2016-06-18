admin_app.controller('manageAdminController', [ '$scope', '$http', '$location', 'adminPortalDataService', 'webServiceUrl', 
		function($scope, $http, $location, adminPortalDataService, webServiceUrl) {

	$scope.userName = "";
	$scope.emailId = "";
	$scope.status = "";
	$scope.fName = "";
	$scope.lName = "";
	$scope.mobileNo = "";

	$scope.totalUsers = 0;
	$scope.usersPerPage = 0;
	$scope.adminPageSize = 2;
	$scope.pageIndex = 1;
    $scope.pageChanged = function(newPage) {
	    	console.log(newPage);
	    	$scope.pageIndex = newPage;
	    	$scope.searchAdminData("paging");	
	};
	$scope.loadAdminData = function() {
		$scope.adminData = {};
	};
	
            $scope.searchAdminData = function(searchType){
                var reqObj = {};
                var params = [];
                params = setParams(searchType);
                    
            var url = webServiceUrl.searchAdmin;
            var reqType = "POST";
            reqObj.params = params;
            reqObj.url = url;
            reqObj.reqType = reqType;    
            adminPortalDataService.ServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                var searchStr = "";
                if(responseType == "success"){
                	var adminInfoList = response.data.responseObj["admin-search-data"].adminInfoList;
                	$scope.noOfRecords = response.data.responseObj["admin-search-data"].paginationBean.noOfRecords;
                    $scope.totalUsers = $scope.noOfRecords;
                    $scope.usersPerPage = adminInfoList.length;
                    $scope.adminData = adminInfoList;	
                }
            });
            
            };
            setParams = function(searchType){
                var params = [];
                 params = {
                             "userName": $scope.userName,
                             "emailId": $scope.emailId,
                             "status": $scope.status,
                             "firstName": $scope.fName,
                             "lastName": $scope.lName,
                             "mobileNo": $scope.mobileNo
                        };
                    
                        if(searchType == "paging"){
                                params.paginationBean = {
                                "pageSize":parseInt($scope.adminPageSize),
                                "pageIndex":$scope.pageIndex,
                                "noOfRecords":$scope.noOfRecords
                            }
                        } else if(searchType == "changePage"){
                                params.paginationBean = {
                                "pageSize":parseInt($scope.adminPageSize),
                                "pageIndex":$scope.pageIndex,
                                "noOfRecords":$scope.noOfRecords
                            }
                        }
                       return params;
            }
            $scope.editAdminData = function(){
                
            };
            $scope.deleteAdminData = function(){
                
            };
            $scope.loadAdminDataPages = function(){
                
            };
            $scope.savePageIndex = function(){
                $scope.searchAdminData("paging");
            };
//            $scope.savePageIndex = function(){
//                $scope.searchAdminData("changePage");
//            };

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
				$('#updateAdminModal').modal('show');
			};
			$scope.showStatusDialog = function(){
				$("#statusChangeModal").modal('show');
			};
			$scope.showDeleteDialog = function(){
				$("#deleteAdminModal").modal('show');
			};
			$scope.updateAdminData = function(){
				
				$('#updateAdminModal').modal('hide');
			};
}]);