admin_app.controller("dashboardController", [ '$scope', '$http', '$location', 'webServiceUrl', function($scope, $http, $location, webServiceUrl){
	$scope.pageHits = sessionStorage.getItem("PageHit_count");
	console.log($scope.pageHits);
     $scope.dashboardData = function(){
                var reqObj = {};
                var reqType = "GET";
                reqObj.url = webServiceUrl.pageHits;
                reqObj.reqType = reqType;
                $scope.dashboardDataServices(reqObj, function(responseType, response){
                console.log(responseType, response);
                        console.log(response);
            });
            };
            
        $scope.dashboardDataServices = function(reqObj, callback){
                $http({
                      url: reqObj.url,
                      headers: {'Content-Type':'application/json'},
                      method: reqObj.reqType,
                      dataType: 'json',
                      data: "",
                        }).then(function(response) {
    //						console.log(response);
                            callback("success", response);
                        }, function(error) {
                            console.log("response error:" + error);
                            callback("error", error);
                        });  
            };
}]);