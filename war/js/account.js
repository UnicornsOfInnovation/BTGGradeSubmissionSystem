app.controller('accountController', function($scope, $http, $httpParamSerializer) {
	console.log("accountController " + "start");
	$scope.studentList = [];
	$scope.teacherList = [];
	$scope.strandList = [];
	$scope.strandNames = [];
	$scope.courseList = [];
	$scope.courseNames = [];
	$scope.selectedYear = 0;
	$scope.selectedCourse = 0;
	$scope.selectedStrand = 0;
	$scope.yearLevel = null;
	$scope.courseEdit = null;
	$scope.strandEdit = null;
	
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
	$scope.year = null;
	$scope.teacherAccount = {
			id: 0,
			username:"",
			password:"",
			firstName: "",
			lastName: "",
			contactNumber: "",
			emailAddress: "",
			courseCode: "",
			action:""
	}	
	$scope.Account = {
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
			userType: "student",
			parentName: "",
			parentContact: "",
			courseCode: ""
	}
	$scope.yearArray = ["10","11","12"];
	$scope.accountType = ["student", "teacher"];
	
	
	$scope.listStudentAccount = function() {
		console.log("accountController.listAccounts " + "start");
		var object = {
				action: "GetAllStudentAccounts" //flag to determine which controller to use
		}
		$http.post("/Account",  $httpParamSerializer(object),
				{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}
		)
		.then(function(response) {
			if (response.data.errorList.length == 0) {
				
				$scope.studentList = response.data.studentAccounts;

				
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
	
	
	$scope.registerStudentAccount = function(){
		var confirmation = window.confirm("Are you sure you want to add student?");
		if (true == confirmation) {	
				var student = {
						id: 0,
						username:$scope.studentAccount.username,
						password:$scope.studentAccount.password,
						firstName: $scope.studentAccount.firstName,
						lastName: $scope.studentAccount.lastName,
						contactNumber: $scope.studentAccount.contactNumber,
						emailAddress: $scope.studentAccount.emailAddress,
						strand: $scope.studentAccount.strand,
						yearLevel: $scope.studentAccount.yearLevel,
						userType:"student",
						school: $scope.studentAccount.school,
						parentName: $scope.studentAccount.parentName,
						parentContact: $scope.studentAccount.parentContact,
						action:"InsertAccount"
				}
				
				$http.post("/account", $httpParamSerializer(student),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Inserting student account was successful.");
						// initializing the contents of the ingredient screen.
						$('#registerStudentAccount').modal('hide');
						$('.modal-backdrop').hide();
						$scope.listStudentAccount();
						$scope.initRegisterStudentModal();				
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Inserting item was NOT successful.");
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
	$scope.registerTeacherAccount = function(){
		var confirmation = window.confirm("Are you sure you want to add teacher?");
		if (true == confirmation) {	
				var teacher = {
						id: 0,
						username:$scope.teacherAccount.username,
						password:$scope.teacherAccount.password,
						firstName: $scope.teacherAccount.firstName,
						lastName: $scope.teacherAccount.lastName,
						contactNumber: $scope.teacherAccount.contactNumber,
						emailAddress: $scope.teacherAccount.emailAddress,
						courseCode: $scope.teacherAccount.courseCode,
						userType:"teacher",
						action:"InsertAccount"
				}
				console.log($scope.teacherAccount.username);
				console.log(teacher.password);
				
				$http.post("/account", $httpParamSerializer(teacher),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				).then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Inserting teacher was successful.");
						// initializing the contents of the ingredient screen.
						$('#registerTeacherAccount').modal('hide');
						$('.modal-backdrop').hide();
						$scope.listTeacherAccount();
						$scope.initRegisterTeacherModal();
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Inserting teacher was NOT successful.");
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
	
	
	
	
	
	
	$scope.initEdit = function(model){
		console.log("year level edited is" +model.yearLevel);
		for(var x = 0; x<$scope.yearArray.length;x++){
			if(model.yearLevel==$scope.yearArray[x]){
				$scope.selectedYear = x;
				$scope.studentAccount.yearLevel = $scope.yearArray[$scope.selectedYear];
				$scope.yearLevel =  $scope.yearArray[$scope.selectedYear];
				console.log("-->>>>>>"+$scope.studentAccount.yearLevel);
			}
		}
		console.log("strand edited is" +model.strand);
		for(var y = 0; y<$scope.strandNames.length;y++){
			if(model.strand==$scope.strandNames[y]){
				console.log("SELECTED STRAND IS -->> " +y);
				$scope.selectedStrand = y;
				$scope.studentAccount.strand = $scope.strandNames[$scope.selectedStrand];
				$scope.strandEdit =  $scope.strandNames[$scope.selectedStrand];
				console.log("STRAND-->>>>>>"+$scope.strandEdit);
			}
		}
		
	}
	
	
	
	
	$scope.initEditTeacher = function(model){
		console.log("course edited is" +model.courseCode);
		for(var y = 0; y<$scope.courseNames.length;y++){
			if(model.courseCode==$scope.courseNames[y]){
				console.log("SELECTED Course IS -->> " +y);
				$scope.selectedCourse = y;
				$scope.teacherAccount.courseCode = $scope.courseNames[$scope.selectedCourse];
				$scope.courseEdit =  $scope.courseNames[$scope.selectedCourse];
				console.log("COURSE-->>>>>>"+$scope.courseEdit);
			}
		}
		
	}
	
	
	
	
	
	
	
	$scope.registerAccount = function(){
		var confirmation = window.confirm("Are you sure you want to add account?");
		if (true == confirmation) {	
			if($scope.Account.userType == "student"){
				var account = {
						id: 0,
						username:$scope.Account.username,
						password:$scope.Account.password,
						firstName: $scope.Account.firstName,
						lastName: $scope.Account.lastName,
						contactNumber: $scope.Account.contactNumber,
						emailAddress: $scope.Account.emailAddress,
						strand: $scope.Account.strand.strandName,
						yearLevel: $scope.Account.yearLevel,
						userType: "student",
						school: $scope.Account.school,
						parentName: $scope.Account.parentName,
						parentContact: $scope.Account.parentContact,
						userType : $scope.Account.userType,
						courseCode: $scope.Account.courseCode,
						action:"InsertAccount"
							
				}
			}else{
				var account = {
						id: 0,
						username:$scope.Account.username,
						password:$scope.Account.password,
						firstName: $scope.Account.firstName,
						lastName: $scope.Account.lastName,
						contactNumber: $scope.Account.contactNumber,
						emailAddress: $scope.Account.emailAddress,
						userType : "teacher",
						courseCode: $scope.Account.courseCode.courseName,
						action:"InsertAccount"
				}
			}	
				
				console.log("STRAND-->>>" + account.strand);
				
				$http.post("/account", $httpParamSerializer(account),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Inserting account was successful.");
						// initializing the contents of the ingredient screen.
						$('#registerStudentAccount').modal('hide');
						$('.modal-backdrop').hide();
						$scope.listStudentAccount();
						$scope.initRegisterStudentModal();				
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Inserting item was NOT successful.");
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
	
	
	$scope.updateStudentAccount = function(studentAccount,yearLevel,strandEdit){
		var confirmation = window.confirm("Are you sure you want to save changes?");
		if (true == confirmation) {	
				var student = {
						accountId: studentAccount.id,
						id: studentAccount.id,
						username:studentAccount.username,
						password:studentAccount.password,
						firstName: studentAccount.firstName,
						lastName: studentAccount.lastName,
						contactNumber: studentAccount.contactNumber,
						emailAddress: studentAccount.emailAddress,
						strand: strandEdit,
						yearLevel: yearLevel,
						userType:"student",
						status: studentAccount.status,
						school: studentAccount.school,
						parentName: studentAccount.parentName,
						parentContact: studentAccount.parentContact,
						action:"UpdateAccount"
				}
				console.log("-->>>"+student.strand+"<<<----")
				$http.post("/account", $httpParamSerializer(student),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Updating student account was successful.");
						// initializing the contents of the ingredient screen.
						$('#editStudentModal').modal('hide');
						$('.modal-backdrop').hide();
						$scope.listStudentAccount();
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Inserting item was NOT successful.");
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
	
	$scope.updateTeacherAccount = function(Account, courseEdit){
		var confirmation = window.confirm("Are you sure you want to save changes?");
		if (true == confirmation) {	
				var teacher = {
						accountId: Account.id,
						id: Account.id,
						username:Account.username,
						password:Account.password,
						firstName: Account.firstName,
						lastName: Account.lastName,
						contactNumber: Account.contactNumber,
						emailAddress: Account.emailAddress,
						courseCode: courseEdit,
						userType:"teacher",
						status: Account.status,
						action:"UpdateAccount"
				}
				
				$http.post("/account", $httpParamSerializer(teacher),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Updating teacher account was successful.");
						// initializing the contents of the ingredient screen.
						$('#editTeacherModal').modal('hide');
						$('.modal-backdrop').hide();
						$scope.listTeacherAccount();
						
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Updating teacher account was NOT successful.");
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
});