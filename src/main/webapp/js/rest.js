var rest = new function() {

    var BASE = "/rest/missionbuilder";

    this.exportMissionAsText = function(missionServerId, callback) {
        $.getJSON(BASE + "/mission/" + missionServerId + "/export", callback);
    }

    this.createMission = function(mission, callback) {
        $.ajax({
                method : 'POST',
                url : BASE + '/mission',
                contentType: 'application/json',
                processData: false,
                data: JSON.stringify(
                    mission
                ),
                success : callback
            }
        )
    }

    this.updateMission = function(mission, callback) {
        $.ajax({
                method : 'PUT',
                url : BASE + '/mission/' + mission.serverId,
                contentType: 'application/json',
                processData: false,
                data: JSON.stringify(
                    mission
                ),
                success : callback
            }
        )
    }

    this.getPlaneTypes = function(countryId, callback) {
        $.getJSON(BASE + "/planeTypes/" + countryId, callback);
    }

    this.getVehicleTypes = function(countryId, callback) {
        $.getJSON(BASE + "/vehicleTypes/" + countryId, callback);
    }

    this.getStaticObjectTypes = function(countryId, callback) {
        $.getJSON(BASE + "/vehicleTypes/" + countryId, callback);
    }

    this.getActionTypes = function(callback) {
        $.getJSON(BASE + "/actionTypes", callback);
    }

    this.getFormationTypes = function(callback) {
        $.getJSON(BASE + "/formationTypes", callback);
    }

    this.getMissions = function(callback) {
        $.getJSON(BASE + "/missions", callback);
    }

    this.getMission = function(serverId, callback) {
        $.getJSON(BASE + "/mission/" + serverId, callback);
    }

    this.loadAirfields = function(callback) {
        $.getJSON(BASE + "/airfields", callback);
    }
}