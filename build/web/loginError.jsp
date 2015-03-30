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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Failed</title>
    </head>
    <body>
        <div class="div_system_title">
            <b>Login failed, please try again.</b>
        </div>
        <form action="login" method="post">
            <input type="submit" name="backToLogin" value="Back to Login" class="submit_backlog"/>   
        </form>
    </body>
</html>
