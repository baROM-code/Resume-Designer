angular.module('resume', []).controller('resumeController', function ($scope, $http) {
const contextPath = 'http://localhost:8888/resume-core'

    $scope.createNewResume = function () {
        console.log($scope.newResume);
        $http.post('http://localhost:8888/resume-core/api/v1/users', $scope.newResume)
            .then(function response(){
                $scope.newResume = null;
            });
    }

	
});