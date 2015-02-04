var maprenderer = new function() {

    var MAP_URL = "";
    //var MAX_X = 231000;      // Height, from bottom in bos
    //var MAX_Z = 357500;      // Width, from left in bos

    this.mapHeight;
    this.mapWidth;

    var zoom = 1.0;

    var MAP_PIXEL_SIZE_X = 8192;
    var MAP_PIXEL_SIZE_Y = 5245;

    var mouseX, mouseY, imageX = 0, imageY = 0, startX = 0, startY = 0, offsetX = 0, offsetY = 0;
    var mouseDownX = 0, mouseDownY = 0;
    var canvasOffset = {};
    var metadata = {};

    var xd, zd; // factor between map res and map coords

    var buffer;

    var mouseDown = false;



    this.renderMap = function() {
        // Reset all map-scroll state variables when a new mission is rendered.
        mouseX = 0, mouseY = 0, imageX = 0, imageY = 0, startX = 0, startY = 0, offsetX = 0, offsetY = 0, zoom = 1.0;

        // Calculate the factor between map size in world coords and pixels
        xd = coordTranslator.MAX_X / MAP_PIXEL_SIZE_Y;
        zd = coordTranslator.MAX_Z / MAP_PIXEL_SIZE_X;

        metadata.xd = xd;
        metadata.zd = zd;

        canvasOffset = $("#map").offset();
        // Disable browser context menu on canvas.
        $('#map').contextmenu( function() {
            return false;
        });

        $body.addClass("loading");


        var width = ($(window).width() - canvasOffset.left)*0.99;
        var height = $(window).height()*0.9;

        $("#map").attr('width', width);
        $("#map").attr('height', height);

        maprenderer.mapHeight = $('#map').attr('height');
        maprenderer.mapWidth = $('#map').attr('width');

        $("#map").mousedown(function (e) {
            startX=parseInt(e.clientX-0);
            startY=parseInt(e.clientY-0);
            mouseDownX = startX;
            mouseDownY = startY;
            mouseDown = true;

            startXX=parseInt(e.clientX-canvasOffset.left);
            startYY=parseInt(e.clientY-canvasOffset.top);

            // Test select
            var hitBox = coordTranslator.calculateHitBox(imageX+startXX*zoom, imageY+startYY*zoom, metadata, 16, zoom);

            // TODO implement rendering of hitbox to help troubleshoot...

            if(state.getState() == state.NORMAL) {
                if(util.notNull(state.getCurrentMission())) {
                    var result = coordTranslator.ifUnitGroupSelected(hitBox, metadata, missionbuilder.objectSelected);
                    if(!result) {
                        // TODO unify to use "drag target" or "selected object" only.
                        state.setSelectedUnitGroup(null);
                        state.setSelectedWaypoint(null);
                        state.setSelectedTriggerZone(null);
                        state.setDragTarget(null);
                    }
                }
            } else if(state.getState() == state.PLACING_WAYPOINT) {
                var worldCoords = coordTranslator.imageToWorld(
                    imageX+startXX*zoom, imageY+startYY*zoom,
                    metadata);
                missionbuilder.saveWaypoint(worldCoords.x, worldCoords.z);
            }



        });
        $("#map").mouseout(function (e) {
            mouseDown = false;
        });
        $("#map").mouseover(function (e) {
            mouseDown = false;
        });
        $("#map").mousemove(function (e) {
            onDrag(e);
        });
        $("#map").mouseup(function (e) {
            mouseDown = false;

            startXX=parseInt(e.clientX-canvasOffset.left);
            startYY=parseInt(e.clientY-canvasOffset.top);

            switch(state.getState()) {
                case state.MAP_WAITING_FOR_CLICK_AIR_GROUP:
                    if(Math.abs(e.clientX - mouseDownX) < 6 && Math.abs(e.clientY - mouseDownY) < 6) {
                        missionbuilder.openCreateUnitDialog(coordTranslator.imageToWorld(
                            imageX+startXX*zoom, imageY+startYY*zoom,
                            metadata));
                    }
                    break;
                case state.MAP_WAITING_FOR_CLICK_GROUND_GROUP:
                    if(Math.abs(e.clientX - mouseDownX) < 6 && Math.abs(e.clientY - mouseDownY) < 6) {
                        missionbuilder.openCreateGroundUnitDialog(coordTranslator.imageToWorld(
                            imageX+startXX*zoom, imageY+startYY*zoom,
                            metadata));
                    }
                    break;
                case state.MAP_WAITING_FOR_CLICK_TRIGGER:
                    if(Math.abs(e.clientX - mouseDownX) < 6 && Math.abs(e.clientY - mouseDownY) < 6) {
                        var coords = coordTranslator.imageToWorld(
                            imageX+startXX*zoom, imageY+startYY*zoom,
                            metadata);
                        var triggerZone = {x:coords.x, y:0, z:coords.z, radius:500, name:"", clientId:new Date().getTime()};
                        state.setSelectedTriggerZone(triggerZone);
                        state.setState(state.MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS);
                    }
                    break;
                case state.MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS:
                    if(util.notNull(state.getSelectedTriggerZone())) {
                        state.pushMissionState(state.getCurrentMission());
                        state.getCurrentMission().triggerZones.push(state.getSelectedTriggerZone());
                        state.setSelectedTriggerZone(null);
                        rest.updateMission(state.getCurrentMission(), function(data) {
                            $('#clickOnMapDiv').addClass('hidden');
                            $("#map").css("cursor", "");
                            console.log("Updated mission "  + data.name);
                            state.setState(state.NORMAL);
                            state.setCurrentMission(data);
                        });
                    }
                    break;
                case state.DRAGGING_UNIT:
                    state.setState(state.NORMAL);
                    if(util.notNull(state.getDragTarget()) && !util.notNull(state.getDragTarget().clientId)) {
                        state.getDragTarget().clientId = new Date().getTime();
                    }
                    rest.updateMission(state.getCurrentMission(), function(data) {
                        state.setCurrentMission(data);
                        console.log("Updated mission "  + data.name);
                    });

                    break;
                case state.NORMAL:

                    // Test select
                    var hitBox = coordTranslator.calculateHitBox(imageX+startXX*zoom, imageY+startYY*zoom, metadata, 16, zoom);
                    var hit = coordTranslator.ifUnitGroupSelected(hitBox, metadata, missionbuilder.objectSelected);
                    if(!hit) {
                        // TODO unify to use "drag target" or "selected object" only.
                        state.setSelectedUnitGroup(null);
                        state.setSelectedWaypoint(null);
                        state.setSelectedTriggerZone(null);
                        state.setDragTarget(null);
                    }
                    break;
            }
            draw();
        });

        $("#map").bind('mousewheel', function(e){
            if(typeof e.originalEvent.wheelDelta == 'undefined' || e.originalEvent.wheelDelta == null) {
                return;
            }
            var wheel = e.originalEvent.wheelDelta/120;
            var lastZoom = zoom;
            zoom = zoom - (wheel/15)*zoom;

            var currentWidth = maprenderer.mapWidth*lastZoom;
            var currentHeight = maprenderer.mapHeight*lastZoom;
            var newWidth = maprenderer.mapWidth*zoom;
            var newHeight = maprenderer.mapHeight*zoom;

            var moveImageXBy = (currentWidth-newWidth) * 0.5;
            var moveImageYBy = (currentHeight-newHeight) * 0.5;
            imageX += moveImageXBy;
            imageY += moveImageYBy;

            e.preventDefault();
            draw();
        });

        renderToCanvas(MAP_PIXEL_SIZE_X, MAP_PIXEL_SIZE_Y);
    }

    var renderToCanvas = function (width, height) {
        buffer = document.createElement('canvas');
        buffer.width = width;
        buffer.height = height;
        renderFunction();
    };

    var renderFunction = function() {
        zoom = 1.0;
        // We need to find the min/max x and z coords for the mission
        var lowerBounds = {
            x1 : coordTranslator.MAX_X, z1 : coordTranslator.MAX_Z
        }

        var upperBounds = {
            x1 : 0, z1 : 0
        }

//        if(data.flightTrack.length > 1) {
//            for(var a = 0; a < data.flightTrack.length; a++) {
//                var fp = data.flightTrack[a];
//                if(fp.x > upperBounds.x1) {upperBounds.x1 = fp.x;}
//                if(fp.z > upperBounds.z1) {upperBounds.z1 = fp.z;}
//                if(fp.x < lowerBounds.x1) {lowerBounds.x1 = fp.x;}
//                if(fp.z < lowerBounds.z1) {lowerBounds.z1 = fp.z;}
//            }
//        }

        var startPos = coordTranslator.worldToImage(lowerBounds.x1, lowerBounds.z1, {"xd":xd, "zd":zd});
        imageX = maprenderer.mapWidth/1.5;
        imageY = maprenderer.mapHeight/1.5;

        zoom = 3.0;

        var context = buffer.getContext('2d');
        var imageObj = new Image();

        imageObj.onload = function() {
            $body.removeClass("loading");
            // Render everything AFTER image has loaded, otherwise map renders over other stuff
            context.drawImage(imageObj, 0, 0, MAP_PIXEL_SIZE_X, MAP_PIXEL_SIZE_Y);
            draw();

            $('#map').css('background-color', 'rgba(158, 167, 184, 1.0)');
            $('#map').removeClass('hidden');
        };
        imageObj.src = '../images/Stalingrad_map.jpg';
    }


    this.redraw = function() {
        draw();
    }

    // The draw function is invoked every time the map is zoomed or panned. It reuses the canvas
    // with the huge map bitmap already rendered.
    var draw = function() {
        var canvas = document.getElementById('map');
        var ctx = canvas.getContext('2d');

        // TODO - the clearrect should only be necessary if imageX or imageY causes
        // render outside of map
        ctx.clearRect ( 0 , 0 , canvas.width, canvas.height );
        if(imageX < 0) imageX = 0;
        if(imageY < 0) imageY = 0;

        // TODO Add check so we can't pan outside of the map bounds. Do by limiting imageX/Y to MAX - width/height*zoom?

        ctx.drawImage(buffer, imageX, imageY, maprenderer.mapWidth*zoom, maprenderer.mapHeight*zoom, 0, 0, maprenderer.mapWidth, maprenderer.mapHeight);
        var viewport = calcViewPortWorldCoordinates();
        renderer.renderFlightObjects(viewport, ctx);
        renderer.renderPlacingWaypoint(mouseX-canvasOffset.left, mouseY-canvasOffset.top, viewport, ctx);
        renderer.renderPlacingTriggerZone(metadata, zoom, viewport, ctx);
        if(state.getFilter('trigger')) {
            renderer.renderTriggerZones(metadata, zoom, viewport, ctx);
        }
        if(state.getFilter('airfields')) {
            renderer.renderAirfields(viewport, ctx);
        }
        if(util.notNull(state.getSelectionBox())) {
            renderer.renderSelectionBox(ctx);
        }

       // renderer.renderFlightMapVectorized(viewport, ctx, sData);
       // renderer.renderKillsOnMapVectorized(viewport, ctx, sData);
    }

    var toWorldCoordY = function(pixel) {
        return pixel * xd;
    }
    var toWorldCoordX = function(pixel) {
        return coordTranslator.MAX_X - (pixel*zd);
    }


    /**
     * Returns the current viewport map as BoS world coordinates
     *
     * Remember that BoS uses X as vertical axis starting from bottom, Y as horizontal starting from left.
     *
     * @returns {{ty: *, tx: *, by: *, bx: *}}
     */
    var calcViewPortWorldCoordinates = function() {
        var viewport = {
            ty : toWorldCoordY(imageX),
            tx : toWorldCoordX(imageY),
            by : toWorldCoordY(imageX+maprenderer.mapWidth*zoom),
            bx : toWorldCoordX(imageY+maprenderer.mapHeight*zoom)
        };
        return viewport;
    }

    var onDrag = function(e) {
        mouseX = parseInt(e.clientX - offsetX);
        mouseY = parseInt(e.clientY - offsetY);

        var dx = mouseX - startX;
        var dy = mouseY - startY;

        startY = mouseY;
        if(!mouseDown && state.getState() == state.PLACING_WAYPOINT) {
            console.log("Placing waypoint at " + mouseX + " / " + mouseY);
            draw();
            return;
        }
        if(!mouseDown && state.getState() == state.MAP_WAITING_FOR_CLICK_TRIGGER_RADIUS) {

            if(util.notNull(state.getSelectedTriggerZone())) {
                var radius = state.getSelectedTriggerZone().radius;
                radius += dy*metadata.zd*zoom;
                if(radius < 100) radius = 100;
                if(radius > 100000) radius = 100000;
                state.getSelectedTriggerZone().radius = radius;
                draw();
            }
            return;
        }
        if(!mouseDown) return;




        // DRAG OBJECT AROUND
        if(util.notNull(state.getDragTarget())) {
            // Update unit world x/z from mouse change..err

            state.setState(state.DRAGGING_UNIT);
            var startXX=parseInt(e.clientX-canvasOffset.left);
            var startYY=parseInt(e.clientY-canvasOffset.top);

            var ug = state.getDragTarget();
            var worldPos = coordTranslator.imageToWorld(imageX+startXX*zoom, imageY+startYY*zoom, metadata);
            ug.z = worldPos.z;
            ug.x = worldPos.x;
            draw();

            return;
        }


        // DRAG MAP VIEW AROUND
        if( e.which == 1 && !(e.which == 1 && e.buttons == 2)) {

            imageX = imageX ? imageX + -dx*2 : -dx*2;
            imageY = imageY ? imageY + -dy*2 : -dy*2;
            if(imageX < 0) imageX = 0;
            if(imageY < 0) imageY = 0;

            startX = mouseX;
            startY = mouseY;

            draw();
        }

        // ZOOM MAP WITH RIGHT MOUSE BUTTON
        if( e.which == 3 || (e.which == 1 && e.buttons == 2)) {

            var lastZoom = zoom;
            zoom = zoom ? zoom + dy/ (500/zoom) : dy/ (500/zoom);

            if(zoom > 3.1) {
                zoom = 3.1;
            }else  if(zoom < 0.1) {
                zoom = 0.1;
            } else {
                // Recalculate imageX and imageY so zooming is centered.

                // Get width/height before and then after next zoom.
                // Take each difference and split in half. Add/subtract to x and y.

                var currentWidth = maprenderer.mapWidth*lastZoom;
                var currentHeight = maprenderer.mapHeight*lastZoom;
                var newWidth = maprenderer.mapWidth*zoom;
                var newHeight = maprenderer.mapHeight*zoom;

                var moveImageXBy = (currentWidth-newWidth) * 0.5;
                var moveImageYBy = (currentHeight-newHeight) * 0.5;
                imageX += moveImageXBy;
                imageY += moveImageYBy;

                draw();
            }

        }
    }
}