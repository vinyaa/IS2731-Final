<%-- 
    Author     : jonathan
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<div>
    <div class="column-center"><h2>Admin </h2>
        
        
        <!-- The following will be replaced with the admin page -->
        
        <%
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/final_project";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM MAINTENANCE M, USERS U WHERE M.STATUS = 'open' AND U.USERNAME=M.USERNAME ORDER BY DATE DESC";
            ResultSet rs = stm.executeQuery(q1);
            
            out.println("<br/>");
           
            try{
                
                while(rs.next()){    
   
                 out.println("<div id='boardBox'><b>Request ID: </b>" 
                         + rs.getString("requestID") 
                         + "<br/> <b> Priority: </b> " + rs.getString("priority")
                         + "<br/><b>Date: </b>" + rs.getString("date")
                         
                         + "<br/><b>From: </b>" + rs.getString("fname") + " " + rs.getString("lname")
                         + "<br/><b>Type of Issue: </b>" + rs.getString("type")
                         
                         + "<br/><br/><b>Description:</b><br/> " + rs.getString("description") + "<br/><br/>"
                         + "<form name='markdone' action='Markdone' method='POST'><input type='hidden' name='requestID' value='" 
                         + rs.getString("requestID") + "'><input type='submit' value='Mark as complete'></form></div><br/> "
                 );
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
     