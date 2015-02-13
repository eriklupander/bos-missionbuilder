<!DOCTYPE html>
<html>
<jsp:include page="include.jsp"/>
<head>
    <title>BoS Web Mission builder - Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Add some nice styling and functionality.  We'll just use Twitter Bootstrap        -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
    <style>
        body{padding-top:20px;background-color: #333;}
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Create BoS Web Mission Builder account</h3>
                </div>
                <div class="panel-body">

                    <p>Please note that this service is currently in alpha development. We do NOT currently support emailing
                    of lost logins/passwords etc. If you lose your account details, you're out of luck...
                    </p>
                    <form name="registerform" action="/reg" method="POST" accept-charset="UTF-8" role="form">
                        <fieldset>
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input id="username" class="form-control" placeholder="Username" name="username" type="text">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input id="password" class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <div class="form-group">
                                <label for="password2">Re-type password</label>
                                <input id="password2" class="form-control" placeholder="Re-type your password" name="password2" type="password2" value="">
                            </div>


                            <div class="form-group">
                                <label for="cylon">Not-a-cylon-check</label>
                                <p><i>What is three plus three divided by two?</i> </p>
                                <input id="cylon" class="form-control" placeholder="Answer here if you are not a Cylon" name="cylon" type="text" value="">
                            </div>

                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Create Account">
                        </fieldset>
                    </form>
                    <div>Already registered? <a href="/login.jsp">Click here</a> to log in.</div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
</body>
</html>