/**
 * Created by purvi on 12/13/16.
 */
app.factory('DonationGetter', ['$http', function($http){
    return {
        addDonation: function(userid,item, value,category,deductible,timestamp, successFunction, errorFunction){
            console.log(deductible);
            var message = {
                "userid": parseInt(userid) ,
                "item": item,
                "value": parseFloat(value),
                "deductible": parseFloat(deductible),
                "category": category,
                "timestamp":timestamp
            };
            $http({
                url: "/api/donations",
                method: "POST",
                data: message,
            }).then(successFunction, errorFunction);
        },
        getDonations: function(userid, successFunction, errorFunction) {
            console.log('Getting all donations');
            $http({
                url: "/api/donations",
                method: "GET",
                params: {
                    'userid' : userid
                }
            }).then(successFunction, errorFunction);
        }
    };
}]);