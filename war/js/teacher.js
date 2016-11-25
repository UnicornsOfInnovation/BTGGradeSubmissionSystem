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
var app = angular.module('teacherApp', []);

app.controller('teacherController', function($scope, $http, $httpParamSerializer) {
	console.log("teacherController " + "start");
	
	
	$scope.studentList = [];
	
	$scope.studentAccount = {
			id: 0,
			username:"",
			password:"",
			firstName: "",
			lastName: "",
			contactNumber: "",
			emailAddress: "",
			strand: "",
			yearLevel: "",
			action:"",
			school: "",
			parentName: "",
			parentContact: ""
	}

	

	$scope.pass = {
		oldPassword: "",
		newPassword: "",
		confirmNewPassword: ""
	};
	

	clearPasswordFields = function(){
		$scope.pass.oldPassword="";
		$scope.pass.newPassword="";
		$scope.pass.confirmNewPassword="";
	}
	
	
	$scope.changePassword = function(){
		console.log("teacher.changePassword " + "START");
		
		
		console.log("account id: " + $scope.studentIdFromLogin);
		console.log("old password: " + $scope.pass.oldPassword);
		console.log("new password: " + $scope.pass.newPassword);
		console.log("confirm new password: " + $scope.pass.confirmNewPassword);
		
		if($scope.pass.oldPassword != $scope.teacherAccount.password){
			alert("Access denied! Wrong password!");
			clearPasswordFields();
	    }else if ($scope.pass.newPassword != $scope.pass.confirmNewPassword){
			alert("Your new password and confirm new password didn't match");
			clearPasswordFields();
		} else if ($scope.pass.newPassword == $scope.pass.oldPassword){
			alert("Your old and new passwords match! Try a new combination.");
			clearPasswordFields();
		} else {
			var changePassObject = {
			   id: $scope.teacherIdFromLogin,
			   newPassword: $scope.pass.newPassword,
			   action: "ChangePassword"
			};
			
			$http.post("/account", $httpParamSerializer(changePassObject),
					{// configuring the request not a JSON type.
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					}
			)
			
			.then(function(response) {
				console.log("Error? " + response.data.errorList.length);
				if (response.data.errorList.length == 0) {
					//There were no errors.
					alert("Changing password was successful.");
					// initializing the contents of the ingredient screen.
					$('#changePassword').modal('hide');
					$('.modal-backdrop').hide();
				} else {
					// display the error messages.
					var errorMessage = "";
					alert("Changing password was NOT successful.");
					for (var i = 0; i < response.data.errorList.length; i++) {
						errorMessage += response.data.errorList[i];
					}
					alert(errorMessage);
				}
			}, function() {
				alert("An error has occured");
			})
		}
		console.log("teacher.changePassword " + "END");
	}
	
	
	
	$scope.getStudentAccount = function() {
		console.log("studentController.getStudentAccount " + "start");
		
		var object = {
				action: "GetAccountByUsername"  //flag to determine which controller to use
		}
		$http.post("/Account",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				
				$scope.studentAccount = response.data.studentAccounts;
 
				
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
	
	

	
	
	
	
	
	
	
	
	
	
	$scope.listTeacherAccount = function() {
		console.log("accountController.listAccounts " + "start");
		var object = {
				action: "GetAllTeacherAccounts" //flag to determine which controller to use
		}
		$http.post("/Account",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.teacherList = response.data.teacherAccounts;
				
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
		console.log("accountController.listAccounts " + "end");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	$scope.getAllStrands = function() {
		console.log("accountController.listAccounts " + "start");
		var object = {
				action: "GetAllStrands" //flag to determine which controller to use
		}
		$http.post("/Strand",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.strandList = response.data.strandsList;
				console.log("-->>"+response.data.strandsList[0]);
				for(var x = 0; x <$scope.strandList.length;x++){
					$scope.strandNames.push($scope.strandList[x].strandName);
					console.log("STRAND NAMES -->> " +$scope.strandList[x].strandName);
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
		console.log("accountController.listAccounts " + "end");
	}
	
	
	
	
	
	
	
	
	
	
	$scope.deleteAccount = function(account) {
		var confirmation = window.confirm("Are you sure you want to delete this menu?");
		if (true == confirmation){
			
			var deleteAccount = {
				id: account,
				action: "DeleteAccount"
			}

			$http.post("/account", $httpParamSerializer(deleteAccount),
					{// configuring the request not a JSON type.
						headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					}
				)
				.then(function(response) {
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Deleting account was successful.");
						$scope.listStudentAccount();
						$scope.listTeacherAccount();
					} else {
						// display the error messages.
						var errorMessage = "";
						for (var i = 0; i < response.data.errorList.length; i++) {
							errorMessage += response.data.errorList[i];
						}
						alert(errorMessage);
					}
				}, function() {
					alert("An error has occured");
				})
		
		}
	}
	
	
	
	
	
	
	$scope.setStrand = function(){
		console.log("<<<--"+$scope.strandList[0].strandName);
		$scope.Account.strand = $scope.strandList[0].strandName;
		console.log("<<<--"+$scope.Account.strand);
	}
	
	$scope.initRegisterStudentModal = function(){
		console.log("accountController.initStudent " + "start");
		$scope.studentAccount.id = 0;
		$scope.studentAccount.username = "";
		$scope.studentAccount.password = "";
		$scope.studentAccount.firstName =  "";
		$scope.studentAccount.lastName =  "";
		$scope.studentAccount.contactNumber =  "";
		$scope.studentAccount.emailAddress =  "";
		$scope.studentAccount.strand =  "";
		$scope.studentAccount.yearLevel =  "";
		$scope.studentAccount.action = "";
		$scope.studentAccount.school =  "";
		$scope.studentAccount.parentName =  "";
		$scope.studentAccount.parentContact =  "";
		$scope.selectedYearLevel = 1;
		console.log("accountController.initStudent " + "End");
	}
	$scope.initRegisterAccountModal = function(){
		console.log("accountController.initAcc " + "start");
		$scope.Account.id = 0;
		$scope.Account.username = "";
		$scope.Account.password = "";
		$scope.Account.firstName =  "";
		$scope.Account.lastName =  "";
		$scope.Account.contactNumber =  "";
		$scope.Account.emailAddress =  "";
		$scope.Account.strand =  "";
		$scope.Account.yearLevel =  "";
		$scope.Account.action = "";
		$scope.Account.school =  "";
		$scope.Account.parentName =  "";
		$scope.Account.parentContact =  "";
		$scope.Account.courseCode = "";
		$scope.Account.userType = "student";
		console.log("accountController.initAcc " + "End");
	}
	$scope.initLists = function(){
		console.log("accountController.initList " + "start");
		$scope.listStudentAccount();
		$scope.getAllStrands();
		$scope.listTeacherAccount();
		$scope.listCourses();
		console.log("accountController.initList " + "end");
	}
	$scope.initRegisterTeacherModal = function(){
		console.log("accountController.initTeacher " + "start");
		$scope.teacherAccount.id = 0;
		$scope.teacherAccount.username = "";
		$scope.teacherAccount.password = "";
		$scope.teacherAccount.firstName =  "";
		$scope.teacherAccount.lastName =  "";
		$scope.teacherAccount.contactNumber = "";
		$scope.teacherAccount.emailAddress = "";
		$scope.teacherAccount.courseCode = "";
		console.log("accountController.initTeacher " + "End");
	}
	$scope.initLists();
	$scope.initRegisterAccountModal();
	$scope.initRegisterStudentModal();
	$scope.initRegisterTeacherModal();
	
	
	
	$scope.getStudentAccount();
});