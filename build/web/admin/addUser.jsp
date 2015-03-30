<%-- 
    Document   : addUser
    Created on : Mar 29, 2015, 6:01:20 PM
    Author     : hab81
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
    </head>
    <body>
        <div class="div_system_title">
            <b>Add a new user</b>
        </div>
        <!-- This div area is for inputing new user's information. -->
        <div id="div_new_user">
            <fieldset id="div_new_user_fieldset">
                <legend id="div_new_user_legend">Please input new user's information</legend>
                <form action="UserAdd" method="post">
                    <table cellpadding="4">
                        <tr>
                            <td align="right">User Name</td>
                            <td align="left"><input type="text" name="userName" id="text_user_name"/></td>
                        </tr>
                        <tr>
                            <td align="right">Email Address</td>
                            <td align="left"><input type="text" name="userEmail" id="text_user_email"/></td>
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
                            <td align="right">Security Answer</td>
                            <td align="left"><input type="text" name="userAnswer" id="text_user_answer"/></td>
                        </tr>
                        <tr>
                            <td align="right">User's Role</td>
                            <td align="left">
                                <select name="userRank" id="select_user_rank">
                                    <option value="admin">Admin</option>
                                    <option value="client" selected>Client</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" name="addUser" value="Add" class="submit_add_user"/>
                    <input id="id_button_clear" type="button" value="clear" onclick="clearUserText()"/>
                    <input type="submit" name="Back" value="Back" class="submit_add_user"/>
                </form>
            </fieldset>
        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
