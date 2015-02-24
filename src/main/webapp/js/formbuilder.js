var formbuilder = new function() {

    this.buildCommandForm = function(obj, commandDefinition) {
        var props = commandDefinition.commandModel.commandProperties;

        for(var a = 0; a < props.length; a++) {
            var prop = props[a];
            switch(prop.propertyType) {
                case "SELECT":
                    break;
                case "SLIDER":
                    break;
            }
        }
    }

}