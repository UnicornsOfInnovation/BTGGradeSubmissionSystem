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
 * [04/13/2016] 0.01 �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ Lehmar Cabrillos  �ｿｽ�ｽｿ�ｽｽ�ｿｽ�ｽｽ�ｽｿ�ｿｽ�ｽｽ�ｽｽ Initial codes.
 */


/**
 * The angular module object.
 * @param pizzaTimeApp - the application name (refer to the 'ng-app' directive)
 */
var app = angular.module('BTGapp', ['ngRoute']);


app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/accountScreen', {
        templateUrl: '/html/templates/account.html',
        controller: 'accountController'
      })
      .when('/courseScreen', {
        templateUrl: '/html/templates/course.html',
        controller: 'courseController'
      })
      .otherwise({
    	  templateUrl: '/html/templates/account.html',
          controller: 'accountController'
      });
  }]);




