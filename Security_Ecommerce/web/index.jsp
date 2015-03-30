<%-- any content can be specified here e.g.: --%>
<%

   
  if(session.getAttribute("username")!=null){  
       try {
                request.getRequestDispatcher("/community.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
   }
%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>Medical Messaging System</title>
    </head>
    <body>
        <div id="signupcontent">

<br/>
<br/>
<br/>
<br/>
<center>
    <h2>Welcome to Medical Messaging System</h2>
    <br/>
    <br/>
    
        Sign In:
            <form name="User Login" action="ValidateUser" class="center" method="POST">
            <br/>
            <br/>
            <input type="text" name="name" placeholder="Email"/>
            <br/>
            <br/>
            <input type="password" name="pass" placeholder="Password"/>
            <br/>
            <br/>
            <input type="submit" id="signupButton" value="Sign In" />
            </form>
        <br/>
        <br/>
        <br/>
        <a href="signup.jsp">Don't have an account? Sign up <u>here</u>.</a>
        <br/>
        <br/>
</center>
</div>
<br/>

    </body>
</html>