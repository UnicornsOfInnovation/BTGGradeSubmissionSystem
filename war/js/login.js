var app = angular.module('loginApp',[]);

app.controller('loginController', function($scope, $http, $location) {
	
	$scope.login = function(){
		console.log("Login START");
		var loginCredentials = {
				username : $scope.username,
				password : $scope.password
				
		}
		$http.post('/Login', loginCredentials).success(function(response){
			if(response.accountLoggedIn.userType == "student"){
				$location.path("admin");
				var Redirect = document.createElement("form");
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("action", "studentPage");
				Redirect.submit();
			}
			else if(response.accountLoggedIn.userType == "teacher"){
				var Redirect = document.createElement("form");
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("action", "teacherPage");
				Redirect.submit();
			}
			else if(response.accountLoggedIn.pageToRedirect == ""){
				alert(response.errorList);
				console.log("NO error List");
			}
		});
	}// End of login
	
	$scope.login();
});
