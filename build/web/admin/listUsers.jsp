<%-- 
    Document   : listUser
    Created on : Mar 29, 2015, 6:01:20 PM
    Author     : hab81
--%>

<%@page import="models.user.UserRole"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>  
<%@page import="models.user.UserManager" %>
<%@page import="models.user.User" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/admin_style.css" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users List</title>
    </head>
    <body>
        <% if(request.getAttribute("errorMessage") != null) { %>
        <div class="error_message"><p><b><%=request.getAttribute("errorMessage")%></b></p></div>   
        <% }      
        else if(request.getAttribute("allUsersCount") != null) { 
            if(request.getAttribute("activationMessage") != null) { %>
                <div class="activation_message"><p><b><%=request.getAttribute("activationMessage")%></b></p></div>   
            <% }
            if(request.getAttribute("register_message") != null) { %>
                <div class="register_message"><p><b><%=request.getAttribute("register_message")%></b></p></div>   
            <% } %>
            <div class="div_system_title">
                <% int allUsersCount = (Integer)request.getAttribute("allUsersCount"); %>
                <h1><b>Hello Admin: </b></h1>
                <h3>You have <%=allUsersCount%> clients.</h3>
            </div>
            <div id="div_users_list">
                <table id="table_userslist" class="table table-hover">
                    <tr>  
                        <th>User Name</th>
                        <th>User Email</th>
                        <th>User Rank</th>
                        <th>User Status</th>
                        <th colspan="3">Operations</th>
                    </tr>  
                    <%  
                        List<User> allUsersList = (List<User>)request.getAttribute("allUsersList");     
                        List<UserRole> allUserRoleList = (List<UserRole>)request.getAttribute("allUserRoleList");
                        if(allUsersList.size() > 0) {  
                            for(User user : allUsersList) {
                                String roles = "";
                                String disabled = "";
                                String notRemove = "";
                                for(UserRole item : allUserRoleList) {
                                    if(item.getUserName().equals(user.getUserName()))
                                        roles += item.getRoleName() + " | ";
                                }
                    %>  
                                <tr>
                                    <td><%=user.getUserName()%></td>  
                                    <td><%=user.getEmail()%></td>
                                    <td><%=roles%></td>
                                    <% if(user.getIsActivated() == 0 || roles.equals("admin | ")) { 
                                        disabled = "disabled";%>
                                        <td id="td_not_activated">Not Activated</td> 
                                    <% } 
                                    else if(user.getIsActivated() == 1) {%>
                                        <td id="td_activated">Activated</td> 
                                    <% } 
                                        if(roles.equals("admin | ")) {
                                            notRemove = "disabled";
                                        }
                                    %>
                                    <td style="padding-left: 5px; padding-right: 5px;">  
                                        <form action="UserList" method="post">
                                            <input type="hidden" name="userName" value="<%=user.getUserName()%>"/>
                                            <input type="submit" name="editUser" value="Edit User" class="btn btn-xs btn-default" />  
                                        </form> 
                                    </td>  
                                    <td style="padding-left: 5px; padding-right: 5px;">
                                        <form action="UserList" method="post">
                                            <input type="hidden" name="userName" value="<%=user.getUserName()%>"/>
                                            <input type="submit" name="removeUser" value="Remove User" class="btn btn-xs btn-danger" <%=notRemove %>/>     
                                        </form>
                                    </td> 
                                    <td style="padding-left: 5px; padding-right: 5px;">
                                        <form action="UserList" method="post">
                                            <input type="hidden" name="userName" value="<%=user.getUserName()%>"/>
                                            <input type="submit" name="createMessage" value="Send Message" class="btn btn-xs btn-info" <%=disabled%>/>     
                                        </form>
                                    </td> 
                                </tr>  
                    <%  
                            }//end of for
                        }//end of if  
                    %>  
                </table>  
            </div>
            
            <div id="two-buttons">
                <form action="UserList" method="post" >
                    <input type="submit" name="addUser" value="Add User" class="btn btn-primary"/>
                    <input type="submit" name="listMessage" value="List Messages" class="btn btn-info"/>
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
