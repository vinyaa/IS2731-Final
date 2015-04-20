package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.email.EmailNotifier;
import models.encryption.KeysManager;
import models.randomizer.Randomizer;
import models.user.SessionManager;
import models.user.User;
import models.user.UserManager;
import models.user.UserRole;
import models.user.UserValidator;

/**
 *
 * @author
 */
@WebServlet(name = "UserRegisterController", urlPatterns = {"/register"})
public class UserRegisterController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>No Such Page</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>This is not a valid URL, please get back to valid pages.</h1>");
            out.println("</body>");
            out.println("</html>");
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
        RequestDispatcher requestDispatcher;
        UserManager userManager = new UserManager();
        
        String action = request.getParameter("action"); 
        String client = request.getParameter("client");
        String token = request.getParameter("token");
        //validate activation token
        HttpSession session = request.getSession(false);
        String sessionToken = session.getAttribute("sessionToken").toString();
        if(sessionToken != null && sessionToken.equals(token)) {
            //activate user
            userManager.changeActivation(client, 1);
            String activationMessage = client + " has been succesfully activated!";
            request.setAttribute("activationMessage", activationMessage);
        }   
        else {
            String errorMessage = "Failed to activate " + client + "!";
            request.setAttribute("errorMessage", errorMessage);
            requestDispatcher = request.getRequestDispatcher("/login.jsp");    
            requestDispatcher.forward(request, response);
        }
        //initializing keys
        PublicKey publicKey;
        PrivateKey privateKey;

        //generate public and private keys
        KeyPair keyPair = KeysManager.generateKeyPairs();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        byte[] publicBytes = publicKey.getEncoded();
        byte[] privateBytes = privateKey.getEncoded();

        String mypublic = javax.xml.bind.DatatypeConverter.printBase64Binary(publicBytes);
        String myprivate = javax.xml.bind.DatatypeConverter.printBase64Binary(privateBytes);
        request.setAttribute("mypublic", mypublic);
        request.setAttribute("myprivate", myprivate);
            
        List<User> allUsersList = userManager.listAllUsers();
        List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
        int allUsersCount = userManager.getUsersCount();
        request.setAttribute("client", client);
        request.setAttribute("allUsersList", allUsersList);
        request.setAttribute("allUserRoleList", allUserRoleList);
        request.setAttribute("allUsersCount", allUsersCount);
        requestDispatcher = request.getRequestDispatcher("/client/clientPasscodeEncrypt.jsp");    
        requestDispatcher.forward(request, response);
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
        String registerMessage = null;
        String errorMessage = null;
        
        String actionRegister = request.getParameter("register");
        
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String userEmail = request.getParameter("emailAddress");
        String userAnswer = request.getParameter("userAnswer");
        
        if(actionRegister !=null && actionRegister.equals("Register")) {
            if(userName.length() > 0 && password.length() > 0 &&
                password.equals(passwordConfirm) && userEmail!= null && userAnswer != null) {
                //validate the format of userName and password
                if(userValidator.validateUserName(userName) == true &&
                    userValidator.validateUserPassword(password) == true) {
                    userManager.addUser(userName, password, userEmail, userAnswer, "client");
                    userManager.changeActivation(userName, 0);
                    
                    //generate session token for confirming registration
                    HttpSession session = request.getSession();
                    String sessionToken = Randomizer.getRandomToken();
                    session.setAttribute("sessionToken",sessionToken); 
                    //token will be expired after 60 mins
                    session.setMaxInactiveInterval(60 * 60);
                    //send this token as URL to user's email address
                    URL url = new URL("https://localhost:8443/is2731_final/register?"
                                    + "client="+userName+"&token="+sessionToken+""
                                    + "&action=Activate");
                    EmailNotifier emailNotifier = new EmailNotifier();
                    emailNotifier.sendMail(userEmail, "Activate Your Account", url.toString());
                    
                    registerMessage = "A confirmation Email has been sent to user's email address. "
                                    + "Please click the link in the email to finish registration.";
                    //generate user session and cookie
                    session = request.getSession();
                    String sesionCode = SessionManager.generateSessionCode(userName);
                    session.setAttribute("sesssionCode",sesionCode); 
                    //session will be expired after 30 mins
                    session.setMaxInactiveInterval(60 * 30);
                    Cookie userCookie = new Cookie("userCookie", userName);
                    //cookie will be expired after 30 mins
                    userCookie.setMaxAge(60 * 30);
                    response.addCookie(userCookie);
                }
                else {
                    errorMessage = "User name should be no more than 20 characters, "
                                        + "consisting of letters in upper/lower case and "
                                        + "numbers. Password should be no more than 20 characters.";
                }
            }
//            List<User> allUsersList = userManager.listAllUsers();
//            List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
//            int allUsersCount = userManager.getUsersCount();
//            request.setAttribute("allUsersList", allUsersList);
//            request.setAttribute("allUserRoleList", allUserRoleList);
//            request.setAttribute("allUsersCount", allUsersCount);
//            request.setAttribute("registerMessage", registerMessage);
//            request.setAttribute("errorMessage", errorMessage);
//            requestDispatcher = request.getRequestDispatcher("/admin/listUsers.jsp");
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
