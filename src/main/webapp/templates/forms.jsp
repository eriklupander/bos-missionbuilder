
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
        <!--
        <select  class="form-control" id="planes-edit-group-altitude">
        </select>
        -->
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
    <!--
    <div class="propertyelement">
        <label>Formation</label>
        <select  class="form-control" id="ground-group-edit-group-formation">
        </select>
    </div>
    -->
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

    <div class="propertyelement" class="" id="waypoint-edit-action-target-wrapper">
        <label>Target</label>
        <select class="form-control" id="waypoint-edit-action-target">
        </select>
        <i>Only applicable to COVER and ATTACK commands</i>
    </div>

    <div class="propertyelement">
        <label>Action radius</label>
        <input id="waypoint-edit-radius" type="text" class="slider" style="width:200px;" value=""
               data-slider-min="100" data-slider-max="5000" data-slider-step="50" data-slider-value="{{area}}"
               data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        <div id="action-radius-text" class="text-center">{{area}} meters</div>
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
        <label>Generate AAA at bridges</label>
        <input id="edit-mission-gen-bridge-aaa" class="checkbox" type="checkbox" value="true"> <br/>
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