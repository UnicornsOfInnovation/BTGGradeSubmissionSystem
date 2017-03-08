app.controller('courseController', function($scope,$route, $http, $httpParamSerializer,serviceShareData) {
	console.log("courseController " + "start");
	$scope.strandList = [];
	$scope.yearArray = ["11","12"];
	$scope.courseType = ["Major","Minor"];
	$scope.selection = $scope.courseType[0];
	$scope.selectedYear = 0;
	$scope.selectedStrand = 0;
	$scope.yearLevel = null;
	$scope.strandEdit = null;
	$scope.strandNames = [];
	$scope.courseList = [];
	$scope.selectedCourse = 0;
	$scope.courseEdit = null;
	$scope.courseNameCurrent ="";
	$scope.modelEditCourse = {
		id: 0,
		courseName: "",
		courseCode: "",
		courseUnits: "",
	    courseType: "",
	    strand: "",
	    yearLevel: ""
	};
	
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
					}
			} else {
				var errorMessage = "";
				for (var i = 0; i < response.data.errorList.length; i++) {
					errorMessage += response.data.errorList[i];
				}
				alert(errorMessage);
			}
		}, function() {
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
		$scope.modelEditCourse.courseName = model.courseName;
		$scope.modelEditCourse.courseCode = model.courseCode;
		$scope.modelEditCourse.courseUnits = model.courseUnits;
		$scope.modelEditCourse.courseType = model.courseType;
		$scope.modelEditCourse.id = model.id;
		if(model.courseType == "Major"){
			$scope.modelEditCourse.yearLevel = model.yearLevel;
			$scope.modelEditCourse.strand = model.strand;
		}
		for(var x = 0; x<$scope.yearArray.length;x++){
			if(model.yearLevel==$scope.yearArray[x]){
				$scope.selectedYear = x;
				$scope.course.yearLevel = $scope.yearArray[$scope.selectedYear];
				$scope.yearLevel =  $scope.yearArray[$scope.selectedYear];
			}
		}
		console.log("strand edited is" +model.strand);
		for(var y = 0; y<$scope.strandNames.length;y++){
			if(model.strand==$scope.strandNames[y]){
				$scope.selectedStrand = y;
				$scope.course.strand = $scope.strandNames[$scope.selectedStrand];
				$scope.strandEdit =  $scope.strandNames[$scope.selectedStrand];
				}
		}
		for(var z = 0; z<$scope.courseType.length;z++){
			if(model.courseType==$scope.courseType[z]){
				$scope.selectedCourse = z;
				$scope.course.courseType = $scope.courseType[$scope.selectedCourse];
				$scope.courseEdit =  $scope.courseType[$scope.selectedCourse];
				}
		}

		$scope.courseNameCurrent = model.courseName;
		
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
						$route.reload();
						$('.modal-backdrop').hide();
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
				var flag=0;
				for(var y = 0; y <$scope.courseList.length;y++){
					if(courseObject.courseCode.toUpperCase() == $scope.courseList[y].courseCode.toUpperCase()){
						alert("Course Code already exist");
						flag = 1;
					}
				}
				if(flag==0){
				$http.post("/course", $httpParamSerializer(courseObject),
						{// configuring the request not a JSON type.
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
						}
				)
				.then(function(response) {
					console.log("Error? " + response.data.errorList.length);
					if (response.data.errorList.length == 0) {
						$('.modal-backdrop').hide();
						$route.reload();
						$scope.refreshButton();
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
	}
	

	$scope.validateType = function(courseType){
		if(courseType == "Major"){
			return true;
		}
		return false;	
	}
	
	
	$scope.updateCourse = function(course,yearLevel,strandEdit, courseEdit){
		var confirmation = window.confirm("Are you sure you want to update this course?");
		var cont = false;
		var check = 0;
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
				
				if(course.courseName.toLowerCase() == $scope.courseNameCurrent.toLowerCase()){
					cont = true;
				} else {
					for(var x=0; x < $scope.courseList.length; x++){	
						if(course.id != $scope.courseList[x].courseId){
							if(course.courseName.toUpperCase() == $scope.courseList[x].courseName.toUpperCase()){
								check = 1;
							}
						}
					}
					if(1 == check){
						cont = false;
					} else {
						cont = true;
					}
				}
				
				var flag=0;
				for(var y = 0; y <$scope.courseList.length;y++){
					if(course.courseCode.toUpperCase() == $scope.courseList[y].courseCode.toUpperCase() && course.id != $scope.courseList[y].id){
						alert("Course Code already exist");
						flag = 1;
					}
				}
				if(flag==0){
				if(true == cont){
					$http.post("/course", $httpParamSerializer(course),
							{// configuring the request not a JSON type.
						headers: {'Content-Type': 'application/x-www-form-urlencoded'}
							}
					)
					.then(function(response) {
						console.log("Error? " + response.data.errorList.length);
						if (response.data.errorList.length == 0) {
							$route.reload();
							$('.modal-backdrop').hide();

							$scope.refreshButton();
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
				} else {
					alert("There is a course already having that name. Please use another course name.");
				}		
				}
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

	$scope.logOut = function(){
		serviceShareData.logout();
		
	}
	$scope.checkAccess = function(){
		if("admin"==serviceShareData.isLogged()){
			$scope.initRegisterCourseModal();
		}else{
			serviceShareData.redirectToType();
		}
	}
	$scope.refreshButton =function(){
		var Redirect = document.createElement("form");
		document.body.appendChild(Redirect);
		Redirect.setAttribute("method", "post");
		Redirect.setAttribute("action", "");
		Redirect.submit();
	}
	$scope.checkAccess();
	
});