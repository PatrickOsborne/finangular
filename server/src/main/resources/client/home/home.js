appModule.controller('Home', function ($scope, $location, restCommon) {
    console.log('Home init');

    $scope.greeting = '';

    function handleGreetingSuccess(result) {
        console.log('greeting success');
        console.log(result);
        $scope.greeting = result.data.content;
        console.log('greeting blap: ' + $scope.greeting);
    }

    function handleGreetingError(status, data) {
        console.log('greeting error');
        console.log(status);
        console.log(data);
        $scope.greeting = 'greeting error';
    }

    function init() {
        restCommon.greeting('', handleGreetingSuccess, handleGreetingError);
    }

    init();
});