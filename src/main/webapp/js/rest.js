var rest = new function() {

    var BASE = "/rest/missionbuilder";

    this.exportMissionAsText = function(missionServerId, callback) {
        $.getJSON(BASE + "/mission/" + missionServerId + "/export", callback);
    }

    this.downloadMissionFile = function(missionServerId, callback) {
        $.ajax({
            method : 'GET',
            url : BASE + "/mission/" + missionServerId + "/downloadmission",
            processData: false,
            success : callback
        });
    }

    this.downloadLocalizationFile = function(missionServerId, callback) {
        $.ajax({
            method : 'GET',
            url : BASE + "/mission/" + missionServerId + "/downloadlocalization",
            processData: false,
            success : callback
        });
    }

    this.exportMissionToDisk = function(missionServerId, callback, errorCallback) {
        $.ajax({
            method : 'PUT',
            url : BASE + "/mission/" + missionServerId + "/export",
            processData: false,
            success : callback,
            error: errorCallback
        });
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

    this.deleteMission = function(missionServerId, callback) {
        $.ajax({
                method : 'DELETE',
                url : BASE + '/missions/' + missionServerId,
                contentType: 'application/json',
                processData: false,
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
        $.getJSON(BASE + "/staticObjectTypes/" + countryId, callback);
    }

    this.getActionTypes = function(callback) {
        $.getJSON(BASE + "/actionTypes", callback);
    }

    this.getFormationTypes = function(groupType, callback) {
        $.getJSON(BASE + "/formationTypes/" + groupType, callback);
    }

    this.getLoadouts = function(planeType, callback) {
        $.getJSON(BASE + "/loadouts/" + planeType, callback);
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

    this.getSkins = function(callback) {
        $.getJSON(BASE + "/skins", callback);
    }

    this.getEffectTypes = function(callback) {
        $.getJSON(BASE + "/effectTypes", callback);
    }

    this.getVehicleMetadata = function(callback) {
        $.getJSON(BASE + "/vehicleTypes/metadata", callback);
    }

    this.getGameObjectMetadata = function(callback) {
        $.getJSON(BASE + "/gameObjectTypes/metadata", callback);
    }

    this.getMaps = function(callback) {
        $.getJSON(BASE + "/maps", callback);
    }
}