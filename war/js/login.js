var app = angular.module('loginApp',['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/accountScreen', {
        templateUrl: '/html/templates/account.html',
        controller: 'accountController'
      })
      .when('/courseScreen', {
        templateUrl: '/html/templates/course.html',
        controller: 'courseController'
      })
      .when('/classListScreen', {
        templateUrl: '/html/templates/classList.html',
        controller: 'classListController'
      })
      .when('/bestStudentScreen', {
        templateUrl: '/html/templates/bestStudent.html',
        controller: 'bestStudentController'
      })
      .otherwise({
    	  templateUrl: '/html/templates/account.html',
          controller: 'accountController'
      });
  }]);

app.controller('loginController', function($scope, $http, $location, $httpParamSerializer, serviceShareData) {
	$scope.checkAccess = function(){
		if(false!=serviceShareData.isLogged()){
			serviceShareData.redirectToType();
		}
	}
	
	$scope.login = function(){
		if(false!=serviceShareData.isLogged()){
			serviceShareData.redirectToType();
			return true;
		}
		var Redirect = document.createElement("form");
		document.body.appendChild(Redirect);
		if($scope.username == null || $scope.password == null){
			alert("Please supply username or password.");
		} else if($scope.username == "admin" && $scope.password == "admin"){
			serviceShareData.addData("admin","admin");
			Redirect.setAttribute("method", "post");
			Redirect.setAttribute("action", "adminPage");
			Redirect.submit();
		} else {
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
				if(response.data.accountStatus!=false){
					console.log(" STATUS "+response.data.accountStatus);
					if(response.data.accountLoggedIn == "student"){
						$location.path("admin");
						console.log("NO error List");
	//					var Redirect = document.createElement("form");
						Redirect.setAttribute("method", "post");
						Redirect.setAttribute("accountID", response.data.accountID);
						serviceShareData.addData(response.data.accountID, "student");
						//serviceShareData.addData1("student");
						console.log("session->"+serviceShareData.getData());
						Redirect.setAttribute("action", "studentPage");
						Redirect.submit();
					}
					else if(response.data.accountLoggedIn == "teacher"){
	//					var Redirect = document.createElement("form");
						console.log("NO error List");
						Redirect.setAttribute("method", "post");
						Redirect.setAttribute("accountID", response.data.accountID);
						serviceShareData.addData(response.data.accountID, "teacher");
						//serviceShareData.addData2("teacher");
						Redirect.setAttribute("action", "teacherPage");
						Redirect.submit();
					}
					else if(response.data.accountLoggedIn == ""){
						alert(response.data.errorList);
						console.log("NO error List");
					} 
				}else{
					alert("Account has been deactivated");
				}
			}, function() {
				alert("Wrong username and/or password! Try again.");
				$scope.username = null;
				$scope.password = null;
			});
			
		}
		
		
	}// End of login
	$scope.checkAccess();
});












