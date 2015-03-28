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
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "Approve", urlPatterns = {"/Approve"})
public class Approve extends HttpServlet {

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
        String username = request.getParameter("username");
        String approve = request.getParameter("approve");
        
        if(approve.equals("yes")){
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/final_project";
                Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                Statement stm = con.createStatement();
                
                String q1;
                q1 = "UPDATE IS2560.USERS SET STATUS='APPROVED' WHERE USERNAME='"+ username + "'";
                stm.executeUpdate(q1);
                stm.close();
                con.close();
                
                RequestDispatcher rd = request.getRequestDispatcher("/approvepatient");
                rd.forward(request, response);
            }
            catch (Exception se){
                out.println("Connection failed ! ");
            }
        }
        else{
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/final_project";
                Connection con = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                Statement stm = con.createStatement();
                
                String q1;
                q1 = "DELETE FROM IS2560.USERS WHERE USERNAME='"+ username + "'";
                stm.executeUpdate(q1);
                stm.close();
                con.close();
                
                RequestDispatcher rd = request.getRequestDispatcher("/approvepatient");
                rd.forward(request, response);
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
