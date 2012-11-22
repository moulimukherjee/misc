// Filename: main.js

require.config({
	
    paths: {
		jquery: "libs/jquery-1.8.3",
		underscore: "libs/underscore",
		backbone: "libs/backbone"
    },
	shim: {
        'backbone': {
        // These script dependencies should be loaded before loading
        // backbone.js
        deps: ['underscore', 'jquery'],
        // Once loaded, use the global 'Backbone' as the
        // module value.
        exports: 'Backbone'
        }
    }
});

require(['app'], function(App) {
	
	console.log("Calling app initialize...");
	
	App.initialize();	
});