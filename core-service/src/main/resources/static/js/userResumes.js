var app = angular.module('resume', ['ngStorage']);
app.controller('resumeController', function ($scope, $http, $localStorage) {

    $scope.loadResumeById = function (id) {
        $http.get('http://localhost:8888/resume-core/api/v1/resumes/' + id)
            .then(function (response) {
                $scope.resume = response.data;
                $scope.works = $scope.resume.works;
                $scope.educations = $scope.resume.educations;
            });
    };

    $scope.loadResumesByUser = function () {
        $http.get('http://localhost:8888/resume-core/api/v1/resumes/user/')
            .then(function (response) {
                $scope.resumes = response.data;
            });
    };

    $scope.editResume = function (id) {
        $scope.isNewResume = false;
        $scope.resumeId = id;
        $scope.loadResumeById(id);
        $scope.btnEditResume = true;
    }

    $scope.newResume = function () {
        $scope.loadWorksByUser();
        $scope.loadEducationsByUser()
        $scope.resume = null;
        $scope.btnEditResume = true;
        $scope.isNewResume = true;
    }

    $scope.saveResume = function () {
        $scope.resume.userId = $localStorage.resumeData.userId;
        if ($scope.isNewResume) {
            $http.post('http://localhost:8888/resume-core/api/v1/resumes', $scope.resume)
                .then(function (){
                    $scope.loadResumesByUser();
                });
        } else {
            $http.put('http://localhost:8888/resume-core/api/v1/resumes', $scope.resume)
                .then(function (){
                    $scope.loadResumesByUser();
                });
            $scope.resumeId = null;
        };
        $scope.resume = null;
        $scope.btnEditResume = false;
    }

    $scope.loadWorksByUser = function () {
        $http.get('http://localhost:8888/resume-core/api/v1/works/user/')
            .then(function (response) {
                $scope.works = response.data;
            });
    };

    $scope.updateWork = function (work) {
        $http.put('http://localhost:8888/resume-core/api/v1/works', work)
            .then(function () {
                $scope.loadWorksByUser();
            });
    };

    $scope.deleteWork = function (id) {
        $http.delete('http://localhost:8888/resume-core/api/v1/works/' + id)
            .then(function () {
                $scope.loadWorksByUser();
            });
    };

    $scope.createNewWork = function () {
        if ($localStorage.resumeData) {
            $scope.newWork.userId = $localStorage.resumeData.userId;
            $http.post('http://localhost:8888/resume-core/api/v1/works', $scope.newWork)
                .then(function () {
                    $scope.newWork = null;
                    $scope.btnAddWork = !$scope.btnAddWork;
                    $scope.loadWorksByUser();
                });
        }
    }

    $scope.loadEducationsByUser = function () {
        $http.get('http://localhost:8888/resume-core/api/v1/educations/user/')
            .then(function (response) {
                $scope.educations = response.data;
            });
    };

    $scope.updateEducation = function (education) {
        $http.put('http://localhost:8888/resume-core/api/v1/educations', education)
            .then(function () {
                $scope.loadEducationsByUser();
            });
    };

    $scope.deleteEducation = function (id) {
        $http.delete('http://localhost:8888/resume-core/api/v1/educations/' + id)
            .then(function () {
                $scope.loadEducationsByUser();
            });
    };

    $scope.createNewEducation = function () {
        if ($localStorage.resumeData) {
            $scope.newEducation.userId = $localStorage.resumeData.userId;
            $http.post('http://localhost:8888/resume-core/api/v1/educations', $scope.newEducation)
                .then(function () {
                    $scope.newEducation = null;
                    $scope.btnAddEducation = !$scope.btnAddEducation;
                    $scope.loadEducationsByUser();
                });
        }
    }

    if ($localStorage.resumeData) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.resumeData.token;
        $scope.loadResumesByUser();
    }
});