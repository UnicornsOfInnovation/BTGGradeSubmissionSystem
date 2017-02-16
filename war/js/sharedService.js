

angular.module('loginApp').factory('serviceShareData', function($window) {
	var Redirect = document.createElement("form");
	document.body.appendChild(Redirect);
    var KEY = 'user';
    var addData = function(newObj) {
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
        	if(null==$window.localStorage.getItem("user")){
        		Redirect.setAttribute("method", "post");
        		Redirect.setAttribute("action", "/");
        		Redirect.submit();
        		return false;
        	}else{
        		return true;
        	}
    		//destroylocal
        },
        addData: addData,
        getData: getData
    };
});







