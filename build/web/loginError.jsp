<%-- 
    Document   : error
    Created on : Mar 28, 2015, 8:09:52 PM
    Author     : hab81
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Failed</title>
    </head>
    <body>
        <div class="div_system_title">
            <h1><b style="color: red">Login failed, please try again.</b></h1>
        </div>
        
        <form action="login" method="post" class="form-horizontal">
            <div class="div_login_eror">
                <input type="submit" name="backToLogin" value="Back to Login" class="btn btn-default"/>  
            </div>
        </form>
        
    </body>
</html>
