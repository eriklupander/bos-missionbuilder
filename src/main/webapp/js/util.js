var util = new function() {

    this.notNull = function(obj) {
        return typeof obj != 'undefined' && obj != null;
    }

    this.isNull = function(obj) {
        return obj == null || typeof obj == 'undefined';
    }

    this.setSelectedSelectItem = function(selectId, val) {
        $('#' + selectId).children().removeAttr('selected');
        $('#' + selectId).find('option[value="'+val+'"]').attr('selected','selected');
    }

    this.populateSelect = function(id, obj, field, items, extraCallback) {
        $('#' + id).empty();
        $(items).each(function(i, value) {
            $('#' + id).append('<option value="'+value+'">'+value+'</option>');
        });
        $('#' + id).unbind().change(function() {
            var newValue = $('#' + id).val();

            setValueOnProperty(obj, field, newValue);

            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);
                console.log("Saved mission after property change.");
                maprenderer.redraw();
            });

            if(util.notNull(extraCallback)) {
                extraCallback(id, obj, field, items, newValue);
            }
        });
        util.setSelectedSelectItem(id, getValueOnProperty(obj, field));
    }

    this.populateSelectKeyVal = function(id, obj, field, items, extraCallback) {
        $('#' + id).empty();
        $(items).each(function(i, value) {
            $('#' + id).append('<option value="'+value.value+'">'+value.name+'</option>');
        });
        $('#' + id).unbind().change(function() {
            var newValue = $('#' + id).val();

            setValueOnProperty(obj, field, newValue);
            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);
                console.log("Saved mission after property change.");
                maprenderer.redraw();
            });

            if(util.notNull(extraCallback)) {
                extraCallback(id, obj, field, items, newValue);
            }

        });
        util.setSelectedSelectItem(id, getValueOnProperty(obj, field));
    }

    this.bindTextField = function(id, obj, field) {
        $('#' + id).on('focusout', function() {
            obj[field] = $('#' + id).val();
            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);
                console.log("Saved mission after text change.");
                maprenderer.redraw();
            })
        });
    }

    this.bindTextArea = function(id, obj, field) {
        $('#' + id).on('change', function() {
            obj[field] = $('#' + id).val();
            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);
                console.log("Saved mission after textarea change.");
                maprenderer.redraw();
            })
        });
    }

    this.bindCheckbox = function(id, obj, field) {
        //if(obj[field] == true || obj[field] == "true") {
        var isChecked = getValueOnProperty(obj, field) === true || getValueOnProperty(obj, field) == "true";
        if(isChecked) {
            $('#' + id).attr('checked','checked');
        } else {
            $('#' + id).removeAttr('checked');
        }
        $('#' + id).on('change', function() {
            //obj[field] = $('#' + id).prop('checked');
            setValueOnProperty(obj, field, $('#' + id).prop('checked'));
            rest.updateMission(state.getCurrentMission(), function(data) {
                state.setCurrentMission(data);
                console.log("Saved mission after checkbox change.");
                maprenderer.redraw();
            });
        });
    }

    this.initHandlebarHelpers = function() {
//        Handlebars.registerHelper('bselect', function(items, options) {
//            var out = '<select>';
//
//            for(var i=0, l=items.length; i<l; i++) {
//                out = out + '<option value="' + options.fn(items[i]) + '">' + options.fn(items[i]) + '</option>';
//            }
//
//            return out + "</select>";
//        });
    }

    var setValueOnProperty = function(obj, field, newValue) {
        field = field.replace(/^\./, '');
        if(field.indexOf('.') == -1) {
            obj[field] = newValue;
        } else {
            var parts = field.split('.');
            var currentLevel = obj;
            for(var a = 0; a < parts.length; a++) {

                if(a == parts.length -1) {
                    currentLevel[parts[a]] = newValue;
                    return;
                }
                currentLevel = currentLevel[parts[a]];
            }
        }
    }

    var getValueOnProperty = function(obj, field) {
        field = field.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
        field = field.replace(/^\./, '');           // strip a leading dot
        var a = field.split('.');
        while (a.length) {
            var n = a.shift();
            if(util.notNull(n)) {
                if (n in obj) {
                    obj = obj[n];
                } else {
                    return;
                }
            }
        }
        return obj;
    }



    this.angle = function(p1x, p1y, p2x, p2y) {
        var xDiff = p2x - p1x;
        var yDiff = p2y - p1y;
        return Math.atan2(yDiff, xDiff) * (180/pi);
    }
}