<%-- 
    Document   : messages
    Author     : jonathan
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<div>
    <div class="column-center"><h2>Your Messages</h2>
        <br/>
        View private messages.
        <br/>
        <br/>
        <%
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/final_project";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM MESSAGES M, USERS U WHERE M.RECIPIENT='" + session.getAttribute("email")+ "' AND M.AUTHOR=U.USERNAME ORDER BY DATE DESC";
            ResultSet rs = stm.executeQuery(q1);
            
            out.println("<br/>");
           
            try{
                
                while(rs.next()){    
   
                 out.println("<div id='boardBox'><b>" 
                         + rs.getString("fname") + " " + rs.getString("lname") + "</b> - " + rs.getString("date") + "<br/><br/>"
                         + rs.getString("message") + "<br/><br/>" +
                         "<form action='sendmessage' method='POST'><input type='hidden' name='recip' value='" + rs.getString("username") 
                         + "'><input type='hidden' name='author' value='"+ rs.getString("fname") + " " + rs.getString("lname")
                         + "'><input type='submit' value='Write Back'></form>"
                         + "<form action='DeleteMessage' method='POST'>"
                         + "<input type='hidden' name='mid' value=" + rs.getString("mid") + "><input type='submit' value='Delete'></form>"
                         + "</div><br/>");
                }
                out.println("");
            }
            catch(SQLException sqle){
            out.println("There was a problem");  
            }
            stm.close();
            con.close();
        }
        catch (Exception se){
            out.println("Connection failed! ");
        }  
        %>
        <br/>
        
