app.factory('IndexGetter', ['$http', function($http){
	return {
      postUserData: function(userId, userPassword, successFunction, errorFunction){

        var message = {
          'username' : userId,
          'password' : userPassword
        };
        $http({
          url: "/api/login",
          method: "POST",
          data: message,
        }).then(successFunction, errorFunction);
      },
      postRegisterUser: function(firstname,lastname, newUserName, newUserPassword, newUserEmail, successFunction, errorFunction) {
        console.log('Registering a new user');

        var message = {
          'first_name': firstname,
          'last_name' : lastname,
          'username' : newUserName,
          'password' : newUserPassword,
          'email'    : newUserEmail,
        };
        $http({
          url: "/api/signup",
          method: "POST",
          data: message,
        }).then(successFunction, errorFunction);
      }
    };
  }]);