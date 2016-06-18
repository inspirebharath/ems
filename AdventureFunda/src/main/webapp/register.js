
//var login = angular.module("loginModule", [ 'ngRoute', 'pascalprecht.translate']);
//login.config(['$translateProvider',
//           		function($translateProvider) {
//	$translateProvider.translations('en', translationsEN);
//	$translateProvider.preferredLanguage('en');
//	
//}]);
//login.controller('loginController', [ '$scope', '$http', '$location',
//		'$window', function($scope, $http, $location, $window) {
//$(document).ready(function(){
	$(document).ajaxStart(function(){
	        $("#wait").css("display", "block");
	    });
	    $(document).ajaxComplete(function(){
	         $("#wait").css("display", "none");
	    });
	    
	function CallMe() {
		 
		
	    var serviceReq = {};
	    
	    serviceReq.url = "http://localhost:8080/AdventureFunda/ems/adminService/sadmin";
	    serviceReq.type = 'GET';
	    serviceReq.params = "";
	    loginAjaxcalls(serviceReq.url, serviceReq.params, serviceReq.type, function(result, resData){
	    	console.log(result, resData);
	    });
//		$.ajax({
//			  url: "http://localhost:8090/AdventureFunda/ems/adminService/sadmin",
//			  headers: {'Content-Type':'application/json'},
//			  method: 'GET',
//			  dataType: 'json',
//			}).done(function(response) {
//				console.log(response);
//				$('.messageBlock').text(response.messageDesc);
//			}).fail(function() {
//			    console.log( "error" );
//			}).always(function() {
//				console.log( "complete" );
//			});
	}
	function authenticateUser(){
		var serviceReq = {};
	    serviceReq.url = "http://localhost:8080/AdventureFunda/ems/adminService/regAdmin";
	    serviceReq.type = 'POST';
	    serviceReq.params = {
				 "userName": $("#userName").val(),
				 "password": $("#password").val(),
				 "emailId": $("#emailId").val(),
				 "adminType": "super_admin"
				};
	    loginAjaxcalls(serviceReq.url, serviceReq.params, serviceReq.type, function(result, resData){
	    	console.log(result, resData);
	    	window.location.href = "http://localhost:8080/AdventureFunda/login.html";
	    });
	    //window.location.href = "http://localhost:8080/AdventureEveryTimeA/login.html";	
//			$.ajax({
//				  url: "http://localhost:8090/AdventureFunda/ems/adminService/regAdmin",
//				  headers: {'Content-Type':'application/json'},
//				  method: 'GET',
//				  dataType: 'json',
//				  data:reqData
//				}).done(function(response) {
//					console.log(response);
//					$('.messageBlock').text(response.messageDesc);
//					window.location.href = "index.html"; 
//				}).fail(function() {
//				    console.log( "error" );
//				}).always(function() {
//					console.log( "complete" );
//				});
//			$http({
//	            url: "http://localhost:8090/AdventureFunda/ems/adminService/regAdmin",
//	            method: "POST",
//	            headers: {
//	            	   'Content-Type': 'application/json'
//	            	 },
//	           data:reqData
//	        })
//	                    .then(function (response) {
////	                        $activityIndicator.stopAnimating();
//	                      
//	                        console.log(response);
//	                    },
//	                        function (error) {
//	                    	console.log("Response " + error);
////	                            $activityIndicator.stopAnimating();
//	                        });
		}
		//CallMe();
		function loginAjaxcalls(serviceUrl, params, reqType, callback){
			$.ajax({
				  url: serviceUrl,
				  headers: {'Content-Type':'application/json'},
				  method: reqType,
				  dataType: 'json',
				  data:params
				}).done(function(response) {
					console.log(response);
					$('.messageBlock').text(response.messageDesc);
					callback("success", response);
				}).fail(function(error) {
				    console.log( "error" );
				    callback("error", error);
				});
//			.always(function(result) {
//					console.log( "complete" );
//					callback("complete", result);
//				});
			
		}
//});

		
//	}
//]);