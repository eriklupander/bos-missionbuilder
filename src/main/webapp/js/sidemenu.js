var sidemenu = new function() {
    this.updateMenu = function() {
        if(util.notNull(state.getSelectedWaypoint())) {

        }
        if(util.notNull(state.getSelectedUnitGroup()) ) {
             $('#menu-add-waypoint').removeClass('hidden');
        }  else {
             $('#menu-add-waypoint').addClass('hidden');
        }
    }
}