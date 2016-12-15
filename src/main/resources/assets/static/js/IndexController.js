app.controller('IndexController', ['$scope', '$window', 'IndexGetter', 'SessionStorage', function($scope, $window,IndexGetter, SessionStorage) { 
    var successFunction = function(data) {
        SessionStorage.set('accessToken', data['data']['accesstoken']);
        SessionStorage.set('userid',data['data']['userid']);
        $window.location.href = '/donations.html';
    }


    var registerSuccessFunction = function(data) {
        SessionStorage.set('userId', data['data']['userId']);
        $scope.loginUser($scope.newUserName, $scope.newUserPassword, false);
    }

    var errorFunction = function(data) {
        $window.location.href = '/index.html';
    }

    $scope.userId = '';
    $scope.userPassword = '';

    $scope.loginUser = function(userId, userPassword) {
        $scope.isActive = true;
        $scope.userId = userId;
        $scope.userPassword = userPassword;
        IndexGetter.postUserData($scope.userId, $scope.userPassword, successFunction, errorFunction);
        
    };

    $scope.registerUser = function(firstname,lastname,newUserName, newUserPassword, newUserEmail) {
        $scope.firstname = firstname
        $scope.lastname = lastname
        $scope.newUserName = newUserName;
        $scope.newUserPassword = newUserPassword;
        $scope.newUserEmail = newUserEmail;
        IndexGetter.postRegisterUser($scope.firstname,$scope.lastname,$scope.newUserName, $scope.newUserPassword, $scope.newUserEmail, 
            registerSuccessFunction, errorFunction);
    }
}]);