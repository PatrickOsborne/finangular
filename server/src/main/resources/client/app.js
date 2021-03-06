'use strict';

var appModule = angular.module('JangularApp.app', ['ngRoute', 'JangularApp.shared', 'JangularApp.rest']);

appModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'home/home.html', controller: 'Home'});
    $routeProvider.when('/about', {templateUrl: 'about/about.html', controller: 'About'});
    $routeProvider.otherwise({redirectTo: '/'})
}]);

appModule.controller('appController', function ($scope, $location, $window, restCommon) {
    console.log('appController init');

    function navigateTo(place) {
        console.log('navigateTo: place: ' + place);
        if (place) {
            $location.path('/' + place);
        }
        else {
            $location.path('/');
        }
    }

    function redirectHome() {
        var url = '/client/index.html';
        console.log(url);
        $window.location.href = url;
    }

    function handleVersionSuccess(versionData) {
        console.log(versionData);
        $scope.version = 'Version: ' + versionData.version;
        $scope.versionData = versionData;
    }

    function handleVersionError(status, data) {
        console.log(status);
        console.log(data);
        $scope.version = 'version lookup error';
    }

    function init() {
        $scope.version = '';
        $scope.navigateTo = navigateTo;

        restCommon.version(handleVersionSuccess, handleVersionError);
    }

    init();

});

