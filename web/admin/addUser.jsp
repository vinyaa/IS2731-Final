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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
    </head>
    <body>
        <div class="div_system_title">
            <h1><b>Add a new user</b></h1>
        </div>
        <!-- This div area is for inputing new user's information. -->
        <fieldset id="div_new_user_fieldset">
            <legend id="div_new_user_legend">Please input new user's information</legend>
        </fieldset>
        <div id="div_new_user">
            
                <form action="UserAdd" method="post" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">User Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="userName" class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email Address</label>
                        <div class="col-sm-10">
                            <input type="email" name="userEmail" class="form-control"  />
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
                                    <!--<option value="admin" >Admin</option>-->
                                    <option value="client" selected>Client</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" name="addUser" value="Add" class="btn btn-default"/>
                            <input type="reset" value="Clear" class="btn btn-default"/>
                            <input type="submit" name="Back" value="Back" class="btn btn-default"/>
                        </div>
                    </div>
                </form>
        </div>
        <script type="text/javascript" src="js/adminScript.js"></script>
    </body>
</html>
