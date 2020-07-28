(function() {
	'use strict';

	angular
			.module('demoApp')
			.controller(
					'BasicExampleCtrl',
					[
							'$scope',
							'$http',
							'$modal',
							'$location',
							function($scope, $http, $modal, $location, SweetAlert) {
								$scope.removeOperator = function(scope) {
									var model = scope.$modelValue;
									$http({
										method : 'DELETE',
										url : 'operators/' + model.ip
									})
											.then(
													function successCallback(
															response) {
													});
									scope.remove();
								};

								$scope.toggle = function(scope) {
									scope.toggle();
								};

								$scope.moveLastToTheBeginning = function() {
									var a = $scope.data.pop();
									$scope.data.splice(0, 0, a);
								};

								$scope.injectAgent = function(scope) {
									var nodeData = scope.$modelValue;
									$scope.selectedJar;
								    $modal.open({
								        templateUrl: 'views/templates/injectAgent.html',
								        backdrop: true,
								        windowClass: 'modal',
								        controller: function($scope, $modalInstance) {
											$http({
												method : 'POST',
												url : 'whatsUp',
												data: nodeData.ip
											})
													.then(
															function successCallback(
																	response) {
																$scope.jars = [];
																angular
																		.forEach(
																				response.data,
																				function(
																						jarName) {
																					$scope.jars.push(jarName);
																				});
															});
											
								          $scope.inject = function() {
								        		$http({
													method : 'POST',
													url : 'injectAgent',
													data: {ip:nodeData.ip,jarName:$scope.selectedJar}
												})
														.then(
																function successCallback(
																		response) {
																	if(response.data)
																	{
																		nodeData.nodes.push({
																		ip : nodeData.ip,
																		title : $scope.selectedJar,
																		mainClass : $scope.selectedJar,
																		isAlive : true,
																		nodrop : true
																		});
																		
																	}
																	else
																	{
																		Swal.fire(
																				  'Injection failed :(',
																				  'Probably lost connection with operator, or there is already instance of this agent, or agent cant be deployed here...',
																				  'error'
																				)
																	}
																});
								        	
								            $modalInstance.dismiss('inject');
								          }
								          $scope.cancel = function() {
								            $modalInstance.dismiss('cancel');
								          };
								        },
								        resolve: {
								          user: function() {
								            return $scope.selected;
								          }
								        }
								      });
								};
								
								$scope.injectCode = function(scope) {
									$scope.classes = [];
						        	$scope.localVariables = [];
						        	$scope.variableName = "";
						        	$scope.variableType = "";
						        	$scope.fields = [];
									var nodeData = scope.$modelValue;
									$scope.selectedAgent;
								    $modal.open({
								        templateUrl: 'views/templates/injectCode.html',
								        backdrop: true,
								        windowClass: 'modal',
								        controller: function($scope, $modalInstance) {
											$http({
												method : 'POST',
												url : 'classes',
												data: nodeData.ip
											})
													.then(
															function successCallback(
																	response) {
																$scope.classes = [];
																angular
																		.forEach(
																				response.data,
																				function(
																						className) {
																					$scope.classes.push(className);
																				});
																$scope.classes.sort();
																$scope.updateClass = function() {
																	$http({
																		method : 'POST',
																		url : 'classDetails',
																		data: {ip:nodeData.ip,className:$scope.selectedClass}
																	})
																			.then(
																					function successCallback(
																							response) {
																						$scope.fields = response.data.fields;
																						$scope.methods = response.data.methods;
																						$scope.methods.sort();
																					});
																	}
															});
											
										      $scope.addVariable = function() {
										    	  if ($scope.localVariables == null)
									    		  {
										    		  $scope.localVariables = [];
									    		  }
										    	  $scope.localVariables.push({name:$scope.variableName,type:$scope.variableType});
										    	  $scope.variableName="";
										    	  $scope.variableType="";
									          }
										      
										      $scope.removeVariable = function(variable)
										      {
										    	  const index = $scope.localVariables.indexOf(variable);
										    	  if (index > -1) {
										    		  $scope.localVariables.splice(index, 1);
										    	  }
										      };
												
										      $scope.inject = function() {
													$http({
														method : 'POST',
														url : 'injectCode',
														data: {ip:nodeData.ip,injection:{targetClass:$scope.selectedClass,targetMethod:$scope.selectedMethod,localVariables:$scope.localVariables,code:$scope.code}}
													})
															.then(
																	function successCallback(
																			response) {
																		if (response)
																		{
																			Swal.fire(
																					  'Code injected',
																					  'Let the fun begin..',
																					  'success'
																					)
																		}
																	else
																		{
																		Swal.fire(
																				  'Injection failed :(',
																				  'you probably did something stupid..',
																				  'error'
																				)
																		}
																	});
									        	
									            $modalInstance.dismiss('inject');
									          }
								          $scope.cancel = function() {
								            $modalInstance.dismiss('cancel');
								          };
								        },
								        resolve: {
								          user: function() {
								            return $scope.selected;
								          }
								        }
								      });
								};

								setInterval(function(){ 
								$http({
									method : 'GET',
									url : 'agentOutputs'
								})
										.then(
												function successCallback(
														response) {
													$scope.outputs = [];
													angular
															.forEach(
																	response.data._embedded.agentOutputs,
																	function(
																			output) {
																		$scope.outputs.push(output.timestamp + " " + output.ip + ": " + output.content);
																	});
												});
								 }, 3000); 
								setInterval(function(){ 
									if ($scope.data == null || $scope.data.length == 0)
										{
								$http({
									method : 'GET',
									url : 'operators'
								})
										.then(
												function successCallback(
														response) {
													$scope.data = [];
													angular
															.forEach(
																	response.data._embedded.operators,
																	function(
																			operator) {
																		if (operator.alive)
																			{
																		$scope.data
																				.push({
																					ip : operator.ip,
																					url: operator._links.self.href,
																					title : operator.ip,
																					isAlive : operator.alive,
																					nodes : []
																				});
																			}
																	});

													$http({
														method : 'GET',
														url : 'agents'
													})
															.then(
																	function successCallback(
																			response) {
																		angular
																				.forEach(
																						response.data._embedded.agents,
																						function(
																								agent) {
																							var index = $scope.data.findIndex(operator => operator.ip == agent.ip);
																							if (index != -1)
																								{
																									$scope.data[index].nodes.push({
																										ip : agent.ip,
																										url: agent._links.self.href,
																										title :  agent.mainClass,
																										mainClass : agent.mainClass,
																										// isCurrent
																										// :
																										// agent.current,
																										nodrop : true
																									});
																								}
																							else
																								{
																									$scope.data.nodes.push({
																										ip : agent.ip,
																										url: agent._links.self.href,
																										title :  agent.mainClass,
																										mainClass : agent.mainClass,
																										// isCurrent
																										// :
																										// agent.current,
																										nodrop : true
																								});
																								}
																						});
																	});

												});}
								}, 3000); 
							} ]);
}());
