var indexApp = angular.module('indexApp', ['ngMaterial']);
indexApp.controller('IndexCtrl', ['$scope', '$http', '$mdDialog', '$mdMedia', function ($scope, $http, $mdDialog, $mdMedia) {

	$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');

	$scope.showRequestPopup = function(ev) {
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'dialog.tmpl.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
			fullscreen: useFullScreen
		})
		.then(function(arr) {
			$http({
				method: 'GET',
				url: '/requestSubmit?targetUrl=' + arr[0] + '&targetCssPath=' + arr[1]
			}).then(function successCallback(response) {
				console.log(response);
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						.title('Request Submitted Successfully')
						.textContent(response.data.msg)
						.ariaLabel('Simple Scraper')
						.ok('OK')
						.targetEvent(ev)
				);

			}, function errorCallback(response) {
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						.title('Request Submitted Successfully')
						.textContent(response.data.msg)
						.ariaLabel('Simple Scraper')
						.ok('OK')
						.targetEvent(ev));

			});

		}, function() {

		});
		$scope.$watch(function() {
			return $mdMedia('xs') || $mdMedia('sm');
		}, function(wantsFullScreen) {
			$scope.customFullscreen = (wantsFullScreen === true);
		});
	};
	
	
	$scope.showResultPopup = function(ev) {
		
		$http({
			method: 'GET',
			url: '/resultsList?startId=0&size=10'
		}).then(function successCallback(response) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
			$mdDialog.show({
				controller: DialogController2,
				templateUrl: 'show.results.tmpl.html',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose:true,
				locals : {
	                    scrapes : response.data
	            }, 
				fullscreen: useFullScreen
			})
			.then(function(arr) {

			}, function() {
			});
			$scope.$watch(function() {
				return $mdMedia('xs') || $mdMedia('sm');
			}, function(wantsFullScreen) {
				$scope.customFullscreen = (wantsFullScreen === true);
			});
		}, function errorCallback(response) {
			alert(response);
		});
		

	};
}]);
function DialogController($scope, $mdDialog) {
	$scope.targetUrl = "";
	$scope.targetCssPath = "";
	$scope.hide = function() {
		$mdDialog.hide();
	};
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
	$scope.submit = function(answer) {
		var arr = [$scope.targetUrl, $scope.targetCssPath];
		$mdDialog.hide(arr);
	};
}
function DialogController2($scope, $mdDialog, scrapes) {
	$scope.scrapes = scrapes;
	
	$scope.hide = function() {
		$mdDialog.hide();
	};
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
	$scope.answer = function(answer) {
		var arr = [$scope.targetUrl, $scope.targetCssPath];
		$mdDialog.hide(arr);
	};
}

