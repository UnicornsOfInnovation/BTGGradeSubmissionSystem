var app = angular.module("strandApp",[]);

app.controller('strandController', function($scope, $http, $httpParamSerializer) {
	console.log("strandController " + "start");
	
	$scope.insertStrand = function(){
		var confirmation = window.confirm("Are you sure you want to add this strand?");
		if (true == confirmation) {	
				var strand = {
						strandName: $scope.strandName,
						strandCode: $scope.strandCode
				}
				
				$http.post("/Strand", $httpParamSerializer(strand),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				
				$scope.strandName="";
				$scope.strandCode="";
		}
	}
});