<script id="weather-tpl" type="text/x-handlebars-template">
    <!--
    <div class="row">
        <div class="col-md-3">Cloud level</div>
        <div class="col-md-2" id="cloudLevel-m">{{cloudLevel}} meters</div>
        <div class="col-md-5">
            <input id="cloudLevel" type="text" class="slider" style="width:200px;" value="{{cloudLevel}}"
                   data-slider-min="0" data-slider-max="10000" data-slider-step="10"
                   data-slider-value="{{cloudLevel}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <hr style="margin-top:10px;margin-bottom:10px;"/>
    <div class="row">
        <div class="col-md-3">Cloud height</div>
        <div class="col-md-2" id="cloudHeight-m">{{cloudHeight}} meters</div>
        <div class="col-md-5">
            <input id="cloudHeight" type="text" class="slider" style="width:200px;" value="{{cloudHeight}}"
                   data-slider-min="0" data-slider-max="10000" data-slider-step="10"
                   data-slider-value="{{cloudHeight}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <hr style="margin-top:10px;margin-bottom:10px;"/>
    -->
    <div class="row">
        <div class="col-md-3">Precipitation level</div>
        <div class="col-md-2" id="precLevel-m">{{precLevel}}</div>
        <div class="col-md-5">
            <input id="precLevel" type="text" class="slider" style="width:200px;" value="{{precLevel}}"
                   data-slider-min="0" data-slider-max="100" data-slider-step="1"
                   data-slider-value="{{precLevel}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <hr style="margin-top:10px;margin-bottom:10px;"/>
    <div class="row">
        <div class="col-md-3">Precipitation type</div>
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <select id="precType">
                <option value="0">None</option>
                <option value="2">Snow</option>
            </select></div>
    </div>

    <div class="row">
        <div class="col-md-3">Cloud config</div>
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <select id="cloudConfig">
                <option value="winter\00_clear_03\sky.ini">Clear (winter)</option>
                <option value="winter\01_Light_08\sky.ini">Light (winter)</option>
                <option value="winter\02_Medium_07\sky.ini">Medium (winter)</option>
                <option value="winter\03_Heavy_03\sky.ini">Heavy (winter)</option>
                <option value="winter\04_Overcast_01\sky.ini">Overcast (winter)</option>
            </select>
        </div>
    </div>

    <div class="row spacer">
        <div class="col-md-3">Turbulence</div>
        <div class="col-md-2" id="turbulence-m">{{turbulence}}</div>
        <div class="col-md-5">
            <input id="turbulence" type="text" class="slider" style="width:200px;" value="{{turbulence}}"
                   data-slider-min="0" data-slider-max="10" data-slider-step="1"
                   data-slider-value="{{turbulence}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>

    <div class="row spacer">
        <div class="col-md-3">Temperature </div>
        <div class="col-md-2" id="temperature-m">{{temperature}} (cel)</div>
        <div class="col-md-5">
            <input id="temperature" type="text" class="slider" style="width:200px;" value="{{temperature}}"
                   data-slider-min="-40" data-slider-max="40" data-slider-step="1"
                   data-slider-value="{{temperature}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <hr style="margin-top:10px;margin-bottom:10px;"/>
    <strong>Wind Layers</strong><br/>
    <div class="row">
        <div class="col-md-2 text-info">Altitude</div>

        <div class="col-md-5 text-info">Heading 0-360</div>

        <div class="col-md-5 text-info">Wind speed m/s</div>
    </div>

    <div class="row">
        <div class="col-md-2">0</div>
        <div class="col-md-1 text-info" id="wl0h-m">{{windLayers.[0].headingDegrees}}</div>
        <div class="col-md-4">
            <input id="wl0h" type="text" class="slider" style="width:150px;" value="{{windLayers.[0].headingDegrees}}"
                  data-slider-min="0" data-slider-max="360" data-slider-step="1"
                  data-slider-value="{{windLayers.[0].headingDegrees}}"
                  data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
        <div class="col-md-1 text-info" id="wl0s-m">{{windLayers.[0].speed}}</div>
        <div class="col-md-4">
            <input id="wl0s" type="text" class="slider" style="width:150px;" value="{{windLayers.[0].speed}}"
                   data-slider-min="0" data-slider-max="36" data-slider-step="1"
                   data-slider-value="{{windLayers.[0].speed}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>

    <div class="row">
        <div class="col-md-2">500</div>
        <div class="col-md-1 text-info" id="wl500h-m">{{windLayers.[1].headingDegrees}}</div>
        <div class="col-md-4">
            <input id="wl500h" type="text" class="slider" style="width:150px;" value="{{windLayers.[1].headingDegrees}}"
                   data-slider-min="0" data-slider-max="360" data-slider-step="1"
                   data-slider-value="{{windLayers.[1].headingDegrees}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
        <div class="col-md-1 text-info" id="wl500s-m">{{windLayers.[1].speed}}</div>
        <div class="col-md-4">
            <input id="wl500s" type="text" class="slider" style="width:150px;" value="{{windLayers.[1].speed}}"
                   data-slider-min="0" data-slider-max="36" data-slider-step="1"
                   data-slider-value="{{windLayers.[1].speed}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">1000</div>
        <div class="col-md-1 text-info" id="wl1000h-m">{{windLayers.[2].headingDegrees}}</div>
        <div class="col-md-4">
            <input id="wl1000h" type="text" class="slider" style="width:150px;" value="{{windLayers.[2].headingDegrees}}"
                   data-slider-min="0" data-slider-max="360" data-slider-step="1"
                   data-slider-value="{{windLayers.[2].headingDegrees}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
        <div class="col-md-1 text-info" id="wl1000s-m">{{windLayers.[2].speed}}</div>
        <div class="col-md-4">
            <input id="wl1000s" type="text" class="slider" style="width:150px;" value="{{windLayers.[2].speed}}"
                   data-slider-min="0" data-slider-max="36" data-slider-step="1"
                   data-slider-value="{{windLayers.[2].speed}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">2000</div>
        <div class="col-md-1 text-info" id="wl2000h-m">{{windLayers.[3].headingDegrees}}</div>
        <div class="col-md-4">
            <input id="wl2000h" type="text" class="slider" style="width:150px;" value="{{windLayers.[3].headingDegrees}}"
                   data-slider-min="0" data-slider-max="360" data-slider-step="1"
                   data-slider-value="{{windLayers.[3].headingDegrees}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
        <div class="col-md-1 text-info" id="wl2000s-m">{{windLayers.[3].speed}}</div>
        <div class="col-md-4">
            <input id="wl2000s" type="text" class="slider" style="width:150px;" value="{{windLayers.[3].speed}}"
                   data-slider-min="0" data-slider-max="36" data-slider-step="1"
                   data-slider-value="{{windLayers.[3].speed}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">5000</div>
        <div class="col-md-1 text-info" id="wl5000h-m">{{windLayers.[4].headingDegrees}}</div>
        <div class="col-md-4">
            <input id="wl5000h" type="text" class="slider" style="width:150px;" value="{{windLayers.[4].headingDegrees}}"
                   data-slider-min="0" data-slider-max="360" data-slider-step="1"
                   data-slider-value="{{windLayers.[4].headingDegrees}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
        <div class="col-md-1 text-info" id="wl5000s-m">{{windLayers.[4].speed}}</div>
        <div class="col-md-4">
            <input id="wl5000s" type="text" class="slider" style="width:150px;" value="{{windLayers.[4].speed}}"
                   data-slider-min="0" data-slider-max="36" data-slider-step="1"
                   data-slider-value="{{windLayers.[4].speed}}"
                   data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
        </div>
    </div>
    <!--
     CloudLevel = 500;
CloudHeight = 200;
PrecLevel = 10;
PrecType = 0;
CloudConfig = "00_Clear_08\sky.ini";
SeaState = 0;
Turbulence = 0;
TempPressLevel = 0;
Temperature = -15;
Pressure = 760;
WindLayers
{
0 :     314 :     0;
500 :     306.791 :     0;
1000 :     294.693 :     0;
2000 :     289.836 :     0;
5000 :     280.331 :     0;
}
-->

    <!-- /.modal-content -->
</script>