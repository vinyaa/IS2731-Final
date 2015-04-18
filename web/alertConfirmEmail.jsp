<%-- 
    Document   : alertConfirmEmail
    Created on : Apr 18, 2015, 3:05:19 PM
    Author     : yanma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please confirm email</title>
    </head>
    <body>
        <div class="div_system_title">
            <h1><b style="color: red">Please confirm your email first.</b></h1>
        </div>
        <form action="login" method="post" class="form-horizontal">
            <div class="div_login_eror">
                <input type="submit" name="backToLogin" value="Back to Login" class="btn btn-default"/>  
            </div>
        </form>
    </body>
</html>
