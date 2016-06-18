admin_app.directive('fileUpload', function ($http, webServiceUrl) {
    return {
        scope: true,
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
            	scope.input = {};
            	scope.input = event.target.dataset;
            	var imageTypeTemp = scope.input['imagetype'];
            	console.log(scope.input);
            	
            	scope.$parent.$parent[imageTypeTemp] = event.target.files;
            	scope.model = {"entityType": scope.input['entitytype'],
            			"entityOperation": scope.input['entityoperation'],
            			"imageType" : scope.input['imagetype']
        				};
            	
            	var files = event.target.files;
            	imageTumbnail("assets/images/spinner-small.gif", imageTypeTemp);
            	submitFiles(webServiceUrl.uploadFiles, scope.model, files, imageTypeTemp);
//                	$http({
//                	    method: 'POST',
//                	    url: webServiceUrl.uploadFiles,
//                	    headers: {'Content-Type': undefined },
//                	    transformRequest: function (data) {
//                	        var formData = new FormData();
//
//                	        formData.append('fileDto', new Blob([angular.toJson(data.model)], {
//                	            type: "application/json"
//                	        }));
//                	        
//                	        for (var i = 0;i<data.file.length;i++) {
//                	        	formData.append("files", data.file[i]);
//                	        }
//                	        return formData;
//                	    },
//                	    data: { model: scope.model, file: files}
//
//                	}).
//                	success(function (data, status, headers, config) {
//                        var reader = new FileReader();
//                        reader.onload = function (e) {
//                        	imageTumbnail(e.target.result);
//                        	scope.$parent.$parent.$digest();
//                        }
//                        reader.readAsDataURL(files[0]);
//                	});
                //}                                       
            });
            
            function submitFiles(url, model, files, imageTypeTemp){
            	$http({
            	    method: 'POST',
            	    url: url,
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
            	    data: { model: model, file: files}

            	}).
            	success(function (data, status, headers, config) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                    	if(model.entityType == 'EVENTS' && model.imageType == 'SECONDARY_PHOTOS') {
                        		imageTumbnailEvents(e.target.result, imageTypeTemp)
                        } else {
                        	imageTumbnail(e.target.result, imageTypeTemp)
                        }
                    	
                    }
                    	for(var i=0;i<files.length;i++){
                    		reader.readAsDataURL(files[i]);
                    	}
            	});
            }
            function imageTumbnail(imagePath, imageTypeTemp){
            	if(imageTypeTemp === 'PASSPORT_PHOTOCOPIES'){
            		scope.$parent.$parent.passportFile = imagePath;
            	} else if(imageTypeTemp === 'INDIVIDUAL_PHOTOS'){
            		scope.$parent.$parent.idvPhoto = imagePath;
            	} else if(imageTypeTemp === 'ORGANIZATION_LOGOS'){
            		scope.$parent.$parent.Orglogo = imagePath;
            	}
            	scope.$parent.$parent.$digest();
            }
            
            function imageTumbnailEvents(imagePath, imageTypeTemp){
            	var imgObj = [];
            	if(imageTypeTemp === 'PRIMARY_PHOTO'){
            		scope.$parent.$parent.primaryImage = imagePath;
            	} else if(imageTypeTemp === 'SECONDARY_PHOTOS'){
            		imgObj.push(imagePath);
            		scope.$parent.$parent.secImages = imagePath;
            	}
            	scope.$parent.$parent.$digest();
            }
        }
    };
});
