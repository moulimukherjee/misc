// Filename: router.js

define([
  'jquery',
  'underscore',
  'backbone'
], function($, _, Backbone){
	
	var AppRouter = Backbone.Router.extend({
		routes : {
			'*action' : 'defaultAction'
		},

		defaultAction : function() {
			alert('Default Action');
		}
	});

	var initialize = function() {
		console.log('Initializing router!');
		var appRouter = new AppRouter();
		Backbone.history.start();
	};

	return {
		initialize : initialize
	};
});
