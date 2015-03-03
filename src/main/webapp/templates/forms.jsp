
<script id="planes-tpl" type="text/x-handlebars-template">
    <label>Unit type</label>
    <select  class="form-control" id="create-unit-group-type">
        {{#each this}}
        <option value="{{this}}">{{this}}</option>
        {{/each}}
    </select>
</script>

<script id="static-objects-types-tpl" type="text/x-handlebars-template">
    <label>Static object type</label>
    <select  class="form-control" id="create-static-object-type">
        {{#each this}}
        <option value="{{this}}">{{this}}</option>
        {{/each}}
    </select>
</script>

<script id="planes-edit-tpl" type="text/x-handlebars-template">
    <h4 class="text-center">Edit {{name}}</h4>
    <div class="propertyelement">
        <label>Type</label>
        <select  class="form-control" id="planes-edit-group-type">
        </select>
    </div>
    <div class="propertyelement">
        <label>Name</label>
        <input id="planes-edit-group-name" class="form-control" type="text" value="{{name}}"/>
    </div>
    <div class="propertyelement">
        <label>Altitude</label>
        <input id="planes-edit-group-altitude" type="text" class="slider" style="width:200px;" value=""
               data-slider-min="0" data-slider-max="10000" data-slider-step="10" data-slider-value="{{y}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="y-text" class="text-center">{{y}} meters</div>
    </div>
    <div class="propertyelement">
        <label>Heading (0-359)</label>
        <input id="planes-edit-group-heading" class="form-control" type="text" value="{{yOri}}"/>
    </div>
    <!--
    <div class="propertyelement">
        <label>Formation</label>
        <select  class="form-control" id="planes-edit-group-formation">
        </select>
    </div>
    -->
    <div class="propertyelement">
        <label>Number of units</label>
        <select  class="form-control" id="planes-edit-group-size">
        </select>
    </div>
    <div class="propertyelement hidden" id="planes-edit-group-player-index-div">
        <label>Player in formation</label>
        <select  class="form-control" id="planes-edit-group-player-index">
        </select>
    </div>
    <div class="propertyelement">
        <label>Skill level</label>
        <select class="form-control" id="planes-edit-group-skill">
        </select>
    </div>
    <div class="propertyelement">
        <label>Loadout</label>
        <select  class="form-control" id="planes-edit-group-loadout">
        </select>
    </div>
    <div class="form-group">
        <label>Icons & Labels</label>
        <div class="row">
            <div class="col-md-6"><input type="checkbox" id="planes-edit-group-icon"/> Show icon in briefing</div>
            <div class="col-md-6"><input type="checkbox" id="planes-edit-group-icon-waypoint"/> Show waypoints in briefing</div>
        </div>
    </div>
    <div class="propertyelement">
        <label>Description</label>
        <textarea id="planes-edit-group-description" class="form-control">{{description}}</textarea>
    </div>
    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete unit</button>
    </div>

</script>

<script id="ground-group-edit-tpl" type="text/x-handlebars-template">
    <h4>Edit {{name}}</h4>
    <div class="propertyelement">
        <label>Type</label>
        <select  class="form-control" id="ground-group-edit-group-type">
        </select>
    </div>
    <div class="propertyelement">
        <label>Name</label>
        <input id="ground-group-edit-group-name" class="form-control" type="text" value="{{name}}"/>
    </div>
    <div class="propertyelement">
        <label>Heading (0-359)</label>
        <input id="ground-group-edit-group-heading" class="form-control" type="text" value="{{yOri}}"/>
    </div>

    <div class="propertyelement">
        <label>Formation</label>
        <select  class="form-control" id="ground-group-edit-group-formation">
        </select>
    </div>

    <div class="propertyelement">
        <label>Number of units</label>
        <select  class="form-control" id="ground-group-edit-group-size">
        </select>
    </div>
    <div class="propertyelement">
        <label>Skill level</label>
        <select class="form-control" id="ground-group-edit-group-skill">
        </select>
    </div>
    <div class="form-group">
        <label>Icons & Labels</label>
        <div class="row">
            <div class="col-md-6"><input type="checkbox" id="ground-group-edit-group-icon"/> Show icon in briefing</div>
            <div class="col-md-6"><input type="checkbox" id="ground-group-edit-group-icon-waypoint"/> Show waypoints in briefing</div>
        </div>
    </div>
    <div class="propertyelement">
        <label>Description</label>
        <textarea id="ground-group-edit-group-description" class="form-control">{{description}}</textarea>
    </div>
    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete group</button>
    </div>

</script>

<script id="trigger-edit-tpl" type="text/x-handlebars-template">
    <h4>Edit trigger</h4>

    <div class="propertyelement">
        <label>Name</label>
        <input id="trigger-edit-name" class="form-control" type="text" value="{{name}}"/>
    </div>


    <div class="propertyelement">
        <label>Radius</label>
        <input type="text" class="slider" style="width:200px;" value=""
               data-slider-min="100" data-slider-max="20000" data-slider-step="100" data-slider-value="{{radius}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="radius-text" class="text-center">{{radius}}</div>
    </div>

    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete trigger</button>
    </div>
</script>

<script id="static-object-group-edit-tpl" type="text/x-handlebars-template">
    <h4>Edit Static Object Group</h4>

    <div class="propertyelement">
        <label>Name</label>
        <input id="static-object-group-edit-name" class="form-control" type="text" value="{{name}}"/>
    </div>

    <div class="propertyelement">
        <label>Type</label>
        <select  class="form-control" id="static-object-group-edit-type">
        </select>
    </div>

    <div class="propertyelement">
        <label>Number of units</label>
        <select  class="form-control" id="static-object-group-edit-size">
        </select>
    </div>

    <div class="propertyelement">
        <label>Heading (0-359)</label>
        <input id="static-object-group-edit-heading" class="form-control" type="text" value="{{yOri}}"/>
    </div>
    <div class="form-group">
        <label>Icons & Labels</label>
        <div class="row">
            <div class="col-md-6"><input type="checkbox" id="static-object-group-edit-group-icon"/> Show icon in briefing</div>
        </div>
    </div>

    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete</button>
    </div>
</script>

<script id="waypoint-edit-tpl" type="text/x-handlebars-template">
    <h4>Edit waypoints</h4>

    <div class="propertyelement">
        <label>Name</label>
        <input id="waypoint-edit-name" class="form-control" type="text" value="{{name}}"/>
    </div>

    <div class="propertyelement">
        <label>Speed</label>
        <input id="waypoint-edit-speed" type="text" class="slider" style="width:200px;" value=""
               data-slider-min="0" data-slider-max="700" data-slider-step="1" data-slider-value="{{speed}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="speed-text" class="text-center">{{speed}} km/h</div>
    </div>

    <div class="propertyelement">
        <label>Altitude</label>
        <input id="waypoint-edit-altitude" type="text" class="slider" style="width:200px;" value=""
               data-slider-min="0" data-slider-max="10000" data-slider-step="10" data-slider-value="{{y}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="y-text" class="text-center">{{y}} meters</div>
    </div>

    <div class="propertyelement">
        <label>Waypoint action</label>
        <select class="form-control" id="waypoint-edit-action">
        </select>
    </div>

    <div class="propertyelement">
        <label>Action priority</label>
        <select class="form-control" id="waypoint-edit-priority">
        </select>
    </div>

    <div class="propertyelement hidden" class="" id="waypoint-edit-action-target-div">
        <label>Target</label>
        <select class="form-control" id="waypoint-edit-action-target">
        </select>
    </div>

    <div class="propertyelement hidden" id="waypoint-edit-action-time-div">
        <label>Action duration</label>
        <input id="waypoint-edit-time" type="text" class="slider" style="width:200px;" value="{{action.properties.time}}"
               data-slider-min="0" data-slider-max="120" data-slider-step="1" data-slider-value="{{action.properties.time}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="waypoint-edit-time-text" class="text-center">{{action.properties.time}} minutes</div>
    </div>

    <div class="propertyelement hidden" id="waypoint-edit-action-area-div">
        <label>Action radius</label>
        <input id="waypoint-edit-area" type="text" class="slider" style="width:200px;" value="{{area}}"
               data-slider-min="100" data-slider-max="5000" data-slider-step="50" data-slider-value="{{area}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="waypoint-action-area-text" class="text-center">{{area}} meters</div>
    </div>

    <div class="propertyelement hidden" id="waypoint-edit-action-targets-div">
        <label>Targets</label>
        <div>
            <div class="row">
                <div class="col-md-4">Air</div>  <div class="col-md-4">Ground</div> <div class="col-md-4">Vehicles</div>

                <div class="col-md-4"><input id="waypoint-edit-action-targets-air" type="checkbox"></div>
                <div class="col-md-4"><input id="waypoint-edit-action-targets-ground" type="checkbox"></div>
                <div class="col-md-4"><input id="waypoint-edit-action-targets-vehicles" type="checkbox"></div>
            </div>
        </div>
    </div>

    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete</button>
    </div>
</script>




<script id="mission-edit-tpl" type="text/x-handlebars-template">
    <div class="form-group">
        <label>Mission name</label>
        <input id="edit-mission-name" class="form-control" value="{{name}}">
    </div>
    <div class="form-group">
        <label>Mission date & time</label>
        <input id="edit-mission-date" class="form-control" value="{{date}}">
        <input id="edit-mission-time" class="form-control" value="{{time}}">
        <i>MM.DD.YYYY and HH:mm:ss</i>
    </div>
    <div class="form-group">
        <label>Generate AAA at airfields</label>
        <input id="edit-mission-gen-airfield-aaa" class="checkbox" type="checkbox" value="true">  <br/>
      <!--
        <label>Generate AAA at bridges</label>
        <input id="edit-mission-gen-bridge-aaa" class="checkbox" type="checkbox" value="true"> <br/>
        -->
        <label>Include Stalingrad buildings</label>
        <input id="edit-mission-gen-include-stalingrad" class="checkbox" type="checkbox" value="true">
    </div>
    <div class="form-group">
        <label>Mission briefing</label>
        <textarea id="edit-mission-desc" rows="10" class="form-control">{{description}}</textarea>
    </div>
    <div class="form-group text-center">
        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="missionbuilder.saveMission();">Save</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
    </div>
</script>




<script id="effect-edit-tpl" type="text/x-handlebars-template">
    <h4>Edit effect</h4>

    <div class="propertyelement">
        <label>Effect type</label>
        <select id="effect-edit-effectType" class="form-control"/>
    </div>

    <div class="propertyelement">
        <label>Altitude</label>
        <div><i>Must be set manually to ground level by trial&error</i></div>
        <input id="effect-edit-y" type="text" class="slider" style="width:200px;" value="{{y}}"
               data-slider-min="0" data-slider-max="1000" data-slider-step="1" data-slider-value="{{y}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="effect-edit-y-text" class="text-center">{{y}} meters</div>
    </div>

    <div class="text-center propertyelement">
        <button class="btn btn-info" id="close"><i class="fa fa-close"></i> Close</button>
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete effect</button>
    </div>
</script>