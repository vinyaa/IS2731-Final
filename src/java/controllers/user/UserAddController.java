package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.email.EmailNotifier;
import models.randomizer.Randomizer;
import models.user.User;
import models.user.UserManager;
import models.user.UserRole;
import models.user.UserValidator;

/**
 *
 * @author
 */
@WebServlet(name = "UserAddController", urlPatterns = {"/UserAdd"})
public class UserAddController extends HttpServlet {

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
        UserValidator userValidator = new UserValidator();
        String errorMessage = null;
        String registerMessage = null;
        
        String actionAddUser = request.getParameter("addUser");
        String actionBack = request.getParameter("Back");
        
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String userAnswer = request.getParameter("userAnswer");
        String userRank = request.getParameter("userRank");
        
        if(actionAddUser !=null && actionAddUser.equals("Add")) {
            if(userName.length() > 0 && password.length() > 0 &&
               password.equals(passwordConfirm) && userRank != null) {
                //validate the format of userName and password
                if(userValidator.validateUserName(userName) == true &&
                   userValidator.validateUserPassword(password) == true) {
                    userManager.addUser(userName, password, userEmail, userAnswer, userRank);
                    //generate session token for confirming registration
                    HttpSession session = request.getSession();
                    String sessionToken = Randomizer.getRandomToken();
                    session.setAttribute("sessionToken",sessionToken); 
                    //token will be expired after 60 mins
                    session.setMaxInactiveInterval(60 * 60);
                    //send this token as URL to user's email address
                    URL url = new URL("http://localhost:8084/is2731_final/register?"
                                    + "client="+userName+"&token="+sessionToken+""
                                    + "&action=Activate");
                    EmailNotifier emailNotifier = new EmailNotifier();
                    emailNotifier.sendMail(userEmail, "Activate Your Account", url.toString());
                    
                    registerMessage = "A confirmation Email has been sent to user's email address. "
                                    + "Please click the link in the email to finish registration.";
                }
                else {
                    errorMessage = "User name should be no more than 20 characters, "
                                        + "consisting of letters in upper/lower case and "
                                        + "numbers. Password should be no more than 20 characters.";
                }
            }
            List<User> allUsersList = userManager.listAllUsers();
            List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
            int allUsersCount = userManager.getUsersCount();
            request.setAttribute("allUsersList", allUsersList);
            request.setAttribute("allUserRoleList", allUserRoleList);
            request.setAttribute("allUsersCount", allUsersCount);
            request.setAttribute("registerMessage", registerMessage);
            request.setAttribute("errorMessage", errorMessage);
            requestDispatcher = request.getRequestDispatcher("/admin/listUsers.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(actionBack != null && actionBack.equals("Back")) {
            List<User> allUsersList = userManager.listAllUsers();
            List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
            int allUsersCount = userManager.getUsersCount();
            request.setAttribute("allUsersList", allUsersList);
            request.setAttribute("allUserRoleList", allUserRoleList);
            request.setAttribute("allUsersCount", allUsersCount);
            requestDispatcher = request.getRequestDispatcher("/admin/listUsers.jsp");
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
