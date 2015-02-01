var missionbuilder = new function() {

    var BASEPATH = "/rest/missionbuilder";

    this.initMap = function() {
        maprenderer.renderMap();
    }

    this.exportMissionAsText = function() {
        if(!(util.notNull(state.getCurrentMission()) && util.notNull(state.getCurrentMission().serverId))) {
            alert("Please load a mission first");
            return;
        }
        rest.exportMissionAsText(state.getCurrentMission().serverId, function(data) {
            window.prompt("Copy to clipboard: Ctrl+C, Enter", data);
        });
    }

    this.selectCurrentCountry = function(countryCode) {
        state.setCurrentCountry(countryCode);

        $('.countryCheckedIcon').addClass('hidden');
        $('#' + countryCode).removeClass('hidden');
    }

    this.toggleMapFilter = function(toggleId) {
        var obj = $('#' + toggleId + '_check');
        if($(obj).hasClass('hidden')) {
            $(obj).removeClass('hidden');
        } else {
            $(obj).addClass('hidden');
        }
        state.setFilter(toggleId, !$(obj).hasClass('hidden'));
    }

    this.openCreateDialog = function() {
        $('#createMissionModal').modal({backdrop:'static'});
    }

    this.editMission = function() {
        var src = $('#mission-edit-tpl').html();
        var template = Handlebars.compile(src);
        var html    = template(state.getCurrentMission());
        $('#edit-mission-body').html(html);
        $('#editMissionModal').modal({backdrop:'static'});
    }

    this.saveMission = function() {

        state.getCurrentMission().name = $('#edit-mission-name').val();
        state.getCurrentMission().date = $('#edit-mission-date').val();
        state.getCurrentMission().time = $('#edit-mission-time').val();
        state.getCurrentMission().description = $('#edit-mission-desc').val();

        rest.updateMission(state.getCurrentMission(), function(data) {
            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

    this.openLoadMissionDialog = function() {
        rest.getMissions(function(data) {
            $('#load-mission-body').empty();
            var tpl = '<div class="list-group">';
            for(var a = 0; a < data.length; a++) {
                tpl += '<a class="list-group-item" data-dismiss="modal" onclick="missionbuilder.loadMission(\''+ data[a].serverId + '\');"><h4 class="list-group-item-heading">' + data[a].name + '</h4>';
                tpl += '<p class="list-group-item-text">' + data[a].description + '</p></a>';
            }
            tpl += '</div>';
            $('#load-mission-body').html(tpl);
            $('#loadMissionModal').modal({backdrop:'static'});
        });
    }

    this.loadMission = function(missionId) {
        rest.getMission(missionId, function(data) {
            state.setCurrentMission(data);
            //state.pushMissionState(data);
            maprenderer.redraw();
            $('#mission-name').text(data.name);
            $('#rightmenu').removeClass('hidden');
            $('#country-select').removeClass('hidden');
        });
    }

    this.openCreateUnitDialog = function (screenToWorld) {

        rest.getPlaneTypes(state.getCurrentCountry(), function(data) {
            var src = $('#planes-tpl').html();
            var template = Handlebars.compile(src);
            var html    = template(data);
            $('#air-group-type').html(html);

//            for(var a = 0 ; a < data.length; a++) {
//                $(unitSelect).append('<option value="' + data[a] + '">' + data[a] + '</option>');
//            }
        });
        $('#createUnitGroupModal').modal({backdrop:'static'});
        $("#map").css("cursor", "");
        $('#clickOnMapDiv').addClass('hidden');
        state.setState(state.NORMAL);
        state.setLocation(screenToWorld);
    };

    this.openCreateGroundUnitDialog = function (screenToWorld) {

        rest.getVehicleTypes(state.getCurrentCountry(), function(data) {
            var unitSelect = $('#create-ground-unit-group-type');
            $(unitSelect).empty();
            for(var a = 0 ; a < data.length; a++) {
                $(unitSelect).append('<option value="' + data[a] + '">' + data[a] + '</option>');
            }
        });
        $('#createGroundUnitGroupModal').modal({backdrop:'static'});
        $("#map").css("cursor", "");
        $('#clickOnMapDiv').addClass('hidden');
        state.setState(state.NORMAL);
        state.setLocation(screenToWorld);
    };

    this.openCreateStaticObjectDialog = function (screenToWorld) {

        rest.getVehicleTypes(state.getCurrentCountry(), function(data) {
            var unitSelect = $('#create-static-object-group-type');
            $(unitSelect).empty();
            for(var a = 0 ; a < data.length; a++) {
                $(unitSelect).append('<option value="' + data[a] + '">' + data[a] + '</option>');
            }
        });
        $('#createStaticObjectGroupModal').modal({backdrop:'static'});
        $("#map").css("cursor", "");
        $('#clickOnMapDiv').addClass('hidden');
        state.setState(state.NORMAL);
        state.setLocation(screenToWorld);
    };

    this.addGroundUnitGroupToMission = function() {
        var countryId = state.getCurrentCountry();
        var unitGroup = {
            x : state.getX(),
            y : 0,
            z : state.getZ(),
            countryId : countryId,
            type : $('#create-ground-unit-group-type').val(),
            name : $('#create-ground-unit-group-name').val(),
            size : $('#create-ground-unit-group-size').val(),
            description : $('#create-ground-unit-group-desc').val(),
            aiLevel : $('#create-ground-unit-group-skill').val(),
            yOri : 90,
            groupType : "GROUND_GROUP",
            clientId : new Date().getTime()
        }

        var currentMission = state.getCurrentMission();
        state.pushMissionState(currentMission);

        var side = currentMission.sides[state.getCurrentCountry()];
        side.unitGroups.push(unitGroup);
        rest.updateMission(currentMission, function(data) {

            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

    this.addUnitGroupToMission = function() {
        var countryId = state.getCurrentCountry();
        var unitGroup = {
            x : state.getX(),
            y : $('#create-unit-group-altitude').val(),
            z : state.getZ(),
            type : $('#create-unit-group-type').val(),
            name : $('#create-unit-group-name').val(),
            size : $('#create-unit-group-size').val(),
            description : $('#create-unit-group-desc').val(),
            aiLevel : $('#create-unit-group-skill').val(),
            loadout : $('#create-unit-group-loadout').val(),
            countryId : countryId,
            yOri : 90,
            groupType : "AIR_GROUP",
            clientId : new Date().getTime()
        }

        var currentMission = state.getCurrentMission();
        state.pushMissionState(currentMission);

        var side = currentMission.sides[state.getCurrentCountry()];
        side.unitGroups.push(unitGroup);
        rest.updateMission(currentMission, function(data) {

            state.setCurrentMission(data);

            maprenderer.redraw();
        });
    }

    this.createMission = function() {
        // Post basic stuff to server, get template document back
        var mission = {
            "name": $('#create-mission-name').val(),
            "description": $('#create-mission-desc').val(),
            "date": $('#create-mission-date').val(),
            "time": $('#create-mission-time').val()
        }
        rest.createMission(mission, handleMissionCreateResponse);
    }

    this.addFlightGroup = function() {
        $('#clickOnMapDiv').removeClass('hidden');
        $("#map").css("cursor", "crosshair");
        $(document).on('mousemove', function(e){
            $('#clickOnMapDiv').css({
                left:  e.pageX,
                top:   e.pageY
            });
        });
        state.setState(state.MAP_WAITING_FOR_CLICK_AIR_GROUP);
    }

    this.addGroundGroup = function() {
        $('#clickOnMapDiv').removeClass('hidden');
        $("#map").css("cursor", "crosshair");
        $(document).on('mousemove', function(e){
            $('#clickOnMapDiv').css({
                left:  e.pageX,
                top:   e.pageY
            });
        });
        state.setState(state.MAP_WAITING_FOR_CLICK_GROUND_GROUP);
    }

    this.objectSelected = function(object) {
        // TODO fix the differentiated selected stuff. Use unified selectedObject thing instead.
        state.setDragTarget(object);
        if(object.objectType == 'UNIT_GROUP') {
            state.setSelectedUnitGroup(object);
            state.setSelectedWaypoint(null);
            state.setSelectedTriggerZone(null);
        } else if(object.objectType == 'WAYPOINT') {
            state.setSelectedWaypoint(object);
            state.setSelectedUnitGroup(null);
            state.setSelectedTriggerZone(null);
        }  else if(object.objectType == 'TRIGGER') {
            state.setSelectedWaypoint(null);
            state.setSelectedUnitGroup(null);
            state.setSelectedTriggerZone(object);
        }

        maprenderer.redraw();
    }

    this.addWaypoint = function() {
        if(util.notNull(state.getSelectedUnitGroup())) {
            $('#clickOnMapDiv').removeClass('hidden');
            $("#map").css("cursor", "crosshair");
            $(document).on('mousemove', function(e){
                $('#clickOnMapDiv').css({
                    left:  e.pageX,
                    top:   e.pageY
                });
            });
            state.setState(state.PLACING_WAYPOINT);
        } else {
            alert("Please select an object first.");
        }
    }

    this.addTriggerZone = function() {
        $('#clickOnMapDiv').removeClass('hidden');
        $("#map").css("cursor", "crosshair");
        $(document).on('mousemove', function(e){
            $('#clickOnMapDiv').css({
                left:  e.pageX,
                top:   e.pageY
            });
        });
        state.setState(state.MAP_WAITING_FOR_CLICK_TRIGGER);
    }

    this.saveWaypoint = function(worldX, worldZ) {
        $('#clickOnMapDiv').addClass('hidden');
        $("#map").css("cursor", "");
        if(util.notNull(state.getSelectedUnitGroup())) {
            var y = state.getSelectedUnitGroup().y;
            var waypoint = {
                x:worldX,
                y:y,
                z:worldZ,
                clientId:new Date().getTime(),
                speed:300,     // TODO Fix speed depending on air or ground unit
                action:{"actionType":"FLY"}
            }
            state.getSelectedUnitGroup().waypoints.push(waypoint);
            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);   // NOTE: When setting current mission, we either need to null all "selected objects" or reassign them.
                maprenderer.redraw();
                missionbuilder.addWaypoint();
            });

        }
    }

    this.undo = function() {
        // If currently editing mission, try to load that last saved version from server.
        var missionState = state.popMissionState();
        if(util.notNull(missionState)) {
            state.setCurrentMission(missionState);
            maprenderer.redraw();
        } else {
            console.log("No state to pop");
        }

    }


    this.handleObjectSelected = function(obj) {
        if(util.notNull(obj)) {
            $('#object-properties').empty();
            switch(obj.groupType) {
                case "AIR_GROUP":

                    var src = $('#planes-edit-tpl').html();
                    var template = Handlebars.compile(src);
                    var html    = template(obj);
                    $('#object-properties').html(html);
                    util.bindTextField('planes-edit-group-name', obj, 'name');
                    util.bindTextField('planes-edit-group-heading', obj, 'yOri');
                    util.bindTextArea('planes-edit-group-description', obj, 'description');
                    rest.getPlaneTypes(state.getCurrentCountry(), function(data) {
                        util.populateSelect('planes-edit-group-type', obj, 'type', data);
                    });
                    rest.getFormationTypes(function(data) {
                        util.populateSelect('planes-edit-group-formation', obj, 'formation', data);
                    });
                    util.populateSelect('planes-edit-group-size', obj, 'size', statics.getGroupSizes());
                    util.populateSelect('planes-edit-group-altitude', obj, 'y', statics.getAltitudes());
                    util.populateSelectKeyVal('planes-edit-group-skill', obj, 'aiLevel', statics.getSkills());
                    break;
                case "GROUND_GROUP":
                    var src = $('#ground-group-edit-tpl').html();
                    var template = Handlebars.compile(src);
                    var html    = template(obj);
                    $('#object-properties').html(html);
                    util.bindTextField('ground-group-edit-group-name', obj, 'name');
                    util.bindTextField('ground-group-edit-group-heading', obj, 'yOri');
                    util.bindTextArea('ground-group-edit-group-description', obj, 'description');
                    rest.getVehicleTypes(state.getCurrentCountry(), function(data) {
                        util.populateSelect('ground-group-edit-group-type', obj, 'type', data);
                    });
                    rest.getFormationTypes(function(data) {
                        util.populateSelect('ground-group-edit-group-formation', obj, 'formation', data);
                    });
                    util.populateSelect('ground-group-edit-group-size', obj, 'size', statics.getGroupSizes());
                    util.populateSelectKeyVal('ground-group-edit-group-skill', obj, 'aiLevel', statics.getSkills());
                    break;
            }

            if(!util.notNull(obj.groupType)) {
                switch(obj.objectType) {
                    case "TRIGGER":
                        var src = $('#trigger-edit-tpl').html();
                        var template = Handlebars.compile(src);
                        var html    = template(obj);
                        $('#object-properties').html(html);
                        util.bindTextField('trigger-edit-name', obj, 'name');
                        $('.slider').slider().on('slide', function(ev){
                            $('#radius-text').text(ev.value);
                            obj.radius = ev.value;
                            maprenderer.redraw();
                        }).on('slideStop', function(ev){
                                $('#radius-text').text(ev.value);
                                obj.radius = ev.value;
                                rest.updateMission(state.getCurrentMission(), function(data) {
                                    state.setCurrentMission(data);
                                    maprenderer.redraw();
                                })
                            });
                        break;
                    case "WAYPOINT":
                        var src = $('#waypoint-edit-tpl').html();
                        var template = Handlebars.compile(src);
                        var html    = template(obj);
                        $('#object-properties').html(html);
                        util.bindTextField('waypoint-edit-name', obj, 'name');

                        // Slider for speed
                        $('#waypoint-edit-speed').slider().on('slide', function(ev){
                            $('#speed-text').text(ev.value);
                            obj.speed = ev.value;
                            maprenderer.redraw();
                        }).on('slideStop', function(ev){
                                $('#speed-text').text(ev.value + ' km/h');
                                obj.speed = ev.value;
                                rest.updateMission(state.getCurrentMission(), function(data) {
                                    state.setCurrentMission(data);
                                    maprenderer.redraw();
                                })
                            });

                        // Slider for altitude
                        $('#waypoint-edit-altitude').slider().on('slide', function(ev){
                            $('#y-text').text(ev.value);
                            obj.y = ev.value;
                            maprenderer.redraw();
                        }).on('slideStop', function(ev){
                                $('#y-text').text(ev.value + ' meters');
                                obj.y = ev.value;
                                rest.updateMission(state.getCurrentMission(), function(data) {
                                    state.setCurrentMission(data);
                                    maprenderer.redraw();
                                })
                            });

                        // Slider for action radius/area
                        $('#waypoint-edit-radius').slider().on('slide', function(ev){
                            $('#action-radius-text').text(ev.value);
                            obj.area = ev.value;
                            maprenderer.redraw();
                        }).on('slideStop', function(ev){
                                $('#action-radius-text').text(ev.value + ' meters');
                                obj.area = ev.value;
                                rest.updateMission(state.getCurrentMission(), function(data) {
                                    state.setCurrentMission(data);
                                    maprenderer.redraw();
                                })
                            });

                        rest.getActionTypes( function(data) {
                            util.populateSelect('waypoint-edit-action', obj, 'action.actionType', data);
                        });
                        var keyval = $.map(state.getUnitGroupsForAllSides(), function(item) {
                            return {
                                "value" : item.clientId,
                                "name" : item.name
                            };
                        });
                        util.populateSelectKeyVal('waypoint-edit-action-target', obj, 'action.targetClientId', keyval);

                        break;
                }
            }
            $('#object-properties').removeClass('hidden');
            $('#object-properties').css('top',50+$('#rightmenu').height() + 'px');
        }

        $('#delete').unbind().click(function() {
            if(confirm("Do you want to delete this object?")) {
                if(util.notNull(state.getSelectedUnitGroup())) {
                    for(var a = 0; a < state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups.length; a++) {
                        if(state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups[a].clientId === obj.clientId) {

                            state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups.splice(a, 1);
                            state.setSelectedUnitGroup(null);
                            rest.updateMission(state.getCurrentMission(), function(data) {
                                console.log("Deleted object and saved mission");
                                state.setCurrentMission(data);
                                maprenderer.redraw();
                            });
                            return;
                        }
                    }
                } else if(util.notNull(state.getSelectedTriggerZone())) {


                    // Also do triggers if not unit or waypoint
                    for(var a = 0; a < state.getCurrentMission().triggerZones.length; a++) {
                        if(state.getCurrentMission().triggerZones[a].clientId === obj.clientId) {
                            state.getCurrentMission().triggerZones.splice(a, 1);
                            state.setSelectedTriggerZone(null);
                            rest.updateMission(state.getCurrentMission(), function(data) {
                                state.setCurrentMission(data);
                                console.log("Deleted trigger zone and saved mission");
                                maprenderer.redraw();
                            });
                            return;
                        }
                    }
                } else if(util.notNull(state.getSelectedWaypoint())) {
                    for(var a = 0; a < state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups.length; a++) {
                        var ugroup = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups[a];

                        for(var b = 0; b < ugroup.waypoints.length; b++) {
                            if(ugroup.waypoints[b].clientId == obj.clientId) {
                                // Splice the array
                                ugroup.waypoints.splice(b, 1);
                                state.setSelectedWaypoint(null);
                                rest.updateMission(state.getCurrentMission(), function(data) {
                                    console.log("Deleted waypoint and saved mission");
                                    state.setCurrentMission(data);
                                    maprenderer.redraw();
                                });
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    var handleMissionCreateResponse = function(data) {
        $('#mission-name').text(data.name);
        $('#rightmenu').removeClass('hidden');
        state.setCurrentMission(data);
    }

}