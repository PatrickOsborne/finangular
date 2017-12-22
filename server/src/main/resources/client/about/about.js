appModule.controller('About', function ($scope, $location, restCommon) {
    console.log( 'About init' );

    function handleVersionSuccess(versionData) {
        console.log(versionData);
        $scope.version = 'Version: ' + versionData.data.version;
        $scope.versionData = versionData.data;
    }

    function handleVersionError(status, data) {
        console.log(status);
        console.log(data);
        $scope.version = 'version lookup error';
    }

    function init() {
        $scope.version = '';

        restCommon.version(handleVersionSuccess, handleVersionError);
    }

    init();

} );