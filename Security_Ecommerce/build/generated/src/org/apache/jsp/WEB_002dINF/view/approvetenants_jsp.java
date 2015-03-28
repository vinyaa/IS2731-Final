package org.apache.jsp.WEB_002dINF.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public final class approvetenants_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/jspf/header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/footer.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");


   
  if(session.getAttribute("username")==null){  
       try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
   }

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"mycss.css\">\n");
      out.write("        <title>Medical Messaging System</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <div id=\"headerContent\">\n");
      out.write("                    <div class=\"left\"></div>\n");
      out.write("                    <div class=\"right\">\n");
      out.write("                        <div id=\"headerInfo\">\n");
      out.write("                       <h3>Hello, ");
      out.print( session.getAttribute("username") );
      out.write("\n");
      out.write("                            &nbsp; | &nbsp;  <form name=\"logout\" action=\"Logout\" method=\"POST\"><a href=\"JAVASCRIPT:logout.submit()\">Logout</a></form>                                                                \n");
      out.write("                           </h3></div> </div>\n");
      out.write("</div></h3>\n");
      out.write("                    \n");
      out.write("            </div>\n");
      out.write("        <div id=\"content\">\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <div class=\"column-center\"><h2></h2>\n");
      out.write("        ");

        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/Apartmentfriend";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM USERS WHERE STATUS='PENDING'";
            ResultSet rs = stm.executeQuery(q1);
            
            try{
                
                    while(rs.next()){    

                         out.println("<br/><div id='boardBox'>"
                                 + "<b>Apartment: </b> " + rs.getString("apartment")
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
        
      out.write("\n");
      out.write("        <br/>\n");
      out.write("     \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</div>  \n");
      out.write("    <div class=\"column-left\">\n");
      out.write("        <div class=\"contentLside\">\n");
      out.write("            <a href=\"profile\">Profile</a><br/>\n");
      out.write("            <a href=\"messages\">Messages</a><br/>\n");
      out.write("            <a href=\"directory\">Neighbors</a><br/>\n");
      out.write("            <a href=\"community\">Community Board</a><br/>\n");
      out.write("            <a href=\"maintenance\">Maintenance Request</a><br/>\n");

        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/Apartmentfriend";
            Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
            Statement stm = con.createStatement();
            String q1 = "SELECT * FROM BUILDING B WHERE USERNAME='" + session.getAttribute("email") + "'";
            ResultSet rs = stm.executeQuery(q1);
           
            try{
                
                while(rs.next()){    
                    out.println("<br/><b>Administration:</b><br/>");
                    out.println("<a href='admin'>View Requests</a><br/>");
                    out.println("<a href='approvetenants'>Approve Tenants</a><br/>");
                    }
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

      out.write("\n");
      out.write("           \n");
      out.write("        </div>    \n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
