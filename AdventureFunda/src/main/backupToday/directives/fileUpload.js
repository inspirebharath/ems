admin_app.directive('fileUpload', function ($http, webServiceUrl) {
    return {
        scope: true,        //create a new scope
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
            	
            	
            	
            	scope.input = {};
            	scope.input = event.target.dataset;
            	var imageTypeTemp = scope.input['imagetype'];
            	
            	scope.$parent.$parent[imageTypeTemp] = event.target.files;
//            	scope.$parent.partnerPhotoFile = scope.partnerPhotoFile;
//            	scope.$parent.orgLogoFile = scope.orgLogoFile;
            	
            	scope.model = {"entityType": scope.input['entitytype'],
            			"entityOperation": scope.input['entityoperation'],
            			"imageType" : scope.input['imagetype']
        				};
            	
                var files = event.target.files;
                //iterate files since 'multiple' may be specified on the element
                //for (var i = 0;i<files.length;i++) {
                    //emit event upward
                    //scope.$emit("fileSelected", { file: files[i] });
                	$http({
                	    method: 'POST',
                	    url: webServiceUrl.uploadFiles,
                	    headers: {'Content-Type': undefined },
                	    transformRequest: function (data) {
                	        var formData = new FormData();

                	        formData.append('fileDto', new Blob([angular.toJson(data.model)], {
                	            type: "application/json"
                	        }));
                	        
                	        for (var i = 0;i<data.file.length;i++) {
                	        	formData.append("files", data.file[i]);
                	        }
                	        return formData;
                	    },
                	    data: { model: scope.model, file: files}

                	}).
                	success(function (data, status, headers, config) {
                	});
                //}                                       
            });
        }
    };
});

/*admin_app.directive("fileUpload", function($http, webServiceUrl){
   
    return{
        scope: {
            fileUpload: "="
        },
        link: function (scope, element, attributes) {
        	scope.entityType = "partner"
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result;
                    });
                }
                reader.readAsDataURL(changeEvent.target.files);
            	var files = changeEvent.target.files;
                
            	var fileDto = {"uploadFor" : "passport-photocopy", "entityType": scope.entityType};
            	
            	var formData = new FormData();
            	
            	formData.append("file", files);
            	
            	formData.append("fileDto", fileDto);
            	
            	$http.post(webServiceUrl.uploadFiles, formData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                .success(function(){
                	console.log(response);
                })
                .error(function(){
                	console.log(response);
                });
            	
                $http({
                    url: webServiceUrl.uploadFiles,
                    headers: {'Content-Type':undefined},
                    method: "POST",
                    dataType: 'json',
                    data: {fileDto: fileDto},
                    transformRequest: function (data) {
                      var formData = new FormData();

                      formData.append("file", data.file);
                      formData.append('fileDto', new Blob([angular.toJson(data.fileDto)], {
                          type: "application/json"
                      }));
                      formData.append('entityType', new Blob([angular.toJson(data.entityType)], {
                          type: "plain/text"
                      }));
                      formData.append("uploadFor", data.uploadFor);
                      formData.append("entityType", data.entityType);
                      
                      return formData;
                    },
                      }).then(function(response) {
  						console.log(response);
  //						$scope.adminData = response.data.messageDesc;
  //						$scope.safeApply();
//                          callback("success", response);
                          //	             return response;
                      }, function(error) {
                          console.log("response error:",  error);
//                          callback("error", error);
                          //	              return error;
                      }); 
            });
        }
    }
});*/