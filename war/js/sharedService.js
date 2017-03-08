

angular.module('loginApp').factory('serviceShareData', function($window) {
	var Redirect = document.createElement("form");
	document.body.appendChild(Redirect);
    var KEY = 'user';
    var addData = function(newObj, newType) {
    	localStorage.clear();
    	var mydata = $window.localStorage.getItem(KEY);
        if (mydata) {
        	console.log("inside local");
            mydata = JSON.parse(mydata);
        } else {
            mydata = [];
        }
        mydata.push(newObj);
        $window.localStorage.setItem(KEY, JSON.stringify(mydata));

        $window.localStorage.setItem("type", newType);
    };
    var getData = function(){
        var mydata = $window.localStorage.getItem(KEY);
        if (mydata) {
            mydata = JSON.parse(mydata);
        }
        return mydata || [];
    };


    
    return {
        logout:function(){

    		Redirect.setAttribute("method", "post");
    		Redirect.setAttribute("action", "/");
    		Redirect.submit();
    		$window.localStorage.clear();
    		//destroylocal
        },
        isLogged:function(){
        	if(null==$window.localStorage.getItem("type")){
        		return false;
        	}else if("student"==$window.localStorage.getItem("type")){
				return "student";
        	}else if("teacher"==$window.localStorage.getItem("type")){
				return "teacher";
        	}else {
				return "admin";
        	}
    		//destroylocal
        },
        redirectToType:function(){
        	if(null==$window.localStorage.getItem("type")){
        		Redirect.setAttribute("method", "post");
        		Redirect.setAttribute("action", "/");
        		Redirect.submit();
        		return false;
        	}else if("student"==$window.localStorage.getItem("type")){
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("action", "studentPage");
				Redirect.submit();
        	}else if("teacher"==$window.localStorage.getItem("type")){
				Redirect.setAttribute("method", "post");
				Redirect.setAttribute("action", "teacherPage");
				Redirect.submit();
        	}else{
        		Redirect.setAttribute("method", "post");
				Redirect.setAttribute("action", "adminPage");
				Redirect.submit();
        	}
        },
        addData: addData,
        getData: getData
    };
});


//if("admin"==$window.localStorage.getItem("user")){
//	Redirect.setAttribute("method", "post");
//	Redirect.setAttribute("action", "/adminPage");
//	Redirect.submit();
//	return true;
//}else if("teacher"==$window.localStorage.getItem("user"){
//	Redirect.setAttribute("method", "post");
//	Redirect.setAttribute("action", "teacherPage");
//	Redirect.submit();
//	return true;
//} else if("student"==$window.localStorage.getItem("user"){
//	Redirect.setAttribute("method", "post");
//	Redirect.setAttribute("action", "studentPage");
//	Redirect.submit();
//	return true;
//} else {
//	return false
//}







