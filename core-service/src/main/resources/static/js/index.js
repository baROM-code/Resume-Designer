angular.module('resume', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    if ($localStorage.resumeData) {
        try {
            let jwt = $localStorage.resumeData.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.resumeData;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.resumeData) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.resumeData.token;
            $scope.nickname = $localStorage.resumeData.username;
        }
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8888/resume-core/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    let payload = JSON.parse(atob(response.data.token.split('.')[1]));
                    $localStorage.resumeData = {userId: payload.userid, username: $scope.user.username, token: response.data.token};
                    $scope.nickname = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.resumeData;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.resumeData) {
            return true;
        } else {
            return false;
        }
    };


});