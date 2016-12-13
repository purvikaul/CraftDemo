app.controller('SubscriptionCtrl', ['$scope', '$window','$filter', 'SessionStorage', 'SubscriptionGetter', 'geolocationService','EmergenciesGetter'
    , function ($scope,$window, $filter, SessionStorage, SubscriptionGetter, geolocationService,EmergenciesGetter) {

        console.log("In SubscriptionCtrl");

        $scope.accessToken = SessionStorage.get('accessToken');
        $scope.userId = SessionStorage.get('userId');

        $scope.chkbxs = EmergenciesGetter;

        $scope.params = [
            "Earthquake", "Hurricane", "Tornado", "Flood", "Shooting"
        ];

        $scope.mylocation = '';
        $scope.nearMe = false;
        $scope.flag = false;
        $scope.length = 0;
        $scope.isChecked = false;

        var counter = 0;

        var emergencySuccessFunction = function(data) {
            console.log("All is well with subscriptions!");
            SessionStorage.set('subscriptionId', data['data']['userSubscriptionId']);
            SessionStorage.set('timestamp', data['data']['timestamp']);
            counter++;
            console.log("counter:" + counter);
            if(counter == $scope.length){
                $window.location.href = '/locationsubs';
            }
        };

        var successFunction = function(data) {
            console.log("All is well with subscriptions!");
            SessionStorage.set('subscriptionId', data['data']['userSubscriptionId']);
            SessionStorage.set('timestamp', data['data']['timestamp']);
        }

        var errorFunction = function (data) {
            console.log("In errorFunction");
            console.log("Something went wrong: " + data['data']);

            $scope.flag = true;
        };

        function UserPosition(position) {
            console.log("in UserPosition");

            var lat = position.coords.latitude;
            var lng = position.coords.longitude;
            $scope.mylocation = {latitude: lat, longitude: lng};

            console.log("latitude" + lat);
        }

        $scope.onClickNearMe = function () {
            console.log("In onClickNearMe");
            if ($scope.nearMe) {
                var d = geolocationService.getCurrentPosition().then(UserPosition);
                var subscriptionList = getSubscriptionList();
                d.then(function() {
                    for(i = 0; i < subscriptionList.length; i++){
                        console.log("Subscribing for:"+subscriptionList[i]);

                        SubscriptionGetter.postEmergenciesAtLocationSubscription($scope.userId, $scope.mylocation,
                            $scope.accessToken, subscriptionList[i], successFunction, errorFunction)
                    }
                });
            }
        };

        $scope.subscribeToShelterInfo=function(){
            console.log("In subscribeToShelterInfo");
            var subscriptionList = getSubscriptionList();
            if($scope.shelterInfo) {
                if (!$scope.nearMe) {
                    var d = geolocationService.getCurrentPosition().then(UserPosition);
                    d.then(function() {
                        for(i = 0; i < subscriptionList.length; i++) {
                            console.log("Subscribing for:"+subscriptionList[i]);
                            SubscriptionGetter.postEmergenciesLocationWithSheltersSubscription($scope.userId,
                                $scope.mylocation, $scope.accessToken, subscriptionList[i],
                                successFunction, errorFunction)
                        }
                    });
                } else {
                    for(i = 0; i < subscriptionList.length; i++){
                        console.log("Subscribing for:"+subscriptionList[i]);
                        SubscriptionGetter.postEmergenciesLocationWithSheltersSubscription($scope.userId,
                            $scope.mylocation, $scope.accessToken, subscriptionList[i],
                            successFunction, errorFunction)
                    }
                }
            }
        };


        function getSubscriptionList() {
            console.log("In getSubscriptionList");
            var subscriptionList = $filter('filter')($scope.chkbxs, {val: true});
            console.log("Just testing filter"+subscriptionList);
            for (var i = 0; i < subscriptionList.length; i++) {
                subscriptionList[i] = angular.lowercase(subscriptionList[i].label);
                console.log("the list "+subscriptionList[i]);
            }
            $scope.length = subscriptionList.length;
            console.log("length of subscriptionList:"+$scope.length);
            return subscriptionList;
        }

        $scope.subscribeToEmergencies = function () {
            $scope.isActive = true;
            var i;
            console.log("SAFIR" + $scope.isChecked);
            if($scope.isChecked == true) {
                var subscriptionList = $scope.params;
            }
            else {
                var subscriptionList = getSubscriptionList();
            }
            console.log(subscriptionList);
            $scope.accessToken = SessionStorage.get('accessToken');
            $scope.userId = SessionStorage.get('userId');
            for(i = 0; i < subscriptionList.length; i++){
                console.log("Subscribing for:" + subscriptionList[i]);
                var successFunctionUsed = successFunction;
                if(i == subscriptionList.length - 1) {
                    successFunctionUsed = emergencySuccessFunction
                }
                SubscriptionGetter.postEmergenciesSubscription($scope.userId, $scope.accessToken,
                    subscriptionList[i], emergencySuccessFunction, errorFunction);
            }
        }
    }]
);
