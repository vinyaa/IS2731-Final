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
<!--            <b>User Management System</b>-->
            <h1><b>User Management System</b></h1>
        </div>
        <!-- This div area displays the user login information. -->
        <!--<div id="div_login">-->
        <fieldset id="div_login_fieldset">
            <legend id="div_login_legend">Please input user information</legend>
        </fieldset>
        <div id="div_login">
            <form action="login" method="post"  class="form-horizontal" >
                <div class="form-group">
                    <label class="col-sm-2 control-label">User Name</label>
                    <div class="col-sm-10">
<!--                            <input type="text" name="userName" id="text_user_name"/>-->
                        <input type="text" name="userName" class="form-control" />
                    </div>
<!--                    <table cellpadding="4">
                    <tr>
                        <td align="right">User Name</td>
                        <td align="left"><input type="text" name="userName" id="text_user_name"/></td>
                    </tr>
                    <tr>
                        <td align="right">Password</td>
                        <td align="left"><input type="password" name="password" id="password_password"/></td>
                    </tr>
                </table>-->
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
<!--                            <input type="password" name="password" id="password_password" />-->
                        <input type="password" name="password" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <!--<input type="submit" name="login" value="Sumbit" class="submit_login"/>-->
                        <input type="submit" name="login" value="Sign In" class="btn btn-default" />
<!--                            <input id="id_button_clear" type="button" value="clear" onclick="clearLoginText()"/>-->
                        <!--<input type="reset" value="clear" onclick="clearLoginText()" class="btn btn-default"/>-->
                        <input type="reset" value="clear" class="btn btn-default"/>
                    </div>
                </div>
            </form>
            <form action="login" method="post">
                    <input type="submit" name="register" value="Register" style="width: 60px"/>
            </form>
        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
