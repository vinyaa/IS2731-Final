<%-- 
    Document   : login
    Created on : Mar 25, 2014, 8:09:15 PM
    Author     : hab81
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please Login</title>
        
    </head>
    <body>
        <div class="div_system_title">
            <h1><b>User Management System</b></h1>
        </div>
        <!-- This div area displays the user login information. -->
        <fieldset id="div_login_fieldset">
            <legend id="div_login_legend">Please input user information</legend>
        </fieldset>
        <div id="div_login">

            <form action="login" method="post"  class="form-horizontal" >
                <div class="form-group">
                    <label class="col-sm-2 control-label">User Name</label>
                    <div class="col-sm-10">
                        <input type="text" name="userName" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" name="login" value="Sign In" class="btn btn-default" />
                        <input type="submit" name="register" class="btn btn-default" value="Register" />
                        <input type="reset" value="clear" class="btn btn-default"/>
                    </div>
                </div>
            </form>

        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
