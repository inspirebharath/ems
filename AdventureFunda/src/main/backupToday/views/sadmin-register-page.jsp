<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="baseUrl">${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/</c:set> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Sadmin Registeration Page</title>
	<link rel="stylesheet" href="../assets/libs/Bootstrap/bootstrap.min.css" />
	<style>
		.overlay {
			/*background-image:url('assets/images/bg.jpg');
				background-repeat:repeat;*/
			background-color: #ccc;
			display: none;
			z-index: 500;
			position: absolute;
			top: 0px;
			left: 0px;
			filter: alpha(opacity = 50);
			-moz-opacity: 0.5;
			-khtml-opacity: 0.5;
			opacity: 0.5;
			width: 100%;
			height: 100%;
		}
		
		.messageBlock {
			text-align: center;
			padding-bottom: 8px;
			font-size: 16px;
		}
	</style>
</head>
<body>
	<div class="container" >
		<div id="wait" class="overlay">
			<div style="width: 70px; height: 90px; position: absolute; top: 50%; left: 50%; padding: 2px;">
				<img src='../assets/images/loader.gif' width="64" height="64" /><!-- <br>Loading.... Please Wait.... -->
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12 col-md-offset-2">
				<div class="form-horizontal">
					<div class="get-in-touch">
						<h3 class="text-center" >Sadmin Registeration</h3>
						<hr />
						<!--Success and Failure Message Start-->
						<div class="col-md-12">
							<div data-msg-code='${response.messageCode}' id="response-code" >
								<c:choose>
									<c:when test="${response.responseType == 'success' && response.messageCode == 'EAS01'}">
										<span class="messageBlock">${response.messageDesc}</span>					
									</c:when>
									<c:when test="${response.responseType == 'error'}">
										<span class="messageBlock">${response.messageDesc}</span>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!--Success and Failure Message End-->
						<div class="form-group">
							<label class="col-sm-2 control-label" >User Name</label>
							<div class="col-sm-4">
								<input type="text" id="userName" class="form-control" placeholder="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" >Email Id</label>
							<div class="col-sm-4">
								<input type="text" id="emailId" class="form-control" placeholder="someone@example.com" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" >Password</label>
							<div class="col-sm-4">
								<input type="password" id="password" class="form-control" placeholder="**************" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Confirm Password</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" placeholder="**************" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10 registerBtnRight">
								<button onclick="registerSuperAdmin()" class="btn btn-danger btn-sm " >Register</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../assets/libs/JQuery/jquery.min.js"></script>
	<script src="../assets/libs/Bootstrap/bootstrap.min.js"></script>
	<script src="../register.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			if($('#response-code').attr('data-msg-code') == 'EAS01') {
				$('.form-horizontal').find('input').attr('disabled','true');
			}
			
		});

		$(document).ajaxStart(function(){
	        $("#wait").css("display", "block");
	    });
		
	    $(document).ajaxComplete(function(){
	         $("#wait").css("display", "none");
	    });
	
	    var baseUrl = "${baseUrl}";
	    var serviceUrl = "ems/adminService/";
	
	    var serviceReq = {};
	    
	    function registerSuperAdmin() {
	    	serviceReq.url = baseUrl + serviceUrl + "regAdmin";
		    serviceReq.type = 'POST';
		    serviceReq.params = {
					 "userName": $('#userName').val().trim(),
					 "password": $('#password').val().trim(),
					 "emailId": $('#emailId').val().trim(),
					 "adminType": "super_admin"
					};
		    loginAjaxcalls(serviceReq, function(result, resData){
		    	console.log(result, resData);
		    	window.location.href = "admin";
		    });
	    }
		
		function loginAjaxcalls(serviceReq, callback){
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
	</script>
</body>
</html>