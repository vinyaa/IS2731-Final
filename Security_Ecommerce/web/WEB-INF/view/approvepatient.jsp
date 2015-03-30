<%-- 

    Author     : jonathan
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<div>
    <div class="column-center"><h2></h2>
        <%
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/final_project";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM USERS WHERE STATUS='PENDING'";
            ResultSet rs = stm.executeQuery(q1);
            
            try{
                
                    while(rs.next()){    

                         out.println("<br/><div id='boardBox'>"
                                 + "<br/><b>Name: </b>" + rs.getString("fname") + " " + rs.getString("lname")
                                 + "<br/><b>Email: </b> " + rs.getString("email")
                                 + "<br/><form name='approve' action='Approve' method='POST'>"
                                 + "<br/><input type='hidden' name='username' value='" + rs.getString("username") + "'>"
                                 + "<input type='hidden' name='approve' value='yes'>"
                                 + "<input type='submit' value='Approve'></form>"
                                 + "<form name='approve' action='Approve' method='POST'>"
                                 + "<input type='hidden' name='username' value='" + rs.getString("username") + "'>"
                                 + "<input type='hidden' name='approve' value='no'>"
                                 + "<input type='submit' value='Reject'></form>"
                                 + "<br/></div><br/> "
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
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/final_project";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM USERS WHERE STATUS='APPROVED'";
            ResultSet rs = stm.executeQuery(q1);
            
            try{
                
                    while(rs.next()){    

                         out.println("<br/><div id='boardBox'>"
                                 + "<br/><b>Name: </b>" + rs.getString("fname") + " " + rs.getString("lname")
                                 + "<br/><b>Email: </b> " + rs.getString("email")
                                 + "<br/><form name='approve' action='Approve' method='POST'>"
                                 + "<br/><input type='hidden' name='username' value='" + rs.getString("username") + "'>"
                                 + "<input type='hidden' name='approve' value='yes'>"
                                 + "<input type='submit' value='Approve'></form>"
                                 + "<form name='approve' action='Approve' method='POST'>"
                                 + "<input type='hidden' name='username' value='" + rs.getString("username") + "'>"
                                 + "<input type='hidden' name='approve' value='no'>"
                                 + "<input type='submit' value='Reject'></form>"
                                 + "<br/></div><br/> "
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
     
