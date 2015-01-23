var util = new function() {

    this.notNull = function(obj) {
        return typeof obj != 'undefined' && obj != null;
    }

    this.setSelectedSelectItem = function(selectId, val) {
        $('#' + selectId).children().removeAttr('selected');
        $('#' + selectId).find('option[value="'+val+'"]').attr('selected','selected');
    }

    this.populateSelect = function(id, obj, field, items) {
        $('#' + id).empty();
        $(items).each(function(i, value) {
            $('#' + id).append('<option value="'+value+'">'+value+'</option>');
        });
        $('#' + id).unbind().change(function() {
            obj[field] = $('#' + id).val();
            rest.updateMission(state.getCurrentMission(), function() {
                console.log("Saved mission after property change.");
            })
        });
        util.setSelectedSelectItem(id, obj[field]);
    }

    this.populateSelectKeyVal = function(id, obj, field, items) {
        $('#' + id).empty();
        $(items).each(function(i, value) {
            $('#' + id).append('<option value="'+value.value+'">'+value.name+'</option>');
        });
        $('#' + id).unbind().change(function() {
            obj[field] = $('#' + id).val();
            rest.updateMission(state.getCurrentMission(), function() {
                console.log("Saved mission after property change.");
            })
        });
        util.setSelectedSelectItem(id, obj[field]);
    }

    this.bindTextField = function(id, obj, field) {
        $('#' + id).unbind().focusout(function() {
            obj[field] = $('#' + id).val();
            rest.updateMission(state.getCurrentMission(), function() {
                console.log("Saved mission after text change.");
                maprenderer.redraw();
            })
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
}