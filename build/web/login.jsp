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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please Login</title>
    </head>
    <body>
        <div class="div_system_title">
            <b>User Management System</b>
        </div>
        <!-- This div area displays the user login information. -->
        <div id="div_login">
            <fieldset id="div_login_fieldset">
                <legend id="div_login_legend">Please input user information</legend>
                <form action="login" method="post">
                    <table cellpadding="4">
                        <tr>
                            <td align="right">User Name</td>
                            <td align="left"><input type="text" name="userName" id="text_user_name"/></td>
                        </tr>
                        <tr>
                            <td align="right">Password</td>
                            <td align="left"><input type="password" name="password" id="password_password"/></td>
                        </tr>
                    </table>
                    <input type="submit" name="login" value="Sumbit" class="submit_login"/>
                    <input id="id_button_clear" type="button" value="clear" onclick="clearLoginText()"/>
                </form>
                <form action="login" method="post">
                    <input type="submit" name="register" value="Register" style="width: 60px"/>
                </form>
            </fieldset>
        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
