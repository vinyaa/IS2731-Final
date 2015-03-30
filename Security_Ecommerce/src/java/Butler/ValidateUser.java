/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Butler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "ValidateUser", urlPatterns = {"/ValidateUser"})
public class ValidateUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
   
        if(name == "" || pass == ""){
            out.print("<script language='Javascript'>alert('Username or password were not filled out. Please try again.')</script>");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
        }
        else{
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/Apartmentfriend";
                Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                Statement stm = con.createStatement();
                String q1 = "SELECT * FROM USERS WHERE USERNAME = '" + name + "' AND PASSWORD = '" + pass + "' AND STATUS ='APPROVED'";
                
                ResultSet rs = stm.executeQuery(q1);
                
                if(rs.next()){
                
                    HttpSession session = request.getSession(true);
                    String username = "" + rs.getString(3) + " " + rs.getString(4);
                    String email = rs.getString(1);
                    String building = rs.getString("building");
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    session.setAttribute("building", building);
                    
                    try{
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/messages.jsp");
                        rd.forward(request, response);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else{
                    out.print("<script language='Javascript'>alert('Invalid username, password, or your account must be approved by a manager.')</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.include(request, response);
                }
            }
            catch (Exception se){
                out.println("Connection failed ! ");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
