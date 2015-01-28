var state = new function() {

    var state = "NORMAL";

    this.MAP_WAITING_FOR_CLICK_AIR_GROUP = "MAP_WAITING_FOR_CLICK_AIR_GROUP";
    this.MAP_WAITING_FOR_CLICK_GROUND_GROUP = "MAP_WAITING_FOR_CLICK_GROUND_GROUP";
    this.MAP_WAITING_FOR_CLICK_TRIGGER = "MAP_WAITING_FOR_CLICK_TRIGGER";
    this.MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS = "MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS";
    this.NORMAL = "NORMAL";
    this.DRAGGING_UNIT = "DRAGGING_UNIT";
    this.PLACING_WAYPOINT = "PLACING_WAYPOINT";
    this.PLACING_TRIGGER = "PLACING_TRIGGER";

    var selectedLocation = null;
    var currentMission = null;
    var selectedUnitGroup = null;
    var selectedWaypoint = null;
    var selectedTriggerZone = null;

    var currentCountry = 201;

    var dragTarget = null;

    var stack = [];

    var filters = {
        'plane_group' : true,
        'ground_group' : true,
        'static_object' : true,
        'trigger' : true
    }

    this.getState = function() {
        return state;
    }

    this.setState = function(nState) {
        state = nState;
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
            for(var a = 0; a < currentMission.axis.unitGroups.length; a++) {
                if(currentMission.axis.unitGroups[a].clientId === selectedUnitGroup.clientId) {
                    selectedUnitGroup = currentMission.axis.unitGroups[a];
                    break;
                }
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
            $('#object-properties').css('top',50+$('#rightmenu').height() + 'px');
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
        return selectedTriggerZone == null && selectedUnitGroup == null && selectedWaypoint == null;
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