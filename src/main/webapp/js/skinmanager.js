var skinmanager = new function() {

    this.save = function() {
        rest.updateMission(state.getCurrentMission(), function(data) {
            state.setCurrentMission(data);
            maprenderer.redraw();
        });
    }

    this.openSkinManager = function() {
        $('#skinsModal').modal({backdrop:'static'});

        var cm = state.getCurrentMission();
        var side = cm.sides[state.getCurrentCountry()];
        var tpl = '';
        for(var a = 0; a < side.unitGroups.length; a++) {
            var ug = side.unitGroups[a];
            if(ug.groupType != 'AIR_GROUP') {
                continue;
            }

            tpl += ' <div class="row spacer">' +
                   '    <div class="col-md-5"><strong>'  + ug.name + ' ('+ug.type+')' + '</strong></div>' +
                    '   <div class="col-md-1">Leader</div>' +
                    '   <div class="col-md-6"><select class="skinselect" id="'+ ug.clientId + '-0'+ '" airtype="' + ug.type + '"/></div>' +
                    '</div>';
            for(var index = 1; index < side.unitGroups[a].size; index++) {
                tpl += ' <div class="row">' +
                    '       <div class="col-md-5"></div>' +
                    '       <div class="col-md-1">#'+ (index+1) +'</div>' +
                    '       <div class="col-md-6"><select class="skinselect"  id="'+ ug.clientId + '-'+index+ '"airtype="' + ug.type + '"/></div>' +
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
    }

    var setSkinForClientId = function(selectId, value) {
        var clientId = selectId.split("-")[0];
        var index = selectId.split("-")[1];
        var objects = state.getCurrentMission().sides[state.getCurrentCountry()].unitGroups;
        for(var a = 0; a < objects.length; a++) {
            if(objects[a].clientId == clientId) {
                var skinsArr = objects[a].skins;
                // TODO handle if size is smaller than index
                skinsArr[index] = value;
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
                    }
                    return "Default";
                } else {
                    return skinsArr[index];
                }
            }
        }
    }
};