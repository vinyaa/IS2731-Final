<%-- 
    Document   : editMessage
    Created on : Apr 6, 2015, 6:17:21 PM
    Author     : yanma
--%>

<%@page import="models.user.Message"%>
<%@page import="models.user.MessageManager"%>
<%@page import="models.user.UserDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Message</title>
    </head>
    <body>
        <%  int mid = Integer.valueOf(request.getAttribute("mid").toString());  
            MessageManager messageManager = new MessageManager();
            Message message = messageManager.findMessage(mid);
            
        %>
        
        <%  if (message != null) { %>
            <div class="div_system_title">
                <h1><b>Edit message</b></h1>
            </div>
            <fieldset id="div_current_user_fieldset">
                <legend id="div_current_user_legend">Please edit the message content</legend>
            </fieldset>
            <div id="div_current_user">
                <form action="MessageEdit" method="post" class="form-horizontal" >
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Sender Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="sender" value="<%=message.getSender()%>" class="form-control" readonly />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Receiver Name</label>
                        <div class="col-sm-10">
                            <input type="text" name="receiver" value="<%=message.getReceiver()%>" class="form-control" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Message Content</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" name="messageContent" ><%=message.getContent()  %></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="hidden" name="mid" value="<%=message.getMid() %>" />
                            <input type="submit" name="Back" value="Back" class="btn btn-default"/>
                        </div>
                    </div>
                </form>
            </div>
        <% }
        else { %>
            <div class="div_system_title">
                <h1><b>There is no valid message to edit!</b></h1>
            </div>
        <% }
        %>
    </body>
</html>
