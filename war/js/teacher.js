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
	$scope.courseDetails = null;
	$scope.studentList = [];
	$scope.courseList= [];
	$scope.gradeList = [];
	$scope.newGradeList=[];
	$scope.newCourseList=[];
	$scope.newCourseCodeList=[];
	$scope.studentGradeList =[];
	$scope.editable = false;
	$scope.bestStudentList = [];
	$scope.bestStudentDetails = null;
	$scope.myTableArray = [];
	$scope.pass = {
		oldPassword: "",
		newPassword: "",
		confirmNewPassword: ""
	};
	var columns = ['Student Name','FG'];
	var myTableArray = [];

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
				$scope.getCourseDetails();
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
	
	$scope.getCourseDetails = function(){
		
		var object = {
				courseName: $scope.teacherAccount.courseCode,
				action: "GetCourseByName"  //flag to determine which controller to use
		}
		console.log("ID--->"+$scope.teacherIdFromLogin);
		$http.post("/Course",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.courseDetails = response.data.courseDto[0];
				console.log("--->"+$scope.courseDetails.courseName);
				$scope.getStudentGradeList();
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
		
	}
	
	
	$scope.getStudentGradeList = function(){
		
		var object = {
				courseId: $scope.courseDetails.id,
				action: "ListStudentGradesByCourse"  //flag to determine which controller to use
		}
		$http.post("/Grade",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				$scope.studentGradeList = response.data.studentGradesList;
				console.log("Inside--->"+$scope.studentGradeList[0].grade);
				for(var ctr = 0; ctr<$scope.studentGradeList.length;ctr++){
					if($scope.studentGradeList[ctr].grade==0){
						$scope.studentGradeList[ctr].grade="NG";
					}
				}
				$scope.getBestStudentList();
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
		
	}
	
	
	
	$scope.saveGrades = function(){
		var saveIt = true;
		for(var i = 0; i < $scope.studentGradeList.length; i++){
			if( 5.0 < parseFloat($scope.studentGradeList[i].grade) || 1.0 > parseFloat($scope.studentGradeList[i].grade )){
				alert("Error grade! Please input from 1.0 to 5.0 in student "+(i+1));
				saveIt = false;
				break;
			}
		}
		
		var gradeList = [];
		
		for(var y = 0; y<$scope.studentGradeList.length;y++){
			var pushGrade = {
					grade: $scope.studentGradeList[y].grade,
					gradeId: $scope.studentGradeList[y].gradeId,
					accountId: $scope.studentGradeList[y].accountId,
					courseId: $scope.studentGradeList[y].courseId,
					firstName: $scope.studentGradeList[y].firstName,
					lastName: $scope.studentGradeList[y].lastName,
					courseName: $scope.studentGradeList[y].courseName
			}
			gradeList.push(pushGrade);
		}
		for(var y = 0; y<gradeList.length;y++){
			console.log("-->>"+gradeList[y].firstName);
		}
		
		var bestStudent =0;
		
		for(var x = 0; x<$scope.bestStudentList.length;x++){
			if($scope.courseDetails.courseId == $scope.bestStudentList[x].courseId){
				bestStudent = $scope.bestStudentList[x].bestStudentId;
			}
		}

		
		
		var object = {
				bestStudentId: bestStudent,
				courseName: $scope.courseDetails.courseName,
				courseId: $scope.bestStudent.courseId  ,
				gradeId: $scope.bestStudent.gradeId,
				accountId: $scope.bestStudent.accountId,
				firstName: $scope.bestStudent.firstName,
				lastName: $scope.bestStudent.lastName,
				grade: $scope.bestStudent.grade,
				gradesArray: gradeList,
				action: "SubmitGrade"  //flag to determine which controller to use
		}
		console.log("Best Student is "+ object.bestStudentId);

		console.log("ID--->"+$scope.teacherIdFromLogin);
		
		if(saveIt == true){
			$http.post("/Grade",  $httpParamSerializer(object),
					{// configuring the request not a JSON type.
						headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					}
			)
			.then(function(response){
				if (response.data.errorList.length == 0) {
					console.log("--->Success inserting grades");
					alert("Inserting grades was successful!");
					var Redirect = document.createElement("form");
					document.body.appendChild(Redirect);
					Redirect.setAttribute("method", "post");
					Redirect.setAttribute("action", "");
					Redirect.submit();
					$route.reload();
				} else {
					var errorMessage = "";
					for (var i = 0; i < response.data.errorList.length; i++) {
						errorMessage += response.data.errorList[i];
						console.log("--->Error Count"+i);
					}
					alert(errorMessage);
				}
			}, function() {
				alert("An error has occured");
			});
		}
		
	}
	
	$scope.callme = function (){
		html2canvas(document.getElementById("class-list-primary"),{
			onrendered: function (canvas){
//				
				var bestStudentName = "";
				for(var ctr = 0; ctr<$scope.studentGradeList.length;ctr++){
				
					
					    var arrayOfThisRow = [];
					    var studentName = $scope.studentGradeList[ctr].firstName + " " +$scope.studentGradeList[ctr].lastName;
					    arrayOfThisRow.push(studentName);    
					    arrayOfThisRow.push($scope.studentGradeList[ctr].grade);
					    $scope.myTableArray.push(arrayOfThisRow);
					
				}
				for(var ctr = 0; ctr<$scope.bestStudentList.length;ctr++){
					if($scope.bestStudentList[ctr].courseId == $scope.courseDetails.id){ 
					    bestStudentName = $scope.bestStudentList[ctr].firstName + " " + $scope.bestStudentList[ctr].lastName;
					}
				}
				var columns2 = [" "," "];
				var array2=[];
				array2.push(["Teacher Name", $scope.teacherAccount.firstName + $scope.teacherAccount.lastName]);
				array2.push(["Course Code", $scope.courseDetails.courseCode]);
				array2.push(["Course Title", $scope.courseDetails.courseName]);
				array2.push(["Course Units", $scope.courseDetails.courseUnits]);
				array2.push(["Best Student", bestStudentName]);
				
				
			    var doc = new jsPDF('p', 'pt');
			    doc.autoTable(columns2, array2, {margin:{top:80}, theme: 'plain'});
			    var header = function(data) {
			        doc.setFontSize(18);
			        doc.setTextColor(40);
			        doc.setFontStyle('normal');
			        //doc.addImage(headerImgData, 'JPEG', data.settings.margin.left, 20, 50, 50);
			        doc.text("Class List - " + $scope.courseDetails.courseName, data.settings.margin.left, 50);
			      };
		        var options = {
		    		    beforePageContent: header,
		    		    margin: {
		    		      top: 160
		    		    },
		    		    startY: doc.autoTableEndPosY() + 20
		    		  };
		        doc.autoTable(columns, $scope.myTableArray, options);
			    doc.save("classList - " + $scope.courseDetails.courseName +".pdf");
			}
		})
	}
	
	$scope.getBestStudentList = function(){
			
			var object = {
					action: "GetAllBestStudents"  //flag to determine which controller to use
			}
			$http.post("/BestStudents",  $httpParamSerializer(object),
					{// configuring the request not a JSON type.
						headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					}
			)
			.then(function(response) {
				if (response.data.errorList.length == 0) {
					
					$scope.bestStudentList = response.data.bestStudentList;
					console.log("yolo->" + $scope.bestStudentList);
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
		}
	
	$scope.logOut = function(){
		serviceShareData.logout();
	}
	$scope.checkAccess = function(){
		if(serviceShareData.isLogged()){

			$scope.getTeacherAccount();
		}
		
	}
	$scope.checkAccess();

});