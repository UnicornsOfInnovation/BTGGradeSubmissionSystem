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
angular.module('loginApp').controller('teacherController', function($scope, $http, $httpParamSerializer, serviceShareData) {
	console.log("teacherController " + "start");
	
	$scope.teacherIdFromLogin = serviceShareData.getData()[0];
	$scope.teacherAccount = null;
	$scope.studentList = [];
	$scope.courseList= [];
	$scope.gradeList = [];
	$scope.newGradeList=[];
	$scope.newCourseList=[];
	$scope.newCourseCodeList=[];
	

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
	

	$scope.getTeacherAccount = function() {
		console.log("teacherController.getTeacherAccount " + "start");
		
		var object = {
				id: $scope.teacherIdFromLogin,
				action: "GetAccountById"  //flag to determine which controller to use
		}
		console.log("ID--->"+$scope.teacherIdFromLogin);
		$http.post("/Account",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.teacherAccount = response.data.account[0];
				console.log("--->"+$scope.teacherAccount);

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
		console.log("teacherController.getTeacherAccount " + "end");
		
	}
	
	
	
	
	
	$scope.getTeacherAccount();
});