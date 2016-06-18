admin_app.constant('messages',(function(){
	return {
		loginError:"Wrong username and password.",
		serviceError:"Server error occured, please try again.",
		noResults:"No results found.",
		addTQsuccess:"Component added to quote succesfully.",
		deleteConfirm: "Are you sure you want to delete this component?",
		deleteCompSuccess: "Component Deleted Successfully",
		deleteComFail: "Deletion Failed",
		updateQtySuccess: "Updated Successfully",
		updateQtyFail: "Updation Failed",
		APIError: "Something went wrong, please refresh",
		quoteSubmitSuccess: "Quote Submitted Successfully",
		quoteSubmitFail: "Error in Quote Submission",
		quoteOnHold: "Quote creation is in progress, kindly submit again.",
		deleteQuoteConfirm: "Are you sure you wanna delete this quote?",
		tryAgain: "Please try again!",
		notFound: "No Results Found!",
		profileUpdate: "Profile updated successfully!"
	};

})());


admin_app.constant('webServiceUrl', (function() {
	var deployment_mode = "Development"; //Configuration-options : "Development","Production"
	// var baseUrl = "http://192.168.0.104:8090/AdventureFunda/ems";
	var baseUrl = "http://localhost:8090/AdventureFunda/ems";

//	if(devployment_mode == "Development")
//	baseUrl = 'http://10.193.16.26:81/Private';
//	else if(devployment_mode == "Stage")
//	baseUrl = 'https://packmanagerstg.stg.cardinalhealth.com';
//	else if(devployment_mode == "Production"){}
	//baseUrl = 'https://packmanagerstg.stg.cardinalhealth.com';

	// Use the variable in your constants
//	+"/searchAdmins"
	return {
		baseUrl:baseUrl,
		searchAdmin:baseUrl +"/adminService/searchAdmins",
		regAdmin:baseUrl+"/adminService/regAdmin",
		getadminData:baseUrl+ "/adminService/getAdminInfo/",
		adminUpdate:baseUrl + "/adminService/updateAdminInfo/",
		searchAdminServices: baseUrl +"/adminService/searchAdmins",
		regPartner: baseUrl + "/partnerService/regPartner",
		partnerUpdate: baseUrl + "/partnerService/updatePartnerInfo/",
		RegisterUser: "https://packmanagerstg.stg.cardinalhealth.com/login/RegisterUser",
		pageHits: baseUrl+"/applicationService/updatePageHits",
		GetAllFacilities: baseUrl + "/Mlogin/GetAllUserFacilities",
		uploadFiles:baseUrl + "/fileService/uploadFiles",
		createEvents:baseUrl + "/eventService/regEvent",
		searchEvents:baseUrl+"/eventService/searchEvents"
	};
})());