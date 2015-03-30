<%-- 
    Document   : editUser
    Created on : Mar 29, 2015, 6:01:20 PM
    Author     : hab81
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.user.User"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <% User currentUser = (User)request.getAttribute("currentUser"); 
           String oldUserName = currentUser.getUserName();
        %>
        <% if(currentUser != null) { %>
            <div class="div_system_title">
                <b>Edit the user</b>
            </div>
            <!-- This div area is for editing user's information. -->
            <div id="div_current_user">
                <fieldset id="div_current_user_fieldset">
                    <legend id="div_current_user_legend">Please edit current user's information</legend>
                    <form action="UserEdit" method="post">
                        <table cellpadding="4">
                            <tr>
                                <td align="right">User Name</td>
                                <td align="left"><input type="text" name="userName" value="<%=currentUser.getUserName()%>" id="text_user_name"/></td>
                            </tr>
                            <tr>
                                <td align="right">Email Address</td>
                                <td align="left"><input type="text" name="userEmail" value="<%=currentUser.getEmail()%>" id="text_user_email"/></td>
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
                                        <option value="admin" >Admin</option>
                                        <option value="client" selected>Client</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <input type="hidden" name="oldUserName" value="<%=oldUserName%>"/>
                        <input type="submit" name="editUser" value="Edit" class="submit_edit_user"/>
                        <input id="id_button_clear" type="button" value="clear" onclick="clearUserText()"/>
                        <input type="submit" name="Back" value="Back" class="submit_edit_user"/>
                    </form>
                </fieldset>
            </div>
        <% } 
        else{ %>
            <div class="div_system_title">
                <p><b>There is no valid user to edit!</b></p>
            </div>
        <% } %>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
