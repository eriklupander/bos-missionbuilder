var flightmanager = new function() {

    this.save = function() {
        rest.updateMission(state.getCurrentMission(), function(data) {
            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

    this.openFlightManager = function() {
        $('#skinsModal').modal({backdrop:'static'});

        var cm = state.getCurrentMission();
        var side = cm.sides[state.getCurrentCountry()];
        var tpl = ' <div class="row h4"><div class="col-md-4">Flight Group</div><div class="col-md-1">#</div><div class="col-md-5">Skin</div><div class="col-md-2">AI level</div></div>';

        for(var a = 0; a < side.unitGroups.length; a++) {
            var ug = side.unitGroups[a];
            if(ug.groupType != 'AIR_GROUP') {
                continue;
            }

            tpl += ' <div class="row spacer">' +
                   '    <div class="col-md-4"><strong>'  + ug.name + ' ('+ug.type+')' + '</strong></div>' +
                    '   <div class="col-md-1">Leader</div>' +
                    '   <div class="col-md-5"><select class="skinselect" id="'+ ug.clientId + '-0" airtype="' + ug.type + '"/></div>' +
                '       <div class="col-md-2"><select class="aiselect" id="ailevel-'+ ug.clientId + '-0"/></div>' +
                '</div>';
            for(var index = 1; index < side.unitGroups[a].size; index++) {
                tpl += ' <div class="row">' +
                    '       <div class="col-md-4"></div>' +
                    '       <div class="col-md-1">#'+ (index+1) +'</div>' +
                    '       <div class="col-md-5"><select class="skinselect" id="'+ ug.clientId + '-'+index+ '"airtype="' + ug.type + '"/></div>' +
                    '       <div class="col-md-2"><select class="aiselect" id="ailevel-'+ ug.clientId + '-'+index+ '"/></div>' +
                    '   </div>';
            }
            tpl += ' </div>';
        }
        $('#skins-body').empty();
        $('#skins-body').append(tpl);

        rest.getSkins(function(data) {

            $.each(data, function( planeType, value) {

                var select =  $('select[airtype="' + planeType + '"]');
                $(select).empty();
                var selectedSkin = null;
                //var selectedSkin = getSkinForClientId($(select).attr('id'), side.unitGroups);
                for(var a = 0; a < value.length; a++) {
                    $(select).append('<option value="'+value[a]+'">' + value[a] + '</option>');
                }
            });

            $('.skinselect').each(function(i, obj) {
                var id = $(obj).attr('id');
                var skin = getSkinForClientId(id, side.unitGroups);
                console.log(skin);

                var opt = $(obj).find('option[value="'+skin+'"]');
                if(util.notNull(opt)) {
                    opt.attr('selected','selected');
                }

                $(obj).unbind().change(function() {
                    setSkinForClientId(id, $(obj).val());
                });
            });
        });

        var aiLevelList = statics.getSkillsAIOnly();
        var aiSelects =  $('.aiselect');
        $.each(aiSelects, function(index, select) {
            $(select).empty();
            for(var a = 0; a < aiLevelList.length; a++) {
                $(select).append('<option value="'+aiLevelList[a].value+'">' + aiLevelList[a].name + '</option>');
            }
        });





        $('.aiselect').each(function(i, obj) {
            var id = $(obj).attr('id');
            var aiLevel = getAIForClientId(id, side.unitGroups);

            var opt = $(obj).find('option[value="'+aiLevel+'"]');
            if(util.notNull(opt)) {
                opt.attr('selected','selected');
            }

            $(obj).unbind().change(function() {
                setAILevelForClientId(id, $(obj).val());
            });
        });
    }

    var setSkinForClientId = function(selectId, value) {
        var clientId = selectId.split("-")[0];
        var index = selectId.split("-")[1];
        var objects = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups;
        for(var a = 0; a < objects.length; a++) {
            if(objects[a].clientId == clientId) {
                objects[a].skins[index] = value;
                break;
            }
        }
    }

    var setAILevelForClientId = function(selectId, value) {
        var clientId = selectId.split("-")[1];
        var index = selectId.split("-")[2];
        var objects = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups;
        for(var a = 0; a < objects.length; a++) {
            if(objects[a].clientId == clientId) {
                objects[a].aiLevels[index] = value;
                break;
            }
        }
    }

    var getSkinForClientId = function(selectId, objects) {
        var clientId = selectId.split("-")[0];
        var index = selectId.split("-")[1];

        for(var a = 0; a < objects.length; a++) {
            if(objects[a].clientId == clientId) {
                var skinsArr = objects[a].skins;
                if(index >= skinsArr.length) {
                    // TODO we probably need to auto-adapt the array size here
                    for(var b = skinsArr.length; b < objects[a].size; b++) {
                        skinsArr.push("Default");
                        setSkinForClientId(selectId, "Default");
                    }
                    return "Default";
                } else {
                    return skinsArr[index];
                }
            }
        }
    }

    var getAIForClientId = function(selectId, objects) {
        var clientId = selectId.split("-")[1];
        var index = selectId.split("-")[2];

        for(var a = 0; a < objects.length; a++) {
            if(objects[a].clientId == clientId) {
                var groupAILevel = objects[a].aiLevel;
                var aiLevelArr = objects[a].aiLevels;
                // Handle player group...
                if(groupAILevel == 0 && (objects[a].playerIndex-1) == index) {
                    // Spagetthi code...
                    $('#' + selectId).attr('disabled','disabled');
                    $('#' + selectId).addClass('disabled');
                    $('#' + selectId).css('color','purple');
                    $('#' + selectId).empty();
                    $('#' + selectId).append('<option value="0">Player</option>');
                    setAILevelForClientId(selectId, 0);
                    return 0;
                } else {
                    $('#' + selectId).removeClass('disabled');
                    if(index >= aiLevelArr.length) {
                        // TODO we probably need to auto-adapt the array size here
                        for(var b = aiLevelArr.length; b < objects[a].size; b++) {
                            aiLevelArr.push(groupAILevel);
                            setAILevelForClientId(selectId, groupAILevel);
                        }
                        return "Default";
                    } else {
                        return aiLevelArr[index];
                    }
                }

            }
        }
    }
};