admin_app.controller('userProfileController', function($scope, webServiceUrl, $http, messages){
	$scope.isPartner = false;
	$scope.isAdmin = false;
	var url = "";
	var params = [];
	var loginData = JSON.parse(sessionStorage.getItem("login_details"));
	$scope.adminUserName = loginData.userName; 
	$scope.saveProfileServiceCalls = function(reqObj, callback){
        $http({
              url: reqObj.url,
              headers: {'Content-Type':'application/json'},
              method: reqObj.reqType,
              dataType: 'json',
             data: JSON.stringify(reqObj.params)
			}).then(function(response) {
//				console.log(response);
//				$scope.adminData = response.data.messageDesc;
//				$scope.safeApply();
                callback("success", response);
				//	             return response;
			}, function(error) {
				console.log("response error:" + error);
                callback("error", error);
				//	              return error;
			});  
    };
    
	if(loginData.userType == "SUPER_ADMIN" || userType == "ADMIN"){
		$scope.isPartner = false;
		$scope.isAdmin = true;
		var reqObj = {};
        reqObj.url = webServiceUrl.getadminData + loginData.id;
        reqObj.reqType = "GET";
        reqObj.params = [];
        $scope.saveProfileServiceCalls(reqObj, function(responseType, response){
        	console.log(responseType, response);
        	var data = response.data.responseObj["specific-admin-data"];
        	$scope.adminEmailId = data.emailId;
        	$scope.adminFirstName = data.firstName;
        	$scope.adminLastName = data.lastName;
        	$scope.adminMobileNo = data.mobileNo;
        });
	} else if(loginData.userType == "PARTNER"){
		$scope.isPartner = true;
		$scope.isAdmin = false;
		var reqObj = {};
        reqObj.url = webServiceUrl.getadminData + loginData.id;
        reqObj.reqType = "GET";
        $scope.saveProfileServiceCalls(reqObj, function(responseType, response){
        	console.log(responseType, response);
        });
	}
	
     $scope.saveProfileData = function(){
                var reqObj = {};
                var reqType = "POST";
                if(loginData.userType == "SUPER_ADMIN" || userType == "ADMIN"){
                	params = {
                			 "id":  loginData.id,
                	         "userName": $scope.adminUserName,
                	         "emailId": $scope.adminEmailId,
                	         "firstName":$scope.adminFirstName,
                	         "lastName": $scope.adminLastName,
                	         "mobileNo": $scope.adminMobileNo,
                	        };
                			url = webServiceUrl.adminUpdate;
                			
                } else if(loginData.userType == "PARTNER"){
                	url = webServiceUrl.partnerUpdate;
                    params = {
                     "userName": $scope.adminUserName, 
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
                     "successStory": $scope.partnerSuccessStry
                    };
                }
                reqObj.params = params;
                reqObj.url = url;
                reqObj.reqType = reqType;
                $scope.saveProfileServiceCalls(reqObj, function(responseType, response){
                console.log(responseType, response);
                        console.log(response);
                        alert(messages.profileUpdate);
//						$scope.adminData = response.data.messageDesc;
//						$scope.safeApply();
            });
            };
            
        
});