package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
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
import models.encryption.KeysManager;
import models.user.Message;
import models.user.MessageManager;
import models.user.SessionManager;
import models.user.User;
import models.user.UserManager;
import models.user.UserRole;

/**
 *
 * @author hab81
 */
@WebServlet(name = "UserLoginController", urlPatterns = {"/login"})
public class UserLoginController extends HttpServlet {

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
        String actionLogin = request.getParameter("login");
        String actionRegister = request.getParameter("register");
        String actionBack = request.getParameter("backToLogin");
        String actionPasscode = request.getParameter("submitPass");
        String actionPasscodeDecrypt = request.getParameter("passDecrypt");
        
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String mypublic = request.getParameter("mypublic");
        String passcode = request.getParameter("passcode");
        String confirmPasscode = request.getParameter("confirm_passcode");

        RequestDispatcher requestDispatcher;
        UserManager userManager = new UserManager();
        if(actionLogin != null) {
            boolean isLegalUser = userManager.authenticateUser(userName, password);
            if(isLegalUser == true) {
                HttpSession session = request.getSession();
                String sesionCode = SessionManager.generateSessionCode(userName);
                session.setAttribute("userName", userName);
                session.setAttribute("sesssionCode",sesionCode); 
                //session will be expired after 30 mins
                session.setMaxInactiveInterval(60 * 30);
                Cookie userCookie = new Cookie("userCookie", userName);
                //cookie will be expired after 30 mins
                userCookie.setMaxAge(60 * 30);
                response.addCookie(userCookie);
                List<UserRole> userRoleList = userManager.listAllUsersRoles();
                String roles = "";
                for(UserRole item : userRoleList) {
                    if(item.getUserName().equals(userName))
                        roles += item.getRoleName() + ":";
                }
                if(roles.contains("admin")) {
                    List<User> allUsersList = userManager.listAllUsers();
                    List<UserRole> allUserRoleList = userManager.listAllUsersRoles();
                    int allUsersCount = userManager.getUsersCount();
                    request.setAttribute("allUsersList", allUsersList);
                    request.setAttribute("allUserRoleList", allUserRoleList);
                    request.setAttribute("allUsersCount", allUsersCount);
                    requestDispatcher = request.getRequestDispatcher("/admin/listUsers.jsp");
                }
                else {
                    request.setAttribute("client", userName);

                    requestDispatcher = request.getRequestDispatcher("/client/clientPasscodeEncrypt.jsp");
                    String publicKey = userManager.findPublicKey(userName);
                    if (publicKey.equals("")) {
                        requestDispatcher = request.getRequestDispatcher("/alertConfirmEmail.jsp");  
                    } else {
                        request.setAttribute("client", userName);
                        MessageManager messageManager = new MessageManager();
                        List<Message> messageList = messageManager.queryAllMessagesForReceiver(userName);
                        request.setAttribute("receiverMessageList", messageList);
                        requestDispatcher = request.getRequestDispatcher("/client/clientMessage.jsp");  
                    }
                }   
            } 
            else {
                requestDispatcher = request.getRequestDispatcher("/loginError.jsp");      
            }
        }
        else if(actionRegister != null) {
            requestDispatcher = request.getRequestDispatcher("/register.jsp");
        }
        else if(actionBack != null) {
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
        }

        else if (actionPasscode != null && actionPasscode.equals("Confirm Passcode")) {
            if(passcode == null) {
                //initializing keys
                PublicKey publicKey;
                PrivateKey privateKey;

                //generate public and private keys
                KeyPair keyPair = KeysManager.generateKeyPairs();
                publicKey = keyPair.getPublic();
                privateKey = keyPair.getPrivate();

                byte[] publicBytes = publicKey.getEncoded();
                byte[] privateBytes = privateKey.getEncoded();

                String mypublickey = javax.xml.bind.DatatypeConverter.printBase64Binary(publicBytes);
                String myprivate = javax.xml.bind.DatatypeConverter.printBase64Binary(privateBytes);
                request.setAttribute("mypublic", mypublickey);
                request.setAttribute("myprivate", myprivate);
                request.setAttribute("client", userName);
                requestDispatcher = request.getRequestDispatcher("/client/clientPasscodeEncrypt.jsp");  
            }
            /**
             * decrypt messages
             */
            request.setAttribute("client", userName);
            //store the public key in database
            userManager.addPublickKey(userName, mypublic);
            MessageManager messageManager = new MessageManager();
            List<Message> messageList = messageManager.queryAllMessagesForReceiver(userName);
            request.setAttribute("receiverMessageList", messageList);
            requestDispatcher = request.getRequestDispatcher("/client/clientMessage.jsp");  
        }
        else if (actionPasscodeDecrypt != null && actionPasscodeDecrypt.equals("Confirm Passcode")) {
            /******
             * add decrypt message functions
             */
            request.setAttribute("client", userName);
            MessageManager messageManager = new MessageManager();
            List<Message> messageList = messageManager.queryAllMessagesForReceiver(userName);
            request.setAttribute("receiverMessageList", messageList);
            requestDispatcher = request.getRequestDispatcher("/client/clientMessage.jsp");  
        }
        
        else {
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
        }
        requestDispatcher.forward(request, response);
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
