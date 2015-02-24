<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
        >

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
                    <!--
                    <li id="missionFileLink" class="hidden">

                    </li>
                    <li id="localizationFileLink" class="hidden">

                    </li>
                    -->
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
                        <a href="#" onclick="missionbuilder.toggleMapFilter('both_sides');"><i id="both_sides_check" class="fa fa-check hidden"></i> Show both sides</a>
                    </li>
                    <li>
                        <a href="#" onclick="missionbuilder.toggleMapFilter('plane_group');"><i id="plane_group_check" class="fa fa-check"></i> Planes Groups</a>
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

            <li class="dropdown hidden" id="weather-select">
                <a href="#"  class="dropdown-toggle"  onclick="weathereditor.openWeatherEditor();"><i class="fa fa-cloud"></i>  Weather Editor</a>
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
                    <!--  COMMENT IN AGAIN ONCE WE ENABLE PROPER SECURITY FOR A WEB-BASED INSTALL
                    <li>
                        <a href="/logout.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>

                    </li>
                    -->
                    <li>
                        <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out (disabled)</a>

                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul id="rightmenu" class="nav navbar-nav side-nav hidden">
                <li>
                    <a href="#"  title="Click to edit basic data" onclick="missionbuilder.editMission();"> <h5 class="text-center" id="mission-name"></h5><div class="text-center" style="font-size:8pt;">Click to edit mission</div></a>
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
                <!--
                <li id="menu-add-trigger-zone" class="menuitem">
                    <a href="#" onclick="missionbuilder.addTriggerZone();"><i class="fa fa-fw fa-exclamation"></i> Add trigger zone</a>
                </li>
                -->
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
<script src="js/weathereditor.js"></script>
<script src="js/missionbuilder.js"></script>
<script src="js/coordTranslator.js"></script>
<script src="js/renderer.js"></script>
<script src="js/maprenderer.js"></script>

</body>
</html>







<jsp:include page="templates/modals.jsp"/>



<!-- OBJECT PROPERTIES DIALOG -->
<div id="object-properties" class="hidden">
        <center>
        <h4 id="obj-edit-title" class="text-center">Object properties</h4>
        </center>
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

<div id="clickOnMapDiv" class="modal-dialog hidden" style="position:absolute;font-size:14pt;z-index:1400;color:#555;">
    Select location on map
</div>

<jsp:include page="templates/forms.jsp"/>
<jsp:include page="templates/weathereditor.jsp"/>

<script>
    $body = $("body");

    document.ondblclick = function(evt) {
        if (window.getSelection)
            window.getSelection().removeAllRanges();
        else if (document.selection)
            document.selection.empty();
    }

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
    state.setState(state.NORMAL);
    util.initHandlebarHelpers();
    statics.loadAirfields();
    missionbuilder.initMap();

    (function($) {
        $.fn.drags = function(opt) {

            opt = $.extend({handle:"",cursor:"move"}, opt);

            if(opt.handle === "") {
                var $el = this;
            } else {
                var $el = this.find(opt.handle);
            }

            return $el.css('cursor', opt.cursor).on("mousedown", function(e) {
                if(opt.handle === "") {
                    var $drag = $(this).parent().addClass('draggable');
                } else {
                    var $drag = $(this).parent().addClass('active-handle').parent().addClass('draggable');
                }
                var z_idx = $drag.css('z-index'),
                        drg_h = $drag.outerHeight(),
                        drg_w = $drag.outerWidth(),
                        pos_y = $drag.offset().top + drg_h - e.pageY,
                        pos_x = $drag.offset().left + drg_w - e.pageX;
                $drag.css('z-index', 1000).parents().on("mousemove", function(e) {
                    $('.draggable').offset({
                        top:e.pageY + pos_y - drg_h,
                        left:e.pageX + pos_x - drg_w
                    }).on("mouseup", function() {
                                $(this).removeClass('draggable').css('z-index', z_idx);
                            });
                });
                e.preventDefault(); // disable selection
            }).on("mouseup", function() {
                        if(opt.handle === "") {
                            $(this).removeClass('draggable');
                        } else {
                            $(this).removeClass('active-handle').parent().removeClass('draggable');
                        }
                    });

        }
    })(jQuery);

</script>


