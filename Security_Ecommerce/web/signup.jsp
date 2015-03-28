<%-- 
    Document   : signup
    Created on : Dec 9, 2014, 11:52:18 PM
    Author     : jonathan
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>Medical Messaging System</title>
        
<!--The following javascript code has been adapted from www.the-art-of-web.com/javascript/validate-password/-->

<script type="text/javascript">

  function checkForm(form)
  {
    if(form.email.value == "") {
      alert("Error: Email cannot be blank!");
      form.email.focus();
      return false;
    }
    
    // The following regular expression is from http://www.zparacha.com/validate-email-address-using-javascript-regular-expression/
    re = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if(!re.test(form.email.value)) {
      alert("Error: Email must contain domain and '@' symbol");
      form.email.focus();
      return false;
    }

    if(form.pass1.value != "" && form.pass1.value == form.pass2.value) {
      if(form.pass1.value.length < 6) {
        alert("Error: Password must contain at least six characters!");
        form.pass1.focus();
        return false;
      }
      if(form.pass1.value == form.email.value) {
        alert("Error: Password must be different from Username!");
        form.pass1.focus();
        return false;
      }
      re = /[0-9]/;
      if(!re.test(form.pass1.value)) {
        alert("Error: password must contain at least one number (0-9)!");
        form.pass1.focus();
        return false;
      }
      re = /[a-z]/;
      if(!re.test(form.pass1.value)) {
        alert("Error: password must contain at least one lowercase letter (a-z)!");
        form.pass1.focus();
        return false;
      }
      re = /[A-Z]/;
      if(!re.test(form.pass1.value)) {
        alert("Error: password must contain at least one uppercase letter (A-Z)!");
        form.pass1.focus();
        return false;
      }
    } else {
      alert("Error: Please check that you've entered and confirmed your password!");
      form.pass1.focus();
      return false;
    }
    
    return true;
  }

</script>

    </head>

    <body>
      
        
<div id='signupcontent'>
<center>
        <h2>Patient Sign-Up</h2>
        <br/>
        <form action="CreateUser" method="POST" onsubmit="return checkForm(this);">
        <input type="text" name="email" placeholder="Email">
        <br/><br/> 
        <input type="password" name="pass1" placeholder="Password">
        <br/><br/>
        <input type="password" name="pass2" placeholder="Confirm Password">
        <br/><br/>
        <input type="text" name="fname" placeholder="First Name"/>
        <br/><br/>
        <input type="text" name="lname"placeholder="Last Name"/>
        <br/>
        <br/>
        <input type="submit" id="signupButton" value="Sign Up">
        <br/>
        <br/>
</form>
</center>
</div>
<br/>   
</div>
           </div>
    </body>
</html>