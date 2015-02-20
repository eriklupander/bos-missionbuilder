/**
 * All code that actually draws something to the map canvas should go into this "class"
 */
var renderer = new function() {

    var images = {};
    var numImages = 0;

    var sources = {
        "t34": 'images/icon-t34.png',
        "pziv": 'images/pziv.png',
        "101": 'images/101.png',
        "201": 'images/201.png',
        "bf109" : 'images/bf109.png'
    };


    // get num of sources
    for(var src in sources) {
        numImages++;
    }
    for(var src in sources) {
        images[src] = new Image();
        images[src].onload = function() {
//            if(++loadedImages >= numImages) {
//                callback(images);
//            }
        };
        images[src].src = sources[src];
    }

    this.renderSelectionBox = function(context) {
        var box = state.getSelectionBox();
        context.beginPath();
        context.rect(box.topX, box.topY, box.bottomX, box.bottomY);
        context.fillStyle = 'red';
        context.globalAlpha = 0.1;
        context.fill();
        context.lineWidth = 1;
        context.strokeStyle = 'black';
        context.stroke();
        context.globalAlpha = 1;
    }

    this.renderAirfields = function(viewport, context) {
        if(statics.getAirfields().length == 0) {
            return;
        }

        for(var a = 0; a < statics.getAirfields().length; a++) {
            var airfield = statics.getAirfields()[a];
            var coords = coordTranslator.worldToImageInViewport(airfield.x, airfield.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);

            // Primitive occlusion culling
            if(coords.x < 0 || coords.y < 0 || coords.x > maprenderer.mapWidth || coords.y >  maprenderer.mapHeight) continue;

            drawAirfield(coords.x, coords.y, airfield.name, context);
        }
    }

    var drawAirfield = function(x, y, name, context) {
        context.save();
        context.beginPath();
        context.arc(x, y, 12, 0, 2*Math.PI, false);
        context.fillStyle = 'green';
        context.globalAlpha = 0.1;
        context.fill();
        context.globalAlpha = 0.5;
        context.lineWidth = 1;
        context.strokeStyle = 'green';
        context.stroke();
        context.globalAlpha = 1;
       // context.font = '12pt Open Sans';
       // context.textAlign = 'center';

      //  context.fillText(name, x, y+12+12);
        context.restore();
    }

    this.renderTriggerZones = function(metadata, zoom, viewport, context) {
        if(!util.notNull(state.getCurrentMission())) return;

         var zones = state.getCurrentMission().triggerZones;
        for(var a = 0; a < zones.length; a++) {
            var tz = zones[a];
            var coords = coordTranslator.worldToImageInViewport(tz.x, tz.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
            drawTriggerZone(coords.x, coords.y, tz.radius/metadata.xd/zoom, context);
        }
    }

    this.renderPlacingTriggerZone = function(metadata, zoom, viewport, context) {
        if(util.notNull(state.getSelectedTriggerZone())) {
            var tz = state.getSelectedTriggerZone();
            var coords = coordTranslator.worldToImageInViewport(tz.x, tz.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
            drawTriggerZone(coords.x, coords.y, tz.radius/metadata.xd/zoom, context);
        }

    }

    var drawTriggerZone = function(x, y, radius, context) {
        context.save();
        // Outer circle
        context.beginPath();
        context.arc(x, y, radius, 0, 2 * Math.PI, false);
        context.fillStyle = 'yellow';
        context.globalAlpha = 0.5;
        context.fill();
        context.globalAlpha = 1.0;

        context.lineWidth = 2;


        context.strokeStyle = '#444444';
        context.stroke();

        // Inner circle
        context.beginPath();
        context.arc(x, y, 16, 0, 2 * Math.PI, false);
        context.fillStyle = 'yellow';
        context.globalAlpha = 0.5;
        context.fill();
        context.globalAlpha = 1.0;

        context.lineWidth = 2;


        context.strokeStyle = '#444444';
        context.stroke();


        context.font = '12pt Open Sans';
        context.textAlign = 'center';
        context.fillStyle = 'black';
        context.fillText("TZ", x, y+6);
        context.restore();
    }

    /**
     * Renders line from last waypoint when in adding waypoint state.
     * @param mouseX
     * @param mouseY
     * @param viewport
     * @param context
     */
    this.renderPlacingWaypoint = function(mouseX, mouseY, viewport, context) {
        if(state.getState() == state.PLACING_WAYPOINT && util.notNull(state.getSelectedUnitGroup())) {
            var ug = state.getSelectedUnitGroup();
            var startingCoord = resolveStartingCoord(ug);
            context.save();
            var coord = coordTranslator.worldToImageInViewport(startingCoord.x, startingCoord.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
            context.moveTo(coord.x, coord.y);
            context.lineWidth = 2;
            context.lineTo(mouseX, mouseY);
            context.strokeStyle = '#005500';
            context.stroke();
            context.restore();
        }
    }

    var resolveStartingCoord = function(ug) {
        if(ug.waypoints.length == 0) {
            return {x:ug.x, z:ug.z};
        } else {
            return {x:ug.waypoints[ug.waypoints.length-1].x, z:ug.waypoints[ug.waypoints.length-1].z};
        }
    }

    this.renderFlightObjects = function(viewport, context) {
        if(!(util.notNull(state.getCurrentMission()) && util.notNull(state.getCurrentMission().sides[state.getCurrentCountry()])))
            return;


        context.save();
        var unitGroups = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups;
        renderSide(state.getCurrentCountry(), viewport, context, 1.0);
        if(state.getFilter('both_sides')) {
            renderSide(state.getOtherCountry(), viewport, context, 0.3);
        }
    }

    var renderSide = function(countryId, viewport, context, globalAlpha) {
        var unitGroups = state.getCurrentMission().sides[countryId].unitGroups;
        if(unitGroups.length > 0) {
            for(var a = 0; a < unitGroups.length; a++) {
                var ug = unitGroups[a];
                var coord = coordTranslator.worldToImageInViewport(ug.x, ug.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
                // Primitive occlusion culling
                //if(coord.x < 0 || coord.y < 0 || coord.x > maprenderer.mapWidth || coord.y >  maprenderer.mapHeight) continue;

                drawUnitGroup(coord.x, coord.y, ug, context, globalAlpha);

                if(countryId == state.getCurrentCountry()) {
                    for(var b = 0; b < ug.waypoints.length; b++) {
                        var waypoint = ug.waypoints[b];
                        var wpCoord = coordTranslator.worldToImageInViewport(waypoint.x, waypoint.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
                        var lastCoord = {};
                        if(b > 0) {
                            lastCoord = coordTranslator.worldToImageInViewport(ug.waypoints[b-1].x, ug.waypoints[b-1].z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
                        }
                        var isSelectedWaypoint = waypoint == state.getSelectedWaypoint();
                        drawWaypoint(wpCoord.x, wpCoord.y, lastCoord.x, lastCoord.y, coord.x, coord.y, b, isSelectedWaypoint, ug.clientId, waypoint, context);
                    }
                }
            }
        }

        var staticObjectGroups = state.getCurrentMission().sides[countryId].staticObjectGroups;
        if(staticObjectGroups.length > 0) {
            for(var a = 0; a < staticObjectGroups.length; a++) {
                var sog = staticObjectGroups[a];
                var coord = coordTranslator.worldToImageInViewport(sog.x, sog.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
                drawStaticObjectGroup(coord.x, coord.y, sog, context, globalAlpha);
            }
        }
        context.restore();
    }

    var drawWaypoint = function(x, y, x2, y2, ugx, ugy, index, isSelectedWaypoint, unitGroupClientId, waypoint, context) {
        var alpha = util.notNull(state.getSelectedUnitGroup()) && unitGroupClientId == state.getSelectedUnitGroup().clientId ? 0.9 : 0.2;
        context.globalAlpha = alpha;
        if(index == 0) {
            // First, draw line from parent object.
            context.moveTo(ugx, ugy);

            context.lineWidth = 2;
            context.lineTo(x, y);
            context.strokeStyle = '#550000';
            context.stroke();
        } else {
            // Draw line from previous to here
            context.moveTo(x2, y2);

            context.lineWidth = 2;
            context.lineTo(x, y);
            context.strokeStyle = '#550000';
            context.stroke();
        }

        context.beginPath();
        context.arc(x, y, 8, 0, 2 * Math.PI, false);
        context.fillStyle = 'red';
        context.globalAlpha = alpha;
        context.fill();
        context.globalAlpha = alpha;
        if(isSelectedWaypoint) {
            context.lineWidth = 4;
        } else {
             context.lineWidth = 2;
        }

        context.strokeStyle = '#550000';
        context.stroke();


        context.font = '10pt Open Sans';
        context.textAlign = 'center';
        context.fillStyle = 'white';
        context.fillText(""+ (index+1), x, y+4);

        // For waypoints with a non-fly command, print it..
        if(util.notNull(waypoint.action) && util.notNull(waypoint.action.actionType) && waypoint.action.actionType != 'FLY') {
            context.font = '8pt Open Sans';
            context.textAlign = 'center';
            context.fillStyle = 'black';
            context.fillText(waypoint.action.actionType, x, y+22);
        }

    }

    var drawStaticObjectGroup  = function(x, y, staticObjectGroup, context, globalAlpha) {
        context.beginPath();
        context.arc(x, y, 20, 0, 2 * Math.PI, false);
        context.fillStyle = 'purple';
        context.globalAlpha = globalAlpha-0.2;
        context.fill();
        context.globalAlpha = globalAlpha;
        if(util.notNull(state.getSelectedStaticObjectGroup()) && staticObjectGroup.clientId == state.getSelectedStaticObjectGroup().clientId) {
            context.lineWidth = 6;
        } else {
            context.lineWidth = 3;
        }

        context.strokeStyle = '#222222';
        context.stroke();

        context.font = '12pt Open Sans';
        context.textAlign = 'center';

        context.fillText(staticObjectGroup.type, x, y+20+16);

        context.font = '10pt Open Sans';
        context.textAlign = 'center';

        context.fillStyle = 'white';
        context.fillText("S", x, y+4);
    }

    var TO_RADIANS = Math.PI/180;

    var drawUnitGroup = function(x, y, unitGroup, context, globalAlpha) {

        var type = unitGroup.groupType;

        context.beginPath();
        context.arc(x, y, 20, 0, 2 * Math.PI, false);
        context.fillStyle = type == 'AIR_GROUP' ? 'blue' : 'green';

        if(type == 'AIR_GROUP') {
            context.globalAlpha = globalAlpha == 1.0 ? 0.2 : 0.1;
        } else {
            context.globalAlpha = globalAlpha == 1.0 ? 0.7 : 0.3;
        }
        context.fill();
        context.globalAlpha = globalAlpha;
        if(util.notNull(state.getSelectedUnitGroup()) && unitGroup.clientId == state.getSelectedUnitGroup().clientId) {
            context.lineWidth = 5;
        } else {
            context.lineWidth = 3;
        }

        context.strokeStyle = globalAlpha == 1.0 ? '#000055' : '#FF0000';
        context.stroke();

        context.font = '12pt Open Sans';
        context.textAlign = 'center';
        context.fillStyle = 'black';
        context.fillText(unitGroup.type, x, y+20+16);


        context.font = '10pt Open Sans';
        context.fillText(unitGroup.size, x-18, y-18);

       // var imageObj = new Image();
        var scale = 0.5;
        var rotation = 0;

        var src = '';
        if(type == 'AIR_GROUP') {
            rotation = unitGroup.yOri;
            src = 'bf109';
        } else {
            src = state.getCurrentCountry() == 201 ? "pziv" : "t34";
            scale = 0.6;
        }
        context.save();
        context.translate(x,y);
        context.rotate(rotation * TO_RADIANS);
        context.scale(scale, scale);
        context.drawImage(images[src], -images[src].width/2, -images[src].height/2);
        context.restore();
    }

    this.renderKillsOnMapVectorized = function(viewport, context, data) {

        context.save();
        if(data.kills.length > 0) {
            for(var a = 0; a < data.kills.length; a++) {
                var kill = data.kills[a];
                if(kill.parentId == -1 && kill.killedXPos != null && kill.killedZPos != null)   {
                    var coord = coordTranslator.worldToImageInViewport(kill.killedXPos, kill.killedZPos, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
                    drawKill(coord.x, coord.y, 10, kill.type, context);
                }
            }
        }
        context.restore();
    }

    this.renderFlightMapVectorized = function(viewport, context, data) {

        context.save();
        if(data.flightTrack.length > 1) {
            var startingCoord = coordTranslator.worldToImageInViewport(data.flightTrack[0].x, data.flightTrack[0].z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);

            context.beginPath();

            context.moveTo(startingCoord.x, startingCoord.y);
            for(var a = 1; a < data.flightTrack.length; a++) {
                var fp = data.flightTrack[a];
                drawFlightPathVectorized(viewport, fp, context);
            }
            context.stroke();
        }
        context.moveTo(startingCoord.x, startingCoord.y);
        drawStartSymbol(startingCoord.x, startingCoord.y, context);
        var secondEndingCoord = coordTranslator.worldToImageInViewport(data.flightTrack[data.flightTrack.length - 2].x, data.flightTrack[data.flightTrack.length - 2].z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
        var endingCoord = coordTranslator.worldToImageInViewport(data.flightTrack[data.flightTrack.length - 1].x, data.flightTrack[data.flightTrack.length - 1].z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
        drawEndSymbol(endingCoord.x, endingCoord.y, secondEndingCoord.x, secondEndingCoord.y, context);
        context.restore();
    }


    this.renderKillInfoDialog = function(kill) {
        $('#gid_objectname').text(kill.type);
        $('#gid_timekilled').text(kill.gameTime);     gid_side
        $('#gid_side').text(kill.country);
        $('#gid_hitcount').text(kill.totalHits);

        var hitsByType = kill.hits;
        $('#gid_hitsbytype').empty();
        if(hitsByType != null) {
            for (var key in hitsByType) {
                if (hitsByType.hasOwnProperty(key)) {
                    var tpl = '<div class="row">' +
                        '<div class="col-md-10">' + key + '</div>' +
                        '<div class="col-md-2">' + hitsByType[key] + '</div>' +
                        '</div>';
                    $('#gid_hitsbytype').append(tpl);
                }
            }
        }
        $('#gid-dialog').removeClass("hidden");
    }

     var drawFlightPathVectorized = function(viewport, fp, context) {
        var coord = coordTranslator.worldToImageInViewport(fp.x, fp.z, viewport, maprenderer.mapWidth, maprenderer.mapHeight);
        context.lineWidth = 2;
        context.lineTo(coord.x, coord.y);
        context.arc(coord.x, coord.y, 2, 0, 2*Math.PI, false);
        context.moveTo(coord.x, coord.y);
    }

    var drawKill = function(x, y, size, label, context) {

        context.beginPath();
        context.arc(x, y, size, 0, 2 * Math.PI, false);
        context.fillStyle = 'red';
        context.globalAlpha = 0.8;
        context.fill();
        context.globalAlpha = 1.0;
        context.lineWidth = 2;
        context.strokeStyle = '#550000';
        context.stroke();

        context.font = '12pt Open Sans';
        context.textAlign = 'center';

        context.fillText(label, x, y+size+16);

        context.font = '10pt Open Sans';
        context.textAlign = 'center';

        context.fillStyle = 'white';
        context.fillText("K", x, y+4);

    }

    var drawStartSymbol = function(x, y, context) {
        context.save();

        context.beginPath();
        context.arc(x, y, 15, 0, Math.PI*2, false);
        context.closePath();
        context.lineWidth = 3;
        context.fillStyle = 'green';
        context.globalAlpha = 0.8;
        context.fill();
        context.globalAlpha = 1.0;
        context.strokeStyle = '#005500';
        context.stroke();

        context.font = '12pt arial';
        context.textAlign = 'center';

        context.fillStyle = 'white';
        context.fillText("S", x, y+5);

        context.restore();
    }

    var drawEndSymbol = function(x, y, x2, y2, context) {
        context.save();
        context.beginPath();
        context.moveTo(x2, y2);
        context.lineWidth = 2;
        context.lineTo(x, y);
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(x, y, 15, 0, Math.PI*2, false);
        context.closePath();
        context.lineWidth = 3;
        context.fillStyle = 'red';
        context.globalAlpha = 0.8;
        context.fill();
        context.globalAlpha = 1.0;
        context.strokeStyle = '#550000';
        context.stroke();

        context.font = '12pt arial';
        context.textAlign = 'center';

        context.fillStyle = 'white';
        context.fillText("E", x, y+5);
        context.restore();
    }
}