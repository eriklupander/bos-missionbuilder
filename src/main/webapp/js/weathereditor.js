var weathereditor = new function() {

    this.openWeatherEditor = function() {

        var src = $('#weather-tpl').html();
        var template = Handlebars.compile(src);
        var html    = template(state.getCurrentMission().weather);
        $('#weather-body').html(html);

//        $('#cloudLevel').slider().on('slide', function(ev){
//            $('#cloudLevel-m').text(ev.value + ' meters');
//        });
//        $('#cloudHeight').slider().on('slide', function(ev){
//            $('#cloudHeight-m').text(ev.value + ' meters');
//        });
        $('#precLevel').slider().on('slide', function(ev){
            $('#precLevel-m').text(ev.value);
        });
        $('#turbulence').slider().on('slide', function(ev){
            $('#turbulence-m').text(ev.value);
        });
        $('#temperature').slider().on('slide', function(ev){
            $('#temperature-m').text(ev.value + ' (cel)');
        });

        util.setSelectedSelectItem('precType', state.getCurrentMission().weather.precType);
        //util.setSelectedSelectItem('cloudConfig', state.getCurrentMission().weather.cloudConfig);
        $('#cloudConfig').children().removeAttr('selected');
        console.log("Escaping weather into: " + replaceAll(state.getCurrentMission().weather.cloudConfig, '\\', '\\\\'));
        $('#cloudConfig').find('option[value="'+ replaceAll(state.getCurrentMission().weather.cloudConfig, '\\', '\\\\') +'"]').attr('selected','selected');

        $('#wl0h').slider().on('slide', function(ev){
            $('#wl0h-m').text(ev.value);
        });
        $('#wl0s').slider().on('slide', function(ev){
            $('#wl0s-m').text(ev.value);
        });
        $('#wl500h').slider().on('slide', function(ev){
            $('#wl500h-m').text(ev.value);
        });
        $('#wl500s').slider().on('slide', function(ev){
            $('#wl500s-m').text(ev.value);
        });
        $('#wl1000h').slider().on('slide', function(ev){
            $('#wl1000h-m').text(ev.value);
        });
        $('#wl1000s').slider().on('slide', function(ev){
            $('#wl1000s-m').text(ev.value);
        });
        $('#wl2000h').slider().on('slide', function(ev){
            $('#wl2000h-m').text(ev.value);
        });
        $('#wl2000s').slider().on('slide', function(ev){
            $('#wl2000s-m').text(ev.value);
        });
        $('#wl5000h').slider().on('slide', function(ev){
            $('#wl5000h-m').text(ev.value);
        });
        $('#wl5000s').slider().on('slide', function(ev){
            $('#wl5000s-m').text(ev.value);
        });

        $('#weatherModal').modal({backdrop:'static'});
    }

    this.saveWeather = function() {

//        state.getCurrentMission().weather.cloudLevel = $('#cloudLevel').val();
//        state.getCurrentMission().weather.cloudHeight = $('#cloudHeight').val();
        state.getCurrentMission().weather.precLevel = $('#precLevel').val();
        state.getCurrentMission().weather.precType = $('#precType').val();
        state.getCurrentMission().weather.cloudConfig = $('#cloudConfig').val();
        state.getCurrentMission().weather.turbulence = $('#turbulence').val();
        state.getCurrentMission().weather.temperature = $('#temperature').val();

        var altitudes = [0, 500, 1000, 2000, 5000];
        for(var a = 0;a < 5; a++) {
            state.getCurrentMission().weather.windLayers[a].headingDegrees = $('#wl'+altitudes[a]+'h').val();
            state.getCurrentMission().weather.windLayers[a].speed = $('#wl'+altitudes[a]+'s').val();
        }

        rest.updateMission(state.getCurrentMission(), function(data) {
            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

    var escapeRegExp = function(string) {
        return string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
    }


    var replaceAll = function(string, find, replace) {
        return string.replace(new RegExp(escapeRegExp(find), 'g'), replace);
    }

}