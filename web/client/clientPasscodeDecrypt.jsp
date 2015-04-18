<%-- 
    Document   : clientPasscodeDecrypt
    Created on : Apr 18, 2015, 3:12:06 PM
    Author     : yanma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/client_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <title>Decrypt your private key</title>
    </head>
    <body>
        <% String username = request.getAttribute("client").toString();%>
        <div class="div_system_title">
            <h1><b>Passcode</b></h1>
        </div>
        <!-- This div area to ask user to enter his passcode. -->
        <fieldset id="div_login_fieldset">
            <legend id="div_login_legend">Please enter your passcode</legend>
        </fieldset>
        <div id="div_login_passcode">
            <form action="login" method="post"  class="form-horizontal" >
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="inputPassword3" class="col-sm-2 control-label">Passcode</label>
                        <input type="password" name="passcode" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="hidden" name="userName" value="<%=username %>" />
                        <input type="submit" name="passDecrypt" class="btn btn-default" value="Confirm Passcode" />
                        <input type="reset" value="Clear" class="btn btn-default"/>
                        <a href="login.jsp" ><input type="button" value="Back" class="btn btn-default" /></a>
                    </div>
                </div>
                
            </form>
        </div>
        
        
    </body>
</html>
