package models.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hab81
 * This class manages users.
 */
public class UserManager {
    
    private final UserDBManager userDBManager;
        
    public UserManager() {
        this.userDBManager = new UserDBManager();
    }
    
    /*
    * Add a new user
    */
    public boolean addUser(String userName, String password, String email, String answer, String roleName) {
        //make sure no same user name
        if (!this.userDBManager.queryAllUsers().stream().noneMatch((user) -> (user.getUserName().equals(userName)))) {
            return false;
        }
        try {
            User user = new User();
            UserRole userRole = new UserRole();
            String hashedPassword = UserManager.encryptText(password);
            String hashedAnswer = UserManager.encryptText(answer);
            user.setUserName(userName);
            user.setHashedPassword(hashedPassword);
            user.setEmail(email);
            user.setHashedAnswer(hashedAnswer);
            user.setIsActivated(0); //not activated by default
            userRole.setUserName(userName);
            userRole.setRoleName(roleName);
            this.userDBManager.addUser(user);
            this.userDBManager.addUserRole(userName, roleName);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /**
     * add user public key
     */
    public boolean addPublickKey(String userName, String publicKey) {
        if (userName.equals("") || userName == null) {
            return false;
        }
        try {
            this.userDBManager.addPublickKey(userName, publicKey);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }    
    
    /**
     * find public key
     * if return empty string, the user is not activated, show alert message, 
     * if return non-empty string, the user is activated and has public key
     * @param receiverName
     * @return 
     */
    public String findPublicKey(String receiverName) {
        if (receiverName.equals("") || receiverName == null) {
            return "";
        }
        try {
            return this.userDBManager.findPublicKey(receiverName);
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
            return "";
        }
    }
    
    
    
    /*
    * update the user
    */
    public boolean updateUser(User currentUser) {
        if (currentUser == null) {
            return false;
        }
        try {
            this.userDBManager.updateUser(currentUser, null);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the user name
    */
    public boolean changeName(String oldName, String newName) {
        //make sure no same user name
        if (!this.userDBManager.queryAllUsers().stream().noneMatch((user) -> (user.getUserName().equals(newName)))) {
            return false;
        }
        try {
            User user = this.userDBManager.queryUser(oldName);  
            user.setUserName(newName);
            this.userDBManager.updateUser(user, oldName);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the password of the user
    */
    public boolean changePassword(String userName, String newPassword) {
        try {
            User user = this.userDBManager.queryUser(userName);
            String hashedPassword = UserManager.encryptText(newPassword);        
            user.setHashedPassword(hashedPassword);
            this.userDBManager.updateUser(user, null);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the email of the user
    */
    public boolean changeEmail(String userName, String newEmail) {
        try {
            User user = this.userDBManager.queryUser(userName);  
            user.setEmail(newEmail);
            this.userDBManager.updateUser(user, null);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the security answer of the user
    */
    public boolean changeAnswer(String userName, String newAnswer) {
        try {
            User user = this.userDBManager.queryUser(userName);  
            String hashedAnswer = UserManager.encryptText(newAnswer);
            user.setHashedAnswer(hashedAnswer);
            this.userDBManager.updateUser(user, null);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the activation status of the user
    */
    public boolean changeActivation(String userName, int isActivated) {
        try {
            User user = this.userDBManager.queryUser(userName);  
            user.setIsActivated(isActivated);
            this.userDBManager.updateUser(user, null);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Change the userName/roleName pair
    */
    public boolean changeUserRole(String oldUserName, String oldRoleName, String newUserName, String newRoleName) {
        try {
            this.userDBManager.updateUserRole(oldUserName, oldRoleName, newUserName, newRoleName);
            return true;
        }
        catch(Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
    
    /*
    * Delete a user and its role pair
    */
    public boolean deleteUser(User currentUser, String currentRoleName) {
        //numbers of all users cannot be zero
        if(this.userDBManager.queryAllUsers().isEmpty() == true) {
            return false;
        }
        //numbers of admin cannot be zero
        for(UserRole tempItem : this.userDBManager.queryAllUsersRoles()) {
            if(tempItem.getRoleName().equals("admin") && !tempItem.getUserName().equals(currentUser.getUserName())) {
                return (this.userDBManager.deleteUserRole(currentUser.getUserName(), currentRoleName) && 
                        this.userDBManager.deleteUser(currentUser));
            }
        }
        return false;       
    }
    
    /*
    * authenticate a user
    */
    public boolean authenticateUser(String userName, String password) {
        return this.userDBManager.queryUserWithPassword(userName, password) != null;
    }
    
    /*
    * find a user
    */
    public User findUser(String userName) {
        return this.userDBManager.queryUser(userName);
    }
            
    /*
    * List all users
    */
    public List<User> listAllUsers() {
        return this.userDBManager.queryAllUsers();
    }
    /*
    * List all users/roles pairs
    */
    public List<UserRole> listAllUsersRoles() {
        return this.userDBManager.queryAllUsersRoles();
    }
    
    /*
    * Get the number of all users
    */
    public int getUsersCount() {
        return this.userDBManager.queryAllUsers().size();
    }
    
    /*
    * Use SHA-256 aogorithm to generate hashed string of a plain text (password or security answer).
    */
    public static String encryptText(String plainText) {
        String hashString = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(plainText.getBytes("UTF-8"));
            
            BigInteger bigInt = new BigInteger(1,hashedBytes);
            hashString = bigInt.toString(16);    
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashString;
    }
    
    /*
    * close database connection
    */
    public boolean closeConnection() { 
        return this.userDBManager.closeConnection();
    }
}
