admin_app.controller('managePartnerController', [ '$scope', '$http', '$location', 'adminPortalDataService', 'webServiceUrl', 
		function($scope, $http, $location, adminPortalDataService, webServiceUrl) {

	$scope.userName = "";
	$scope.emailId = "";
	$scope.status = "";
	$scope.fName = "";
	$scope.lName = "";
	$scope.mobileNo = "";

	$scope.totalUsers = 0;
	$scope.usersPerPage = 0;
	$scope.PartnerPageSize = 10;
	$scope.pageIndex = 1;
    $scope.pageChanged = function(newPage) {
	    	console.log(newPage);
	    	$scope.pageIndex = newPage;
	    	$scope.searchPartnerData("paging");	
	};
	$scope.loadPartnerData = function() {
		$scope.PartnerData = {};
	};
	
            $scope.searchPartnerData = function(searchType){
                var reqObj = {};
                var params = [];
                params = setParams(searchType);
                    
            var url = webServiceUrl.searchPartner;
            var reqType = "POST";
            reqObj.params = params;
            reqObj.url = url;
            reqObj.reqType = reqType;    
            adminPortalDataService.ServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                var searchStr = "";
                if(responseType == "success"){
                	var PartnerInfoList = response.data.responseObj["partner-search-data"].partnerInfoList;
                	$scope.noOfRecords = response.data.responseObj["partner-search-data"].paginationBean.noOfRecords;
                    $scope.totalUsers = $scope.noOfRecords;
                    $scope.usersPerPage = PartnerInfoList.length;
                    $scope.PartnerData = PartnerInfoList;	
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
                                "pageSize":parseInt($scope.PartnerPageSize),
                                "pageIndex":$scope.pageIndex,
                                "noOfRecords":$scope.noOfRecords
                            }
                        } else if(searchType == "changePage"){
                                params.paginationBean = {
                                "pageSize":parseInt($scope.PartnerPageSize),
                                "pageIndex":$scope.pageIndex,
                                "noOfRecords":$scope.noOfRecords
                            }
                        }
                       return params;
            }
            $scope.editPartnerData = function(){
                
            };
            $scope.deletePartnerData = function(){
                
            };
            $scope.loadPartnerDataPages = function(){
                
            };
            $scope.savePageIndex = function(){
                $scope.searchPartnerData("paging");
            };
            
//            $scope.savePageIndex = function(){
//                $scope.searchPartnerData("changePage");
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
				$scope.PartnerItem = angular.copy($scope.PartnerData.filter(function(elem){
					return elem.id == id;
				}));
				console.log($scope.PartnerItem);
				
				$scope.UuserName = "sdfsdd";
				$scope.safeApply();
				$('#updatePartnerModal').modal('show');
			};
			$scope.showStatusDialog = function(){
				$("#statusChangeModal").modal('show');
			};
			$scope.showDeleteDialog = function(){
				$("#deletePartnerModal").modal('show');
			};
			$scope.updatePartnerData = function(){
				var reqObj = {};
				var url = webServiceUrl.partnerUpdate;
                var reqType = "POST";
                reqObj.params = $scope.PartnerItem;
                reqObj.url = url;
                reqObj.reqType = reqType;
                adminPortalDataService.ServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                        console.log(response);
//						$scope.adminData = response.data.messageDesc;
//						$scope.safeApply();
            });
				$('#updatePartnerModal').modal('hide');
			};
			$scope.deletePartner = function(){
				var reqObj = {};
				var url = webServiceUrl.deletePartner;
                var reqType = "POST";
                reqObj.params = $scope.PartnerItem.id;
                reqObj.url = url;
                reqObj.reqType = reqType;
                adminPortalDataService.ServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                        console.log(response);
//						$scope.adminData = response.data.messageDesc;
//						$scope.safeApply();
            });
			};
}]);