

angular.module('loginApp').factory('serviceShareData', function($window) {
	
    var KEY = 'keyGen';
    var addData = function(newObj) {
    	sessionStorage.clear();
    	var mydata = $window.sessionStorage.getItem(KEY);
        if (mydata) {
        	console.log("inside Session");
            mydata = JSON.parse(mydata);
            
        } else {
            mydata = [];
        }
        mydata.push(newObj);
        $window.sessionStorage.setItem(KEY, JSON.stringify(mydata));
    };

    var getData = function(){
        var mydata = $window.sessionStorage.getItem(KEY);
        if (mydata) {
            mydata = JSON.parse(mydata);
        }
        return mydata || [];
    };

    return {
        addData: addData,
        getData: getData
    };
});







