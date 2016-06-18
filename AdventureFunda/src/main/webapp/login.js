
//var login = angular.module("loginModule", [ 'ngRoute', 'pascalprecht.translate']);
//login.config(['$translateProvider',
//function($translateProvider) {
//$translateProvider.translations('en', translationsEN);
//$translateProvider.preferredLanguage('en');

//}]);
//login.controller('loginController', [ '$scope', '$http', '$location',
//'$window', function($scope, $http, $location, $window) {
//$(document).ready(function(){
$(document).ajaxStart(function(){
	$("#wait").css("display", "block");
});
$(document).ajaxComplete(function(){
	$("#wait").css("display", "none");
});

function authenticateUser(){
	console.log("clicked");
	var serviceReq = {};
	serviceReq.url = "http://localhost:8090/AdventureFunda/ems/userLoginService/authenticateUser";
	serviceReq.type = 'POST';
	console.log("Location"+location.pathname);
	var userType = location.pathname.split("/");
	var userTypeTemp = userType[userType.length - 1]; 
	console.log(userTypeTemp);
	serviceReq.params = {
			"userName": $("#loginEmail").val(),
			"password": $("#loginPwd").val(),
			"emailId": "",
			"userType": userTypeTemp
	};
	loginAjaxcalls(serviceReq, function(result, resData){
		console.log(result, resData);
		sessionStorage.setItem("login_details",JSON.stringify(resData.responseObj["login-details"]));
		sessionStorage.setItem("PageHit_count",JSON.stringify(resData.responseObj["current-day-pagehits-count"]));
		
		window.location.href = "http://localhost:8090/AdventureFunda/index.html";	
//		window.location.href = "../index.html";	

	});

}
function loginAjaxcalls(serviceReq, callback) {
	$.ajax({
		url: serviceReq.url,
		headers: {'Content-Type':'application/json'},
		method: serviceReq.type,
		dataType: 'json',
		data: JSON.stringify(serviceReq.params)
	}).done(function(response) {
		console.log(response);
		$('.messageBlock').text(response.messageDesc);
		callback("success", response);
	}).fail(function(error) {
		console.log( "error" );
		callback("error", error);
	});
	/*.always(function(result) {
				console.log( "complete" );
				callback("complete", result);
			});*/

}
//});


//}
//]);