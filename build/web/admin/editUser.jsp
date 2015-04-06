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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <% User currentUser = (User)request.getAttribute("currentUser"); 
           String oldUserName = currentUser.getUserName();
        %>
        <% if(currentUser != null) { %>
            <div class="div_system_title">
<!--                <b>Edit the user</b>-->
                <h1><b>Edit the user</b></h1>
            </div>
            <!-- This div area is for editing user's information. -->
<!--            <div id="div_current_user">-->
            <fieldset id="div_current_user_fieldset">
                <legend id="div_current_user_legend">Please edit current user's information</legend>
            </fieldset>
            <div id="div_current_user">

                <form action="UserEdit" method="post" class="form-horizontal" >
<!--                        <table cellpadding="4">
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
                    </table>-->

                    <div class="form-group">
                        <label class="col-sm-2 control-label">User Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="userName" value="<%=currentUser.getUserName()%>" class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email Address</label>
                        <div class="col-sm-10">
                            <input type="email" name="userEmail" value="<%=currentUser.getEmail()%>"  class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Confirm Password</label>
                        <div class="col-sm-10">
                            <input type="password" name="passwordConfirm" class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Security Answer</label>
                        <div class="col-sm-10">
                            <input type="text" name="userAnswer" class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">User's Role</label>
                        <div class="col-sm-10">
                            <select name="userRank" class="form-control">
                                    <option value="admin" >Admin</option>
                                    <option value="client" selected>Client</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="hidden" name="oldUserName" value="<%=oldUserName%>"/>
                            <input type="submit" name="editUser" value="Edit" class="btn btn-default"/>
                            <input type="reset" value="Clear" class="btn btn-default"/>
                            <input type="submit" name="Back" value="Back" class="btn btn-default"/>
                        </div>
                    </div>
                </form>
                <!--</fieldset>-->
            </div>
        <% } 
        else{ %>
            <div class="div_system_title">
                <h1><b>There is no valid user to edit!</b></h1>
            </div>
        <% } %>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
