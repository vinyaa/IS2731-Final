<%-- 
    Author     : jonathan
--%>

<div>
    <div class="column-center"><h3>Send a message:</h3>
        <br/>
        <form action="SubmitMessage" method="POST">
        <input name='recip' type='hidden' value='<%= request.getParameter("physician")%>'>
        <input type='text' name="messagebody" size='83'>
        <br/>
        <br/>
        <input type="submit" value='Send'>
        </form>
   