<%-- 
    Document   : Main page
    Created on : Mar 29, 2015, 8:09:43 PM
    Author     : hab81
--%>

<%@page import="models.user.Message"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Client's Home Page</title>
    </head>
    <body>
        <% 
            String receiver = request.getAttribute("client").toString();
            String passcode = "";
            if(request.getAttribute("passcode") != null) {
                passcode = request.getAttribute("passcode").toString();
            }
        %>
        <% if (receiver != null) { %>
            <div class="div_system_title" onload="unlockKey()">
                <h1><b>List of messages you received:</b></h1>
            </div>
            
            <div id="div_messages_list">
                <table id="table_messagelist" class="table table-hover">
                    <tr>  
                        <th style="width: 5%">#</th>
                        <th style="width: 10%">Sender</th>
                        <th style="width: 15%">Time</th>
                        <th>Content</th>
                        <th>Status</th>
                        <th colspan="2">Operations</th>
                    </tr>  
                    <%  
                        List<Message> receiverMessageList = (List<Message>)request.getAttribute("receiverMessageList");     
                        if(receiverMessageList.size() > 0) {  
                            int id = 1;
                            for(Message message : receiverMessageList) {
                                String status = "";
                                String color = "";
                                String content = message.getContent();
                                String disabled = "";
                                
                                if (message.getIs_read() == 0) {
                                    status = "Unread";
                                } else {
                                    status = "Read";
                                    disabled = "disabled";                                   
                                }
                                
                                if (status.equals("Unread")) {  
                                    color = "red";
                                }
                                
                                if (content.length() > 50) {
                                    content = "Encrypted Message ******";
                                }
                   
                    %>  
                                <tr>
                                    <td style="width: 5%"><%=id%></td>
                                    <td style="width: 10%"><%=message.getSender()%></td>  
                                    <td style="width: 15%"><%=message.getTime()%></td>
                                    <td style="width: 40%; word-break: break-all;text-align: left;"><%=content%></td>  
                                    <td style="width: 10%; color: <%=color %>" ><%=status%></td>
                                    <td style="padding-left: 15px; padding-right: 15px; width: 10%;">  
                                        <form action="ClientMessage" method="post">
                                            <input type="hidden" name="mid" value="<%=message.getMid()%>"/>
                                            <input type="submit" name="showMessage" value="Show Message" class="btn btn-xs btn-info" />  
                                        </form> 
                                    </td>  
                                    <td style="padding-left: 5px; padding-right: 5px; width: 10%;">
                                        <form action="ClientMessage" method="post">
                                            <input type="hidden" name="mid" value="<%=message.getMid()%>"/>
                                            <input type="submit" name="markRead" value="Mark As Read" class="btn btn-xs btn-success" <%=disabled %>/>     
                                        </form>
                                    </td> 
                                </tr>  
                    <%          id++;
                            }//end of for
                        }//end of if  
                    %>  
                </table>  
            </div>
            
            <div id="two-buttons">
                <form action="UserList" method="post" >
                    
                    <input type="submit" name="logOut" value="Log Out" class="btn btn-warning"/>
                </form>
            </div>
        <% } 
        else { %>
            <div class="div_system_title">
                <p><b>There is no valid users to list!</b></p>
            </div>
        <% } %>
    </body>
</html>
