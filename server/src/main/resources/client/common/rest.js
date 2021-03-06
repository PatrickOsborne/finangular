var restModule = angular.module( 'JangularApp.rest', [] );

restModule.factory( 'restUtils', function( $http ) {

    function createHandleGetSuccess( callback, url ) {
        return function( data, status, headers, config ) {
            callback( data );
        };
    }

    function createHandleGetError( callback, url ) {
        return function( data, status, headers, config ) {
            console.log( url + ': ERROR' );
            console.log( arguments );
            callback( status, data );
        };
    }

    function createHandlePostSuccess( callback, url ) {
        return function handleSuccess( data, status, headers, config ) {
            console.log( url + ': SUCCESS' );
//            console.log( arguments );
            if ( callback )
            {
                callback( data );
            }
        };
    }

    function createHandlePostError( callback, url ) {
        return function handleError( data, status, headers, config ) {
            console.log( url + ': ERROR' );
            console.log( arguments );
            if ( callback )
            {
                callback( data, status, headers, config );
            }
        };
    }

    function doGet( url, successCallback, errorCallback ) {
        console.log( 'GET: ' + url );
        var promise = $http.get( url );
        promise.then( createHandleGetSuccess( successCallback, url ), createHandleGetError( errorCallback, url ) );
        return promise;
    }

    function doPost( url, successCallback, errorCallback, data, config ) {
        console.log( 'POST: ' + url );
        var promise = $http.post( url, data, config );
        promise.then( createHandlePostSuccess( successCallback, url ), createHandlePostError( errorCallback, url ) );
        return promise;
    }

    return {
        doGet:doGet,
        doPost:doPost
    }

} );

restModule.factory( 'restCommon', function( restUtils ) {

    function version( successCallback, errorCallback ) {
        var url = '/rest/version';
        console.log( 'looking up version: ' + url );
        return restUtils.doGet( url, successCallback, errorCallback );
    }

    function greeting( name, successCallback, errorCallback ) {
        var url = '/rest/greeting';
        if ( name )
        {
            url += '?name=' + name;
        }
        console.log( 'greeting: name: (' + name + '), url: (' + url + ')');
        return restUtils.doGet( url, successCallback, errorCallback );
    }

    return {
        version:version,
        greeting:greeting
    }

} );
