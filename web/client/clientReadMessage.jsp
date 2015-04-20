<%-- 
    Document   : clientReadMessage
    Created on : Apr 7, 2015, 8:02:45 PM
    Author     : yanma
--%>

<%@page import="models.user.Message"%>
<%@page import="models.user.MessageManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/utilities.js"></script>
        <script type="text/javascript" src="js/cryptojs/rollups/aes.js"></script>
        <script type="text/javascript" src="js/messageutils.js"></script>
        <script type="text/javascript" src="js/jsencrypt.js"></script>
        <link rel="stylesheet" href="css/client_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Read Message</title>
    </head>
    <body>
        <% 
            int mid = Integer.valueOf(request.getAttribute("mid").toString());
            MessageManager messageManager = new MessageManager();
            Message message = messageManager.findMessage(mid);
            String disabled = "";
            if (message.getIs_read() == 1) {
                disabled = "disabled";
            }
        %>
        
        <%  if (message != null) { %>
            <div class="div_system_title">
                <h1><b>Message Details</b></h1>
            </div>
            
            <!-- This div area to ask user to enter his passcode. -->
            <fieldset id="div_login_fieldset">
                <legend id="div_login_legend">Please enter your passcode</legend>
            </fieldset>
            <div id="div_login_passcode">
                <form action="login" method="post"  class="form-horizontal" >
                    <div class="form-group">
                        <div class="col-sm-10" style="width: 100%">
                            <label for="inputPassword3" class="col-sm-2 control-label">Passcode</label>
                            <input type="password" id="passcode" name="passcode" class="form-control" style="width:35%; display: inline-block; margin-right: 10px;"/>
                            <input type="hidden" name="userName" value="<%=message.getReceiver()%>" />
                            <input type="button" name="passDecrypt" class="btn btn-default" value="Confirm Passcode" style="display: inline-block"
                                   onclick="decryptMessage()"/>
                        </div>
                    </div>
                </form>
            </div>
            
            <div id="div_current_message">
                <form action="ClientMessage" method="post" class="form-horizontal" >
                    <div class="form-group">
                        <label class="col-sm-2 control-label">From: </label>
                        <div class="col-sm-10">
                            <input type="text" name="sender" value="<%=message.getSender()%>" class="form-control" readonly />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">To: </label>
                        <div class="col-sm-10">
                            <input type="text" name="receiver" value="<%=message.getReceiver()%>" class="form-control" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Sent Time </label>
                        <div class="col-sm-10">
                            <input type="text" name="receiver" value="<%=message.getTime()%>" class="form-control" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Message Content</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" id="message" name="messageContent" readonly><%=message.getContent()  %></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="hidden" name="mid" value="<%=message.getMid() %>" />
                            <input type="submit" name="markRead" value="Mark As Read" class="btn btn-success" <%=disabled%>/>
                            <input type="submit" name="back" value="Back" class="btn btn-default"/>
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
