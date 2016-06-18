function fileUploadCtrl($scope, $http, webServiceUrl) {
console.log($scope);
    //a simple model to bind to and send to the server
    $scope.model = {"uploadFor" : "passport-photocopy",
    				"entityType": $scope.entityType};

    //an array of files selected
    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {  
        	
            //add the file object to the scope's files collection
            $scope.files.push(args.file);
            $scope.save();
        });
    });
    
    //the save method
    $scope.save = function() {
        $http({
            method: 'POST',
            url: webServiceUrl.uploadFiles,
            headers: { 'Content-Type': undefined },
            //This method will allow us to change how the data is sent up to the server
            // for which we'll need to encapsulate the model data in 'FormData'
            transformRequest: function (data) {
                var formData = new FormData();
                //need to convert our json object to a string version of json otherwise
                // the browser will do a 'toString()' on the object which will result 
                // in the value '[Object object]' on the server.
                formData.append("fileDto", angular.toJson(data.model));
                //now add all of the assigned files
                for (var i = 0; i < data.files; i++) {
                    //add each file to the form data and iteratively name them
                    formData.append("file" + i, data.files[i]);
                }
                return formData;
            },
            //Create an object that contains the model and files which will be transformed
            // in the above transformRequest method
            data: { model: $scope.model, files: $scope.files }
        }).
        success(function (data, status, headers, config) {
            alert("success!");
        }).
        error(function (data, status, headers, config) {
            alert("failed!");
        });
    };
};