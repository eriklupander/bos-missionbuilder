<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BoS Mission Builder</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/slider.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">BoS Mission Builder</a>
        </div>
        <ul class="nav navbar-left top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file"></i> <b class="caret"></b> Menu</a>
                <ul class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#" onclick="missionbuilder.openCreateDialog();">Create Mission</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.openLoadMissionDialog();">Load Mission</a>
                    </li>
                    <!--
                    <li>
                        <a href="#" onclick="missionbuilder.downloadMissionFile();">Download .Mission file</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.downloadLocalizationFile();">Download .eng file</a>
                    </li>
                    -->
                    <li id="missionFileLink" class="hidden">

                    </li>
                    <li id="localizationFileLink" class="hidden">

                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.exportMissionToDisk();">Export Mission to Disk</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown hidden"  id="country-select">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-flag"></i><b class="caret"></b> Select country</a>
                <ul id="selected-side" class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#" onclick="missionbuilder.selectCurrentCountry(201);"><i id="201" class="fa fa-check countryCheckedIcon"></i> Germany</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.selectCurrentCountry(101);"><i id="101" class="fa fa-check countryCheckedIcon hidden"></i> USSR</a>
                    </li>

                </ul>
            </li>
            <li class="dropdown"  id="filter-select">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-map-marker"></i> <b class="caret"></b> Map filters</a>
                <ul id="filter" class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('air_group');"><i id="air_group_check" class="fa fa-check"></i> Planes Groups</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('ground_group');"><i id="ground_group_check" class="fa fa-check"></i> Ground groups</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('static_object');"><i id="static_object_check" class="fa fa-check"></i> Static objects</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('trigger');"><i id="trigger_check" class="fa fa-check"></i> Triggers</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('airfields');"><i id="airfields_check" class="fa fa-check hidden"></i> Airfields</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${pageContext.request.remoteUser} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <!--
                    <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    -->
                    <li>
                        <a href="/logout.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        <!-- <form th:action="@{/logout}" method="post">
                            <input type="submit" value="Sign Out"/>
                        </form>           -->
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul id="rightmenu" class="nav navbar-nav side-nav hidden">
                <li>
                    <a href="#"  title="Click to edit basic data" onclick="missionbuilder.editMission();"> <h3 class="text-center" id="mission-name"></h3><div class="text-center" style="font-size:8pt;">Click to edit mission</div></a>
                </li>


                <li id="menu-add-flight" class="menuitem">
                    <a href="#" onclick="missionbuilder.addFlightGroup();"><i class="fa fa-fw fa-plus-circle"></i> Add flight</a>
                </li>
                <li id="menu-add-ground-group" class="menuitem">
                    <a href="#" onclick="missionbuilder.addGroundGroup();"><i class="fa fa-fw fa-plus-circle"></i> Add ground unit</a>
                </li>
                <li id="menu-add-static-object-group" class="menuitem">
                    <a href="#" onclick="missionbuilder.addStaticObjectGroup();"><i class="fa fa-fw fa-plus-circle"></i> Add static object group</a>
                </li>
                <li id="menu-add-waypoint" class="menuitem hidden">
                    <a href="#" onclick="missionbuilder.addWaypoint();"><i class="fa fa-fw fa-times-circle-o"></i> Add waypoint</a>
                </li>
                <li id="menu-add-trigger-zone" class="menuitem">
                    <a href="#" onclick="missionbuilder.addTriggerZone();"><i class="fa fa-fw fa-exclamation"></i> Add trigger zone</a>
                </li>

            </ul>


        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <canvas id="map"></canvas>

                </div>
            </div>
            <!-- /.row
            <ol class="breadcrumb">
                <li>
                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                </li>
                <li class="active">
                    <i class="fa fa-file"></i> Blank Page
                </li>
            </ol>  -->
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/jquery.fileDownload.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-slider.js"></script>
<script src="js/handlebars-v2.0.0.js"></script>
<!-- script references -->
<script src="js/statics.js"></script>
<script src="js/util.js"></script>
<script src="js/rest.js"></script>
<script src="js/sidemenu.js"></script>
<script src="js/state.js"></script>
<script src="js/missionbuilder.js"></script>
<script src="js/coordTranslator.js"></script>
<script src="js/renderer.js"></script>
<script src="js/maprenderer.js"></script>

</body>
</html>







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
                    <input id="create-mission-gen-bridge-aaa" class="checkbox" type="checkbox" value="false">
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
                    <!--
                    <select id="create-unit-group-altitude" class="form-control">
                        <option value="500">500 m</option>
                        <option value="1000">1000 m</option>
                        <option value="1500" selected>1500 m</option>
                        <option value="2000">2000 m</option>
                        <option value="3000">3000 m</option>
                        <option value="4000">4000 m</option>
                        <option value="5000">5000 m</option>
                        <option value="7000">7000 m</option>
                        <option value="9000">9000 m</option>
                        <option value="0">Ground</option>

                    </select>
                    -->
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
                <h4 class="modal-title">Create Airplane Group</h4>
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



<!-- OBJECT PROPERTIES DIALOG -->
<div id="object-properties" class="hidden">

        <h4 id="obj-edit-title">Object properties</h4>

        <div class="form-group">
            <label>Skill level</label>
            <select id="create-ground-unit-group-skillXXXX" class="form-control">
                <option value="4">Ace</option>
                <option value="3">Veteran</option>
                <option value="2">Regular</option>
                <option value="1">Rookie</option>
                <option value="0">Player</option>
            </select>
        </div>

        <div class="form-group">
            <label>Skill level</label>
            <select id="create-ground-unit-group-skillXXXXx" class="form-control">
                <option value="4">Ace</option>
                <option value="3">Veteran</option>
                <option value="2">Regular</option>
                <option value="1">Rookie</option>
                <option value="0">Player</option>
            </select>
        </div>
    <div class="text-center">
    <button class="btn btn-danger"><i class="fa fa-remove"></i> Delete</button>
    </div>

</div>



<div class="modalspinner"><!-- Place at bottom of page --></div>

<div id="clickOnMapDiv" class="modal-dialog hidden" style="position:absolute;">
    Select location on map
</div>

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
    <h4>Edit {{name}}</h4>
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
    <div class="propertyelement">
        <label>Formation</label>
        <select  class="form-control" id="planes-edit-group-formation">
        </select>
    </div>
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

    <div class="propertyelement">
        <label>Description</label>
        <textarea id="ground-group-edit-group-description" class="form-control">{{description}}</textarea>
    </div>
    <div class="text-center propertyelement">
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
        <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete static object group</button>
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
        <button class="btn btn-info" id="edit-action"><i class="fa fa-exclamation"></i> Edit action</button> <button class="btn btn-danger" id="delete"><i class="fa fa-remove"></i> Delete</button>
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
        <input id="edit-mission-gen-bridge-aaa" class="checkbox" type="checkbox" value="true">
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

<script>
    $body = $("body");
    $(document).keydown(function(e){
        if( e.which === 90 && e.ctrlKey ){
            missionbuilder.undo();
        }
    });
    $(document).keyup(function(e) {
        if (e.keyCode == 27) { // esc keycode
            state.setState(state.NORMAL);
            $('#clickOnMapDiv').addClass('hidden');
            $("#map").css("cursor", "");
            maprenderer.redraw();
        }
    });
    util.initHandlebarHelpers();
    statics.loadAirfields();
    missionbuilder.initMap();

</script>


