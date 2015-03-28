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
@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

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
        String newUser = request.getParameter("email");
        String newPass1 = request.getParameter("pass1");
        String newPass2 = request.getParameter("pass2");
        String newFname = request.getParameter("fname");
        String newLname = request.getParameter("lname");

   
        if(newUser == "" || newPass1 == ""){
            out.print("<script language='Javascript'>alert('Username or password were not filled out.')</script>");
            RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
            rd.include(request, response);
        }
        else {
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/final_project";
                Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                Statement stm = con.createStatement();
                String q1 = "SELECT * FROM USERS WHERE USERNAME = '" + newUser +"'";
                ResultSet rs = stm.executeQuery(q1);
                
                if(rs.next()){ 
                    try{
                      out.print("<script language='Javascript'>alert('That email is already registered. Please try again.')</script>");
                      RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
                      rd.include(request, response);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else{

                    String q2;
                    q2 = "INSERT INTO IS2560.USERS (USERNAME, PASSWORD, FNAME, LNAME, STATUS) VALUES ('" + 
                       newUser + "', '" + newPass1 + "', '" + newFname + "', '" + newLname + "', '" + "', 'PENDING')";
                    stm.executeUpdate(q2);
                    stm.close();
                    con.close();
                    out.print("<script language='Javascript'>alert('Success!  Now, an admin must validate your account.')</script>");
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
