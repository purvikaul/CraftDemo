/**
 * Created by purvi on 12/13/16.
 */
app.controller('DonationCtrl',['$scope','$filter','$window', 'SessionStorage', 'DonationGetter',function ($scope,$filter,$window,SessionStorage,DonationGetter){
    $scope.item = '';
    $scope.value ='';
    $scope.categories = ["Education","Health Care","Child Welfare"];
    $scope.category =''
    $scope.deductible = '';
    $scope.userId = SessionStorage.get("userid");
    $scope.donations = [];

    x2js = new X2JS();
    var xmlDoc;

    $scope.getXML = function () {
        console.log(xmlDoc);
        console.log("Download button clicked");
        var blob = new Blob([
            xmlDoc
        ], {type: "text/plain;charset=utf-8"});
        saveAs(blob, "donations"+Date.now().toString() +".xml");
    }

    $scope.logoutUser = function () {
        SessionStorage.remove();

        $window.location.href = '/index.html';
    }


    var successFunction = function () {
        console.log("Added Donations");
        $scope.item = '';
        $scope.value ='';
        $scope.category ='';
        $scope.deductible = '';
        $scope.loadDonations();
    }

    var loadSuccessFunction = function (data) {
        $scope.donations = [];
        console.log("In loadSuccess Function");
        for(var obj in data['data']["donations"]){
            console.log('Donation'+ obj + data['data']["donations"][obj]);
            $scope.donations.push(data['data']["donations"][obj]);
        }
        dons = $scope.donations;
        console.log($scope.donations + " " + $scope.donations.length);
        xmlDoc = x2js.json2xml_str(
            {
                'donations': {
                    'donation': $scope.donations
                }
            }
        );
        console.log("XML: " + xmlDoc);
    }

    var errorFunction = function(data) {
        console.log("Something went wrong: " + data);
    }

    $scope.loadDonations = function () {
        console.log("In loadDonations");
        DonationGetter.getDonations($scope.userId,loadSuccessFunction,errorFunction);
    }

    $scope.addDonation = function () {
        timestamp = Date.now();
        $scope.deductible = parseFloat($scope.value) * 0.3;
        DonationGetter.addDonation($scope.userId,$scope.item,$scope.value,$scope.category,$scope.deductible,timestamp,successFunction,errorFunction);
    }


}
]);