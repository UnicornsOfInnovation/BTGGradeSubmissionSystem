app.controller('courseController', function($scope, $http, $httpParamSerializer) {
	console.log("courseController " + "start");
	$scope.strandList = [];
	$scope.yearArray = ["10","11","12"];
	$scope.courseType = ["major","minor"];
	$scope.selection = $scope.courseType[0];
	$scope.selectedYear = 0;
	$scope.selectedStrand = 0;
	$scope.yearLevel = null;
	$scope.strandEdit = null;
	$scope.strandNames = [];
	$scope.courseList = [];
	$scope.selectedCourse = 0;
	$scope.courseEdit = null;
	
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
	
	$scope.initEdit = function(model){
		console.log("year level edited is" +model.yearLevel);
		for(var x = 0; x<$scope.yearArray.length;x++){
			if(model.yearLevel==$scope.yearArray[x]){
				$scope.selectedYear = x;
				$scope.course.yearLevel = $scope.yearArray[$scope.selectedYear];
				$scope.yearLevel =  $scope.yearArray[$scope.selectedYear];
				console.log("-->>>>>>"+$scope.course.yearLevel);
			}
		}
		console.log("strand edited is" +model.strand);
		for(var y = 0; y<$scope.strandNames.length;y++){
			if(model.strand==$scope.strandNames[y]){
				console.log("SELECTED STRAND IS -->> " +y);
				$scope.selectedStrand = y;
				$scope.course.strand = $scope.strandNames[$scope.selectedStrand];
				$scope.strandEdit =  $scope.strandNames[$scope.selectedStrand];
				console.log("STRAND-->>>>>>"+$scope.strandEdit);
			}
		}
		console.log("course edited is" +model.courseType);
		for(var z = 0; z<$scope.courseType.length;z++){
			if(model.courseType==$scope.courseType[z]){
				console.log("SELECTED COURSE TYPE IS -->> " +z);
				$scope.selectedCourse = z;
				$scope.course.courseType = $scope.courseType[$scope.selectedCourse];
				$scope.courseEdit =  $scope.courseType[$scope.selectedCourse];
				console.log("Course-->>>>>>"+$scope.courseEdit);
			}
		}
		
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
					    strand: $scope.course.strand.strandName,
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
	

	$scope.validateType = function(courseType){
		if(courseType == "major"){
			return true;
		}
		return false;	
	}
	
	
	$scope.updateCourse = function(course,yearLevel,strandEdit, courseEdit){
		var confirmation = window.confirm("Are you sure you want to update this course?");
		console.log("UPDATE COURSE-->>"+yearLevel+" STRAND EDIT"+strandEdit);
		if (true == confirmation) {	
				var course = {	
						id: course.id,
						courseName: course.courseName,
						courseCode: course.courseCode,
						courseUnits: course.courseUnits,
					    courseType: courseEdit,
					    strand: strandEdit,
					    yearLevel: yearLevel,
						action:"UpdateCourse"
				}
				console.log("UPDATE COURSE-->>"+course.strand);
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