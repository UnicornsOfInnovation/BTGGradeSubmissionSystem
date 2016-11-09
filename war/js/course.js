app.controller('courseController', function($scope, $http, $httpParamSerializer) {
	console.log("courseController " + "start");
	$scope.strandList = [];
	$scope.yearArray = ["10","11","12"];
	$scope.courseType = ["major","minor"];
	$scope.selection = $scope.courseType[0];
	
	$scope.courseList = [];
	
	$scope.course = {
		id: 0,
		courseCode: "",
		courseUnits: "",
	    courseType: "",
	    strand: "",
	    yearLevel: ""			
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
	
	
	
	
	
	$scope.deleteCourse = function(courseId) {
		var confirmation = window.confirm("Are you sure you want to delete this menu?");
		if (true == confirmation){
			
			var course = {
				id: courseId,
				action: "DeleteCourse"
			}

			$http.post("/course", $httpParamSerializer(course),
					{// configuring the request not a JSON type.
						headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					}
				)
				.then(function(response) {
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Deleting course was successful.");
						
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
	
	
	
	
	
	
	$scope.registerCourse = function(){
		var confirmation = window.confirm("Are you sure you want to add this course?");
		if (true == confirmation) {	
				var courseObject = {	
						courseName: $scope.course.courseName,
						courseCode: $scope.course.courseCode,
						courseUnits: $scope.course.courseUnits,
					    courseType: $scope.course.courseType,
					    strand: $scope.course.strand,
					    yearLevel: $scope.course.yearLevel,
						action:"InsertCourse"
				}
				
				console.log(courseObject.courseName);
				console.log(courseObject.courseCode);
				console.log(courseObject.courseUnits);
				console.log(courseObject.courseType);
				console.log(courseObject.strand);
				console.log(courseObject.yearLevel);
				$http.post("/course", $httpParamSerializer(courseObject),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Inserting course was successful.");
						// initializing the contents of the ingredient screen.
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Inserting course was NOT successful.");
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
	

	
	
	
	$scope.updateCourse = function(course){
		var confirmation = window.confirm("Are you sure you want to update this course?");
		if (true == confirmation) {	
				var course = {	
						id: course.id,
						courseName: course.courseName,
						courseCode: course.courseCode,
						courseUnits: course.courseUnits,
					    courseType: course.courseType,
					    strand: course.strand,
					    yearLevel: course.yearLevel,
						action:"UpdateCourse"
				}
				console.log("-->>"+course.courseName);
				$http.post("/course", $httpParamSerializer(course),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						//There were no errors.
						alert("Updating course was successful.");
						// initializing the contents of the ingredient screen.
					} else {
						// display the error messages.
						var errorMessage = "";
						alert("Updating course was NOT successful.");
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
	

	
	


	$scope.initRegisterCourseModal = function(){
		console.log("courseController.initCourse " + "start");
		$scope.listCourses();
		$scope.getAllStrands();
		$scope.course.id = 0;
		$scope.course.courseCode = "";
		$scope.course.courseUnits = "";
		$scope.course.courseType = "";
		$scope.course.strand = "";
		$scope.course.yearLevel = "";	
		console.log("courseController.initCourse " + "End");
	}

	
	$scope.initRegisterCourseModal();
	
});