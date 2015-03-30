<%-- 
    Document   : loginReminder
    Created on : Mar 29, 2015, 4:04:31 PM
    Author     : hab81
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login First</title>
    </head>
    <body>
        <div class="div_system_title">
            <b>Please Login first.</b>
        </div>
        <form action="login" method="post">
            <input type="submit" name="backToLogin" value="Back to Login" class="submit_backlog"/>   
        </form>
    </body>
</html>
