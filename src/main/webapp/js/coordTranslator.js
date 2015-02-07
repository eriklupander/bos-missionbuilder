var coordTranslator = new function() {

	this.MAX_X = 229135;      // Height, from bottom in bos
    this.MAX_Z = 357500;      // Width, from left in bos

    var MAP_PIXEL_SIZE_X = 8192;
    var MAP_PIXEL_SIZE_Y = 5245;

    this.ifUnitGroupSelected = function(hitBox, metadata, callback) {
        if(!util.notNull(state.getCurrentMission())) {
            return;
        }
        var unitList = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups;

        // Try to find unit or waypoint
        for(var a = 0; a < unitList.length; a++) {
            var unit = unitList[a];
            if(coordTranslator.inHitBox(unit.x, unit.z, hitBox)) {
                callback(unit);
                return true;
            }

            // Only the currently selected unit's waypoints are selectable.

            for(var b = 0; b < unit.waypoints.length; b++) {

                if(util.notNull(state.getSelectedUnitGroup()) && (state.getSelectedUnitGroup().clientId == unit.clientId || unit.waypoints[b].unitGroupClientId == unit.clientId)) {

                    if(coordTranslator.inHitBox(unit.waypoints[b].x, unit.waypoints[b].z, hitBox)) {
                        callback(unit.waypoints[b]);
                        return true;
                    }
                }
            }

        }

        // Try triggers
        var triggers = state.getCurrentMission().triggerZones;
        for(var a = 0; a < triggers.length; a++) {
            var tz = triggers[a];
            if(coordTranslator.inHitBox(tz.x, tz.z, hitBox)) {
                callback(tz);
                return true;
            }
        }
        return false;
    }

	this.worldToImage = function(worldX, worldY, metaData) {
		var mapX =  ((coordTranslator.MAX_X - worldX) / metaData.xd);
        var mapZ = worldY / metaData.zd;
        return {
            x : mapZ,
            y : mapX
        };
	}
	
	this.imageToWorld = function(imageX, imageY, metaData) {
		return {
			x : toWorldCoordX(imageY, metaData.zd),
			z : toWorldCoordY(imageX, metaData.xd)
		};
	}

	this.isWorldObjectVisible = function(worldX, worldY, viewport) {
		
		return worldX < viewport.tx && worldX > viewport.bx && worldY > viewport.ty && worldY < viewport.by;
	
	}
	
	this.worldToImageInViewport = function(worldX, worldY, viewport, mapWidth, mapHeight) {
		
        
        var vImageX = (((worldY - viewport.ty) / (viewport.by - viewport.ty)) * mapWidth);
        var vImageY = (1 - ((worldX - viewport.bx) / (viewport.tx - viewport.bx))) * mapHeight;
        return {
            x : vImageX,
            y : vImageY
        };
	}

    this.calculateHitBox = function(imageX, imageY, metadata, radius, zoom) {
        var box = {};
        var r = radius*zoom;
        //state.setSelectionBox(imageX-radius, imageY-radius, imageX+radius, imageY+radius);
        var topLeft = coordTranslator.imageToWorld(imageX-r, imageY-r, metadata);
        var bottomRight = coordTranslator.imageToWorld(imageX+r, imageY+r, metadata);
        box.x1 = topLeft.x;
        box.y1 = topLeft.z;
        box.x2 = bottomRight.x;
        box.y2 = bottomRight.z;
        return box;
    }

    this.inHitBox = function(worldX, worldY, hitBox) {
        return worldX < hitBox.x1 && worldX > hitBox.x2 && worldY > hitBox.y1 && worldY < hitBox.y2;
    }

    this.doIfHit = function(hitbox, sData, callback) {

    }
	
	var toWorldCoordY = function(imagePixelX, xd) {
        return imagePixelX * xd;
    }
    var toWorldCoordX = function(imagePixelY, zd) {
        return coordTranslator.MAX_X - (imagePixelY*zd);
    }

};