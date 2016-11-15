var app = angular.module('loginApp',[]);
app.controller('loginController', function($scope, $http, $location) {
	
	$scope.login=function(){
		var loginCredentials = {
				username : $scope.username,
				password : $scope.password
				
		}
		$http.post('/Login', jason).success(function(data){
			if(data.pageToRedirect == "admin"){
				$location.path("admin");
				var adminRedirect = document.createElement("form");
				adminRedirect.setAttribute("method", "post");
				adminRedirect.setAttribute("action", "AdminPage");
				adminRedirect.submit();
			}
			else if(data.pageToRedirect == "company"){
				var companyRedirect = document.createElement("form");
				companyRedirect.setAttribute("method", "post");
				companyRedirect.setAttribute("action", "CompanyPage");
				companyRedirect.submit();
			}
			else if(data.pageToRedirect == ""){
				alert(data.errorMsg);
			}
		});
	}// End of login
	
	$scope.login();
});
