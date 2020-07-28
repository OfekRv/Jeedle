(function() {
	'use strict';

	angular.module('demoApp', [ 'ui.tree', 'ngRoute', 'ui.bootstrap' ])

	.config(
			[ '$routeProvider', '$compileProvider',
					function($routeProvider, $compileProvider) {
						$routeProvider.when('/basic-example', {
							controller : 'BasicExampleCtrl',
							templateUrl : 'views/cnc.html'
						})

						.otherwise({
							redirectTo : '/cnc'
						});

						$compileProvider.debugInfoEnabled(false);
					} ]);
})();
