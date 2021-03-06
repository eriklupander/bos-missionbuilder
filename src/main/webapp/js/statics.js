var statics = new function() {

    var airfields = [];
//    var vehicleMetadata;
//
//    rest.getVehicleMetadata(function(data) {
//        vehicleMetadata = data;
//    });

    var gameObjectMetadata;

    rest.getGameObjectMetadata(function(data) {
        gameObjectMetadata = data;
    });

    this.getGameObjectMetadata = function() {
        return gameObjectMetadata;
    }

    this.loadAirfields = function() {
        rest.loadAirfields(function(data) {
            airfields = data;
        });
    }

    this.getAirfields = function() {
        return airfields;
    }

    this.getAltitudes = function() {
        var arr = new Array();
        arr.push(500);
        arr.push(1000);
        arr.push(1500);
        arr.push(2000);
        arr.push(3000);
        arr.push(4000);
        arr.push(5000);
        arr.push(7000);
        arr.push(9000);
        arr.push(0);
        return arr;
    }

    this.getGroupSizes = function() {
        var arr = new Array();
        for(var a = 1; a < 24; a++) {
            arr[a-1] = a;
        }
        return arr;
    }

    this.getSkills = function() {
        var arr = new Array();
        arr.push({"value":4, "name":"Ace"});
        arr.push({"value":3, "name":"Veteran"});
        arr.push({"value":2, "name":"Regular"});
        arr.push({"value":1, "name":"Novice"});
        arr.push({"value":0, "name":"Player"});
        return arr;
    }

    this.getSkillsAIOnly = function() {
        var arr = new Array();
        arr.push({"value":4, "name":"Ace"});
        arr.push({"value":3, "name":"Veteran"});
        arr.push({"value":2, "name":"Regular"});
        arr.push({"value":1, "name":"Novice"});
        return arr;
    }
}