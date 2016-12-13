app.controller('IndexController', ['$scope', '$window', 'IndexGetter', 'SessionStorage', function($scope, $window,IndexGetter, SessionStorage) { 
    var successFunction = function(data) {
        console.log(data['data']['accessToken']);
        SessionStorage.set('accessToken', data['data']['accessToken']);
        SessionStorage.set('username', data['data']['username']);
        $window.location.href = '/home';
    }

    // var subscribeSuccessFunction = function(data) {
    //     console.log("SAFIR-->All is well!");
    //     console.log(data['data']['accessToken']);
    //     SessionStorage.set('accessToken', data['data']['accessToken']);
    //     $window.location.href = '/subscriptions';
    //     // $window.location.href = '/preferences';

    // }

    var registerSuccessFunction = function(data) {
        console.log("registration was a success!");
        SessionStorage.set('userId', data['data']['userId']);
        $scope.loginUser($scope.newUserName, $scope.newUserPassword, false);
    }

    var errorFunction = function(data) {
        console.log("Something went wrong: " + data);
    }

    $scope.userId = '';
    $scope.userPassword = '';

    $scope.loginUser = function(userId, userPassword) {
        $scope.isActive = true;
        $scope.userId = userId;
        $scope.userPassword = userPassword;
            console.log('It works');
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