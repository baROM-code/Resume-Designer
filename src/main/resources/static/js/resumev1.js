var app = angular.module('resume', ['ngStorage']);
app.controller('resumev1Controller', function ($scope, $http, $localStorage) {
    $scope.loadResume = function (id) {
        $http.get('http://localhost:8888/resume-core/api/v1/resumes/' + id)
            .then(function (response) {
                $scope.resume = response.data;
            });
    };
    if ($localStorage.resumeData) {
        $scope.loadResume($localStorage.resumeData.userId);
    }
});

app.filter('scheduleType', function () {
    return function (item) {
        if (item == 'FULL') { return 'Полный день';}
        if (item == 'FLEXIBLE') { return 'Гибкий график';}
        if (item == 'DISTANT') { return 'Удаленная работа';}
        if (item == 'PART') { return 'Подработка';}
        return '';
    };
});

window.addEventListener('DOMContentLoaded', event => {

    // Activate Bootstrap scrollspy on the main nav element
    const sideNav = document.body.querySelector('#sideNav');
    if (sideNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#sideNav',
            offset: 74,
        });
    }
    ;

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});
