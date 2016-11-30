var app = angular.module('loginApp',[]);

app.controller('loginController', function($scope, $http, $location, $httpParamSerializer, serviceShareData) {
	
	$scope.login = function(){
		var loginCredentials = {
				username : $scope.username,
				password : $scope.password
				
		}
		$http.post("/Login",  $httpParamSerializer(loginCredentials),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			console.log(" --> "+response.data.accountLoggedIn);

			if(response.data.accountLoggedIn == "student"){
				$location.path("admin");
				console.log("NO error List");
				var Redirect = document.createElement("form");
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("accountID", response.data.accountID);
				serviceShareData.addData(response.data.accountID);
				console.log("session->"+serviceShareData.getData());
				Redirect.setAttribute("action", "studentPage");
				Redirect.submit();
			}
			else if(response.data.accountLoggedIn == "teacher"){
				var Redirect = document.createElement("form");
				console.log("NO error List");
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("accountID", response.data.accountID);
				serviceShareData.addData(response.data.accountID);
				Redirect.setAttribute("action", "teacherPage");
				Redirect.submit();
			}
			else if(response.data.accountLoggedIn == ""){
				alert(response.data.errorList);
				console.log("NO error List");
			}
			
		});
	}// End of login

});












