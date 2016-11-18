/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Rococo Global Technologies, Inc - All Rights Reserved 2016
 * --------------------------------------------------------------------------- */
 
/*
* This file contains the backend process for the web page.
* The holds the values/properties that is common to the application.
* @author Lehmar Cabrillos
 * @version 0.01
 * Version History
 * [04/13/2016] 0.01 �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ Lehmar Cabrillos  �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｽ�ｿｽ�ｽｽ�ｽｽ Initial codes.
 */


/**
 * The angular module object.
 * @param pizzaTimeApp - the application name (refer to the 'ng-app' directive)
 */
var app = angular.module('loginApp', []);

app.controller('studentController', function($scope, $http, $httpParamSerializer,) {
	console.log("studentController " + "start");
	
	$scope.studentIdFromLogin = 0;
	$scope.studentAccount = null;
	$scope.studentList = [];
	$scope.courseList= [];
	$scope.gradeList = [];
	
	$scope.getStudentAccount = function() {
		console.log("studentController.getStudentAccount " + "start");
		
		var object = {
				id = $scope.studentIdFromLogin;
				action: "GetAccountById"  //flag to determine which controller to use
		}
		$http.post("/Account",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.studentAccount = response.data.account;
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
			alert("An error has occured");
		});
		console.log("studentController.getStudentAccount " + "end");
		
	}
	

	
	
	$scope.listCourses = function() {
		console.log("courseController.listCourses " + "start");
		var course = {
				action: "GetAllCourses" //flag to determine which controller to use
		}
		$http.post("/course",  $httpParamSerializer(course),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.courseList = response.data.courseDtoList;
				console.log("Course-->>"+$scope.courseList[0].yearLevel);
				for(var x = 0; x <$scope.courseList.length;x++){
					$scope.courseNames.push($scope.courseList[x].courseName);
					console.log("Course NAMES -->> " +$scope.courseList[x].courseName);
				}
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
			alert("An error has occured");
		});
		console.log("courseController.listCourses " + "end");
	}
	
	$scope.listGrades = function() {
		console.log("studentController.listGrades " + "start");
		var grade = {
				action: "GetAllGrades" //flag to determine which controller to use
		}
		$http.post("/Grade",  $httpParamSerializer(grade),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.gradeList = response.data.gradeDtoList;
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
			alert("An error has occured");
		});
		console.log("studentController.listGrades  " + "end");
	}
	
	

	
	$scope.getStudentAccount();
	$scope.listCourses();
	$scope.listGrades();
	$scope.processData();
});