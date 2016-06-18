admin_app.controller('partnerCreateController', [ '$scope', '$http', '$location',
		'adminPortalDataService', 'webServiceUrl',  
		function($scope, $http, $location, adminPortalDataService, webServiceUrl) {
		$scope.entityType = "partner";
		$scope.filePath = '';
        $scope.createPartners = function(){
//            $scope.fd.append()
//               $scope.fd = file;
//               $scope.fd.append('file', file);
		
			var isPassportUploaded = (($scope['PASSPORT_PHOTOCOPIES'] && $scope['PASSPORT_PHOTOCOPIES'].length > 0)?'1':'0').toString();
	        var isPhotoUploaded = (($scope['INDIVIDUAL_PHOTOS'] && $scope['INDIVIDUAL_PHOTOS'].length > 0)?'1':'0').toString();
	        var isLogoUploaded = (($scope['ORGANIZATION_LOGOS'] && $scope['ORGANIZATION_LOGOS'].length > 0)?'1':'0').toString();
	        var fileUploadFlag = isPassportUploaded + isPhotoUploaded + isLogoUploaded;
//	        console.log(checkFileUpload);
        
                var reqObj = {};
                var params = [];
                
                    params = {
                     "userName": $scope.partnerUserName,
                     "emailId": $scope.partnerEmailId,
                     "firstName":$scope.partnerFirstName,
                     "lastName": $scope.partnerLastName,
                     "mobileNo": $scope.partnerMobileNo,
                     "alternativeNo": $scope.partnerAlterNo,
                     "gender": $scope.partnerGender,
                     "dob": $scope.partnerDOB,
                     "country": $scope.partnerCountry,
                     "state": $scope.partnerState,
                     "city": $scope.partnerCity,
                     "partnerType": $scope.partnerType,
                     "organizationName": $scope.partnerOrgName,
                     "merchantDesc": $scope.partnerMerchantDesc,
                     "websiteAddress": $scope.partnerWebsite,
                     "mountainsClimbed": $scope.partnerMountains,
                     "leadersDescription": $scope.partnerLeaders,
                     "facilityProvided": $scope.partnerFacilities,
                     "successStory": $scope.partnerSuccessStry,
                     "uploadedFiles":fileUploadFlag
                    };
                var url = webServiceUrl.regPartner;
                var reqType = "POST";
                reqObj.params = params;
                reqObj.url = url;
                reqObj.reqType = reqType;
                $scope.partnerSearchServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                        console.log(response);
//						$scope.adminData = response.data.messageDesc;
//						$scope.safeApply();
            });
            };
            /*$scope.$watch(
            		"passportFile",
            		function handleFooChange( newValue, oldValue ) {
            		 
            		console.log( "vm.fooCount:", newValue );
                    
//                	console.log(element);
                         
            		 
            		}
            		);*/     

            $scope.partnerSearchServiceCalls = function(reqObj, callback){
                $http({
                      url: reqObj.url,
                      headers: {'Content-Type':'application/json'},
                      method: reqObj.reqType,
                      dataType: 'json',
//                      contentType:"application/json",
                      data: JSON.stringify(reqObj.params),
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
            }
            $scope.uploadFile = function(){
//               var file = $scope.myFile;
//               console.log('file is ' );
//               console.dir(file);
//               $scope.fd = new FormData();
//               $scope.fd.append('file', file);
            };
              
            
}]);
//admin_app.directive('fileModel', ['$parse', function ($parse) {
//            return {
//               restrict: 'A',
//               link: function(scope, element, attrs) {
//                  var model = $parse(attrs.fileModel);
//                  var modelSetter = model.assign;
//                  
//                  element.bind('change', function(){
//                     scope.$apply(function(){
//                        modelSetter(scope, element[0].files[0]);
//                     });
//                  });
//               }
//            };
//         }]);
// admin_app.service('fileUpload', ['$http', function ($http) {
//            this.uploadFileToUrl = function(file, uploadUrl){
//               var fd = new FormData();
//               fd.append('file', file);
//                return fd;
//
//            }
//         }]);