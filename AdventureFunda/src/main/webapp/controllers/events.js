admin_app.controller('eventsController', [ '$scope', '$http', 'webServiceUrl', 'adminPortalDataService', function($scope, $http, webServiceUrl, adminPortalDataService) {
			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy',
					'shortDate' ];
			$scope.format = $scope.formats[0];

			$scope.status = {
				opened : false
			};
			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};
			
			$scope.getNewClass = function(pageFlag) {
				var setNewClass = "";
				if (pageFlag == 'create') {
					setNewClass = "active";
				} else {
					setNewClass = "";
				}
				return setNewClass;
			};
			$scope.togglePage = function(pageFlag) {
				// $scope.pageFlag = "create";
				$scope.pageFlag = pageFlag;
				// $scope.$apply();
				$scope.getNewClass(pageFlag);
			};

			$scope.togglePage('create');
//			 var reqObj = {};
//			 var createEventParams = [];
//			 createEventParams = {
//				  eventName:$scope.eventName,
//				  startDate : $scope.eventStartDate,
//				  endDate : $scope.eventEndDate,
//				  shortDesc:$scope.shortDesc,
//				  adminQuotedPrice : $scope.eventCost,
//				  maxNoOfParticipants : $scope.Max_No_part,
//				  minNoOfParticipants : $scope.Min_No_part,
//				  noOfBatches : $scope.NoOfBatches,
//				  difficultyGrade:$scope.diffGrade,
//				  fitnessLevel:$scope.fitnessLevel,
//				  eventType:$scope.eventType,
//				  country : $scope.eventCountry,
//				  state : $scope.eventState,
//				  city : $scope.eventCity,
//				  partnerQuotedPrice: $scope.eventCity,
//			  	  paymentLastDate: $scope.eventpaymentLastDate,
//			  	  medicalCertificateStatus: "",
//			  	  eventLocation: $scope.eventLocation,
//			  	  zipCode: $scope.eventLocZip,
//			};
//			  	
//			var url = webServiceUrl.createEvents;
//            var reqType = "POST";
//            reqObj.params = createEventParams;
//            reqObj.url = url;
//            reqObj.reqType = reqType;
//            adminPortalDataService.ServiceCalls(reqObj, function(res, resData){
//            	console.log(res, resData);
//            });
//			$scope.addNewEventField = function() {
//			};
			$scope.resetEventData = function() {
			};
            $scope.navToManageEvents = function(){
                $location.url("/manageEvents")
            };
			$scope.submitEventData = function() {
				 var reqObj = {};
				 var createEventParams = [];
				 createEventParams = {
					  eventName:$scope.eventName,
					  startDate : $scope.eventStartDate,
					  endDate : $scope.eventEndDate,
					  shortDesc:$scope.shortDesc,
					  adminQuotedPrice : $scope.eventCost,
					  maxNoOfParticipants : $scope.Max_No_part,
					  minNoOfParticipants : $scope.Min_No_part,
					  noOfBatches : $scope.NoOfBatches,
					  difficultyGrade:$scope.diffGrade,
					  fitnessLevel:$scope.fitnessLevel,
					  eventType:$scope.eventType,
					  country : $scope.eventCountry,
					  state : $scope.eventState,
					  city : $scope.eventCity,
					  partnerQuotedPrice: $scope.eventCity,
				  	  paymentLastDate: $scope.payLastDate,
				  	  medicalCertificateStatus: "",
				  	  eventLocation: $scope.eventLocation,
				  	  zipCode: $scope.eventLocZip,
				};
				  	
				var url = webServiceUrl.createEvents;
	            var reqType = "POST";
	            reqObj.params = createEventParams;
	            reqObj.url = url;
	            reqObj.reqType = reqType;
	            adminPortalDataService.ServiceCalls(reqObj, function(res, resData){
	            	console.log(res, resData);
	            });
			};
    
            $scope.createEventServiceCalls = function(reqObj, callback){
                $http({
                      url: reqObj.url,
                      headers: {'Content-Type':'application/json'},
                      method: reqObj.reqType,
                      dataType: 'json',
                     data: JSON.stringify(reqObj.params)
					}).then(function(response) {
//						console.log(response);
//						$scope.adminData = response.data.messageDesc;
//						$scope.safeApply();
                        callback("success", response);
						//	             return response;
					}, function(error) {
						console.log("response error:" + error);
                        callback("error", error);
						//	              return error;
					});  
            };

			// $http({
			// url: webServiceUrl.GetComponentImages,
			// method: "GET",
			// params: $scope.GetComponentsImageParams
			// })
			// .then(function (response) {
			// $activityIndicator.stopAnimating();
			// console.log("images response success: " +
			// JSON.stringify(response));
			// for (i = 0; i < $scope.watIfProductsForPack.length; i++) {
			// $scope.watIfProductsForPack[i].thumbnailImg = response.data[i];
			// }
			// console.log("wat if in components: " +
			// $scope.watIfProductsForPack);
			// },
			// function (error) {
			// console.log("images response error:" + error);
			// $activityIndicator.stopAnimating();
			// });
		} ]);