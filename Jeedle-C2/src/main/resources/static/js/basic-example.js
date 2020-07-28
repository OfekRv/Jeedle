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
										url : 'api/agents/' + model.id
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

								$scope.createMission = function(scope) {
									var nodeData = scope.$modelValue;
									$scope.arg;
									$scope.missionArgs = [];
								    $modal.open({
								        templateUrl: 'views/templates/createMission.html',
								        backdrop: true,
								        windowClass: 'modal',
								        controller: function($scope, $modalInstance) {
								          $scope.create = function() {
								        		$http({
													method : 'POST',
													url : 'api/missions',
													data: {
													        agent:nodeData.url,
													        type:$scope.missionType,
													        args:$scope.missionArgs
													      }
												})
														.then(function successCallback(response) {});
								        	
								            $modalInstance.dismiss('create');
								          }
								          $scope.addArg = function() {
                                          if ($scope.missionArgs == null)
                                          {
                                              $scope.missionArgs = [];
                                          }
                                          $scope.missionArgs.push($scope.arg);
                                          $scope.arg="";
                                      }

                                        $scope.removeArg = function(variable)
                                        {
                                            const index = $scope.missionArgs.indexOf(variable);
                                            if (index > -1) {
                                                $scope.missionArgs.splice(index, 1);
                                            }
                                        };

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
									url : 'api/missions?sort=creationTime,desc'
								})
										.then(
												function successCallback(
														response) {
													$scope.missions = [];
													angular
															.forEach(
																	response.data._embedded.missions,
																	function(
																			mission) {
																		var artifactContent;
																		if (mission.artifact != null)
																		{
																		    artifactContent = mission.artifact.content;
																		}
																		$scope.missions.push(
                                                                            {
                                                                                agentIp : mission.agent.ip,
                                                                                agentType : mission.agent.type,
                                                                                missionType : mission.type,
                                                                                missionArgs : mission.args,
                                                                                missionCreationTime : mission.creationTime,
                                                                                content : artifactContent
                                                                            }
																		);
																	});
												});
								 }, 3000);
								setInterval(function(){ 
									if ($scope.data == null || $scope.data.length == 0)
										{
								$http({
									method : 'GET',
									url : 'api/agents'
								})
										.then(
												function successCallback(
														response) {
													$scope.data = [];
													angular
															.forEach(
																	response.data._embedded.agents,
																	function(
																			agent) {
																		$scope.data
																				.push({
																					ip : agent.ip,
																					url: agent._links.self.href,
																					type: agent.type,
																					id : agent.id,
																					lastActive : agent.lastActive,
																					nodes : []
																				});
																	});
																	if ($scope.selectedAgent == null && $scope.data.length != 0)
																	{
																	    $scope.selectedAgent = $scope.data[0];
																	}
												});}
								}, 3000);
							} ]);
}());
