var effects = new function() {

    this.addEffect = function() {
        $('#clickOnMapDiv').html("Select effect location by clicking on map");
        $('#clickOnMapDiv').removeClass('hidden');
        $("#map").css("cursor", "crosshair");
        $(document).on('mousemove', function(e){
            $('#clickOnMapDiv').css({
                left:  e.pageX,
                top:   e.pageY
            });
        });
        state.setState(state.MAP_WAITING_FOR_CLICK_EFFECT);
    }

    this.saveEffect = function(xPos, zPos) {
        var effect = {
            objectType: 'EFFECT',
            x:xPos,
            y:0,
            z:zPos,
            clientId:new Date().getTime(),
            effectType: 'LAND_FIRE' // hard-coded value to start with
        }

        state.getCurrentMission().effects.push(effect);
        state.setState(state.NORMAL);
        $("#map").css("cursor", "");
        $('#clickOnMapDiv').addClass('hidden');
        rest.updateMission(state.getCurrentMission(), function(data) {
            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

}