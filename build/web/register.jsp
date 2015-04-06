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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New User</title>
    </head>
    <body>
        <div class="div_system_title">
            <b>Register New User</b>
        </div>
        <!-- This div area displays the user login information. -->
        <div id="div_login">
            <fieldset id="div_login_fieldset">
                <legend id="div_login_legend">Please input user information</legend>
                <form action="register" method="post">
                    <table cellpadding="4">
                        <tr>
                            <td align="right">User Name</td>
                            <td align="left"><input type="text" name="userName" id="text_user_name"/></td>
                        </tr>
                        <tr>
                            <td align="right">Password</td>
                            <td align="left"><input type="password" name="password" id="password_password"/></td>
                        </tr>
                        <tr>
                            <td align="right">Confirm Password</td>
                            <td align="left"><input type="password" name="passwordConfirm" id="password_password_confirm"/></td>
                        </tr>
                        <tr>
                            <td align="right">E-mail</td>
                            <td align="left"><input type="text" name="emailAddress" id="text_email_address"/></td>
                        </tr>
                        <tr>
                            <td align="right">Security Answer</td>
                            <td align="left"><input type="text" name="userAnswer" id="text_answer"/></td>
                        </tr>
                    </table>
                    <input type="submit" name="register" value="Register" class="submit_register"/>
                    <input id="id_button_clear" type="button" value="clear" onclick="clearRegisterText()"/>
                </form>
            </fieldset>
        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
