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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login First</title>
    </head>
    <body>
        <div class="div_system_title">
            <h1><b style="color: red">Please Login first.</b></h1>
        </div>
        <form action="login" method="post" class="form-horizontal">
            <div class="div_login_eror">
                <input type="submit" name="backToLogin" value="Back to Login" class="btn btn-default"/>  
            </div>
        </form>
    </body>
</html>
