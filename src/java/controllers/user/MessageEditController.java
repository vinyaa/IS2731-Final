/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.user.Message;
import models.user.MessageManager;
import models.user.User;
import models.user.UserManager;
import models.user.UserRole;

/**
 *
 * @author yanma
 */
public class MessageEditController extends HttpServlet {

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
        RequestDispatcher requestDispatcher;
        //validate session first
        HttpSession session = request.getSession(false);
        if(session.getAttribute("sesssionCode") == null) {
            requestDispatcher = request.getRequestDispatcher("/loginReminder.jsp");    
            requestDispatcher.forward(request, response);
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
        RequestDispatcher requestDispatcher;
        MessageManager messageManager = new MessageManager();
        UserManager userManager = new UserManager();
        
        String errorMessage = null;
        String actionEditMessage = request.getParameter("editMessage");
        String actionBack = request.getParameter("Back");
        
        String content = request.getParameter("messageContent"); 
        HttpSession session = request.getSession();
        String sender = session.getAttribute("userName").toString();
        
        int mid = Integer.valueOf(request.getParameter("mid"));
        
        if(actionEditMessage !=null && actionEditMessage.equals("Confirm Edit")) {
            messageManager.updateMessageContent(mid, content);
            request.setAttribute("sender", sender);
            List<Message> allMessageList = messageManager.listAllMessage(sender);
            request.setAttribute("allMessageList", allMessageList);
            requestDispatcher = request.getRequestDispatcher("/admin/listMessages.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(actionBack != null && actionBack.equals("Back")) {
            request.setAttribute("sender", sender);
            List<Message> allMessageList = messageManager.listAllMessage(sender);
            request.setAttribute("allMessageList", allMessageList);
            requestDispatcher = request.getRequestDispatcher("/admin/listMessages.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            requestDispatcher = request.getRequestDispatcher("/error404.jsp");
            requestDispatcher.forward(request, response);
        }
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
