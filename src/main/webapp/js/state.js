var state = new function() {

    var appstate;

    this.MAP_WAITING_FOR_CLICK_AIR_GROUP = "MAP_WAITING_FOR_CLICK_AIR_GROUP";
    this.MAP_WAITING_FOR_CLICK_GROUND_GROUP = "MAP_WAITING_FOR_CLICK_GROUND_GROUP";
    this.MAP_WAITING_FOR_CLICK_STATIC_OBJECT_GROUP = "MAP_WAITING_FOR_CLICK_STATIC_OBJECT_GROUP";
    this.MAP_WAITING_FOR_CLICK_TRIGGER = "MAP_WAITING_FOR_CLICK_TRIGGER";
    this.MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS = "MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS";
    this.NORMAL = "NORMAL";
    this.DRAGGING_UNIT = "DRAGGING_UNIT";
    this.PLACING_WAYPOINT = "PLACING_WAYPOINT";
    this.PLACING_TRIGGER = "PLACING_TRIGGER";




    var selectedLocation = null;
    var currentMission = null;
    var selectedUnitGroup = null;
    var selectedStaticObjectGroup = null;
    var selectedWaypoint = null;
    var selectedTriggerZone = null;

    var dragTarget = null;

    this.deselectAll = function() {
        selectedUnitGroup = null;
        selectedStaticObjectGroup = null;
        selectedWaypoint = null;
        selectedTriggerZone = null;
        dragTarget = null;
        this.setState(state.NORMAL);
        $('#clickOnMapDiv').addClass('hidden');
        $("#map").css("cursor", "");
        maprenderer.redraw();
    }

    var currentCountry = 201;



    var selectionBox = {
        topX :0, topY :0, bottomX :0, bottomY :0
    }

    var stack = [];

    var filters = {
        'plane_group' : true,
        'ground_group' : true,
        'static_object' : true,
        'trigger' : true,
        'airfields' : false
    }

    this.setSelectionBox = function(topX, topY, bottomX, bottomY) {
        selectionBox.topX = topX;
        selectionBox.topY = topY;
        selectionBox.bottomX = bottomX;
        selectionBox.bottomY = bottomY;
    }

    this.getSelectionBox = function() {
        return selectionBox;
    }

    this.getState = function() {
        return appstate;
    }

    this.setState = function(nState) {
        appstate = nState;
    }

    this.getUnitGroupsForAllSides = function() {
        var ussr = currentMission.sides[101].unitGroups;
        var germany = currentMission.sides[201].unitGroups;
        return ussr.concat(germany);
    }

    this.getUnitGroupsForOtherSide = function() {
        var countryId = 201;
        if(currentCountry == 201) {
            countryId = 101;
        }
        return currentMission.sides[countryId].unitGroups;
    }

    this.setLocation = function(location) {
        selectedLocation = location;
    }

    this.getLocation = function() {
        return selectedLocation;
    }

    this.getX = function() {
        return selectedLocation.x;
    }

    this.getZ = function() {
        return selectedLocation.z;
    }

    this.getCurrentMission = function() {
        return currentMission;
    }

    this.setCurrentMission = function(mission) {
        currentMission = mission;

        // Re-set the unit group
        if(util.notNull(selectedUnitGroup)) {
            for(var a = 0; a < currentMission.sides[currentCountry].unitGroups.length; a++) {
                if(currentMission.sides[currentCountry].unitGroups[a].clientId === selectedUnitGroup.clientId) {
                    currentMission.sides[currentCountry].unitGroups[a] = selectedUnitGroup;
                    break;
                }
            }
        }
    }

    this.getSelectedStaticObjectGroup = function() {
        return selectedStaticObjectGroup;
    }

    this.setSelectedStaticObjectGroup = function(sog) {
        selectedStaticObjectGroup = sog;
        if(selectedStaticObjectGroup != null) {
            missionbuilder.handleObjectSelected(selectedStaticObjectGroup);
        } else {
            if(noneSelected()) {
                $('#object-properties').addClass('hidden');
            }
        }
    }

    this.getSelectedUnitGroup = function() {
        return selectedUnitGroup;
    }

    this.setSelectedUnitGroup = function(unitGroup) {
        selectedUnitGroup = unitGroup;
        if(unitGroup != null) {

            missionbuilder.handleObjectSelected(unitGroup);

            $('#object-properties').removeClass('hidden');
          //  $('#object-properties').css('top',50+$('#rightmenu').height() + 'px');
        } else {
            if(noneSelected()) {
                $('#object-properties').addClass('hidden');
            }
        }
        sidemenu.updateMenu();

    }

    this.getSelectedWaypoint = function() {
        return selectedWaypoint;
    }

    this.setSelectedWaypoint = function(waypoint) {
        selectedWaypoint = waypoint;
        if(waypoint != null) {
            missionbuilder.handleObjectSelected(waypoint);
        } else {
            if(noneSelected()) {
                $('#object-properties').addClass('hidden');
            }
        }
    }

    this.getDragTarget = function() {
        return dragTarget;
    }

    this.setDragTarget = function(nDragTarget) {
        dragTarget = nDragTarget;
    }

    this.getSelectedTriggerZone = function() {
        return selectedTriggerZone;
    }

    this.setSelectedTriggerZone = function(triggerZone) {
        selectedTriggerZone = triggerZone;
        if(triggerZone != null) {
            missionbuilder.handleObjectSelected(triggerZone);
        } else {
            if(noneSelected()) {
                $('#object-properties').addClass('hidden');
            }
        }
    }

    var noneSelected = function() {
        return selectedTriggerZone == null && selectedUnitGroup == null && selectedWaypoint == null && selectedStaticObjectGroup == null;
    }

    this.setCurrentCountry = function(countryCode) {
        currentCountry = countryCode;
        maprenderer.redraw();
    }

    this.getCurrentCountry = function() {
        return currentCountry;
    }

    this.setFilter = function(filter, enabled) {
        filters[filter] = enabled;
        maprenderer.redraw();
    }

    this.getFilter = function(filter) {
        return filters[filter];
    }

    var lastOP = "PUSH";

    this.pushMissionState = function(mission) {
        if(util.notNull(mission)) {
            stack.push(JSON.parse(JSON.stringify(mission)));
            lastOP = "PUSH";
            console.log("Pushed onto stack: " + mission);
        }
    }

    this.popMissionState = function() {
        if(stack.length > 0) {
            if(lastOP == "PUSH") {
                stack.pop(); // This is the current state.
            }

            var missionState = stack.pop();      // This is the state before the last edit.
            console.log("Popped from stack: " + missionState);
            lastOP = "POP";
            return missionState;
        }
        return null;
    }
}