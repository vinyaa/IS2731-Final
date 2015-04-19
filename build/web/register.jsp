<%-- 
    Document   : register
    Created on : Apr 6, 2014, 8:09:15 AM
    Author     : hab81
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New User</title>
    </head>
    <body>
        <div class="div_system_title">
            <h1><b>Register New User</b></h1>
        </div>
        <!-- This div area displays the user login information. -->
        
        <fieldset id="div_login_fieldset">
            <legend id="div_login_legend">Please input user information</legend>
        </fieldset>
        <div id="div_login">
            <form action="register" method="post"  class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">User Name</label>
                    <div class="col-sm-10">
                        <input type="text" name="userName" class="form-control"  id="text_user_name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" id="password_password"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Confirm Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="passwordConfirm" class="form-control" id="password_password_confirm"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">E-mail</label>
                    <div class="col-sm-10">
                        <input type="text" name="emailAddress" class="form-control" id="text_email_address"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Security Answer</label>
                    <div class="col-sm-10">
                        <input type="text" name="userAnswer" class="form-control"  id="text_answer"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" name="register" value="Register" class="btn btn-default"/>
                        <input type="reset" value="Clear" class="btn btn-default"/>
                        <a href="login.jsp" ><input type="button" value="Back" class="btn btn-default" /></a>
                        
                    </div>
                </div>
            </form>

        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
