package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.user.User;
import models.user.UserManager;
import models.user.UserRole;

/**
 *
 * @author
 */
@WebServlet(name = "UserListController", urlPatterns = {"/UserList"})
public class UserListController extends HttpServlet {

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
        UserManager userManager = new UserManager();
        User currentUser;
        
        String actionAddUser = request.getParameter("addUser");
        String actionEditUser = request.getParameter("editUser");
        String actionRemoveUser = request.getParameter("removeUser");
        String actionLogOut = request.getParameter("logOut");
        
        if(actionAddUser !=null && actionAddUser.equals("Add User")) {
            requestDispatcher = request.getRequestDispatcher("/admin/addUser.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(actionEditUser != null && actionEditUser.equals("Edit")) {
            String userName = request.getParameter("userName");
            if(userName != null){
                currentUser = userManager.findUser(userName);
                request.setAttribute("currentUser", currentUser);
                requestDispatcher = request.getRequestDispatcher("/admin/editUser.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        else if(actionRemoveUser != null && actionRemoveUser.equals("Remove")) {
            String userName = request.getParameter("userName");
            if(userName != null){
                currentUser = userManager.findUser(userName);
                userManager.deleteUser(currentUser, null);

                List<User> allUsersList = userManager.listAllUsers();
                List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
                int allUsersCount = userManager.getUsersCount();
                request.setAttribute("allUsersList", allUsersList);
                request.setAttribute("allUserRoleList", allUserRoleList);
                request.setAttribute("allUsersCount", allUsersCount);
                requestDispatcher = request.getRequestDispatcher("/user/listUsers.jsp");
                requestDispatcher.forward(request, response);
            }   
        }
        else if(actionLogOut != null && actionLogOut.equals("Log Out")) {
            HttpSession session=request.getSession(false);  
            session.invalidate();
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
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
