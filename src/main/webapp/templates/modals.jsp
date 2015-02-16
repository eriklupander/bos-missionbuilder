<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Scan result</h4>
            </div>
            <div class="modal-body">
                <p id="modal-content"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="createMissionModal">
    <div id="mapModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Create Mission</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Mission name</label>
                    <input id="create-mission-name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Mission date & time</label>
                    <input id="create-mission-date" class="form-control" value="11.12.1942">
                    <input id="create-mission-time" class="form-control" value="9:30:0">
                    <i>MM.DD.YYYY and HH:mm:ss</i>
                </div>
                <div class="form-group">
                    <label>Generate AAA at airfields</label>
                    <input id="create-mission-gen-airfield-aaa" class="checkbox" type="checkbox" value="true" checked>  <br/>
                    <label>Generate AAA at bridges</label>
                    <input id="create-mission-gen-bridge-aaa" class="checkbox" type="checkbox" value="false">   <br/>
                    <label>Include Stalingrad City buildings</label>
                    <input id="create-mission-include-stalingrad" class="checkbox" type="checkbox" value="true" checked>
                </div>
                <div class="form-group">
                    <label>Mission briefing</label>
                    <textarea id="create-mission-desc" rows="10" class="form-control"></textarea>
                </div>
                <div class="form-group text-center">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="missionbuilder.createMission();">Create</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="editMissionModal">
    <div id="editModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Edit Mission</h4>
            </div>
            <div class="modal-body" id="edit-mission-body">

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="loadMissionModal">
    <div id="loadModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Load Mission</h4>
            </div>
            <div id="load-mission-body" class="modal-body">

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->




<div class="modal fade" id="createUnitGroupModal">
    <div id="createUnitGroupModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Create Airplane Group</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" id="air-group-type">

                </div>
                <div class="form-group">
                    <label>Group name</label>
                    <input id="create-unit-group-name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Starting altitude</label>     <br/>
                    <input id="create-unit-group-altitude" type="text" class="slider" style="width:570px;" value=""
                           data-slider-min="0" data-slider-max="10000" data-slider-step="10" data-slider-value="1500"
                           data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="hide">
                    <br/>
                    <div id="ycreate-text">1500 meters</div>

                </div>
                <div class="form-group">
                    <label>Number of units in group</label>
                    <select id="create-unit-group-size" class="form-control">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Skill level</label>
                    <select id="create-unit-group-skill" class="form-control">
                        <option value="4">Ace</option>
                        <option value="3">Veteran</option>
                        <option value="2">Regular</option>
                        <option value="1">Rookie</option>
                        <option value="0">Player</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Loadout</label>
                    <select id="create-unit-group-loadout" class="form-control">
                        <option value="0">Default</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Unit group description</label>
                    <textarea id="create-unit-group-desc" rows="5" class="form-control"></textarea>
                </div>
                <div class="form-group text-center">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="missionbuilder.addUnitGroupToMission();">Create</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- GROUND UNIT MODAL DIALOG -->
<div class="modal fade" id="createGroundUnitGroupModal">
    <div id="createGroundUnitGroupModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Create Ground Group</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Unit type</label>
                    <select id="create-ground-unit-group-type" class="form-control">
                    </select>
                </div>
                <div class="form-group">
                    <label>Group name</label>
                    <input id="create-ground-unit-group-name" class="form-control">
                </div>

                <div class="form-group">
                    <label>Number of units in group</label>
                    <select id="create-ground-unit-group-size" class="form-control">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Skill level</label>
                    <select id="create-ground-unit-group-skill" class="form-control">
                        <option value="4">Ace</option>
                        <option value="3">Veteran</option>
                        <option value="2">Regular</option>
                        <option value="1">Rookie</option>
                        <option value="0">Player</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Formation</label>
                    <select id="create-ground-unit-group-formation" class="form-control">
                    </select>
                </div>

                <div class="form-group">
                    <label>Unit group description</label>
                    <textarea id="create-ground-unit-group-desc" rows="5" class="form-control"></textarea>
                </div>
                <div class="form-group text-center">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="missionbuilder.addGroundUnitGroupToMission();">Create</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->










<!-- STATIC GROUND OBJECT MODAL DIALOG -->
<div class="modal fade" id="createStaticObjectGroupModal">
    <div id="createStaticObjectGroupModalDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Create Static Object Group</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" id="create-static-object-group-type">

                </div>
                <div class="form-group">
                    <label>Object/Group name</label>
                    <input id="create-static-object-group-name" class="form-control">
                </div>

                <div class="form-group">
                    <label>Number of objects to create in group</label>
                    <select id="create-static-object-group-size" class="form-control">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Static object description</label>
                    <textarea id="create-static-object-group-desc" rows="5" class="form-control"></textarea>
                </div>
                <div class="form-group text-center">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="missionbuilder.addStaticObjectGroupToMission();">Create</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->