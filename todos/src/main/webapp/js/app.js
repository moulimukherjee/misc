// Filename: app.js
define([
  'jquery',
  'underscore',
  'backbone',
  'router'
], function($, _, Backbone, Router){
  var initialize = function(){
	  console.log("Initializing dummy app!");
	  Router.initialize();
  }

  return {
	  initialize: initialize
  };
});