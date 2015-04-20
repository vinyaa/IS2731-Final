package models.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hab81
 * This class establishes connection with
 * database, and operates data in tables. 
 */
public class UserDBManager {

    private final String databaseURL;
    private final String dbUserName;
    private final String dbPassword;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public UserDBManager() {
        databaseURL = "jdbc:mysql://localhost:8889/is2731";
        dbUserName = "is2731";
        dbPassword = "is2731";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(databaseURL, dbUserName, dbPassword);   
        }
        catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }		
    }
	
    /*
    * Query if user is stored in database with only userName. 
    */
    public User queryUser(String userName) {
    	User userResult = null;
        String sql = "SELECT * FROM users WHERE user_name = ?";
	try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName);
            this.resultSet = this.statement.executeQuery();

            if(this.resultSet.next()){
                userResult = new User();              
		userResult.setUserName(this.resultSet.getString("user_name"));
                userResult.setHashedPassword(this.resultSet.getString("hashed_password"));
		userResult.setEmail(this.resultSet.getString("email"));
                userResult.setHashedAnswer(this.resultSet.getString("hashed_answer"));
                userResult.setIsActivated(this.resultSet.getInt("is_activated"));
                userResult.setPublicKey(this.resultSet.getString("pubkey"));
            }
            statement.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	return userResult;
    }
    
    /*
    * Query if user/role pair is stored in database with only userName. 
    */
    public List<UserRole> queryUserRole(String userName) {
        List<UserRole> userRoleList = new ArrayList<>();
    	UserRole userRoleResult;
        String sql = "SELECT * FROM users_roles WHERE user_name = ?";
	try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName);
            this.resultSet = this.statement.executeQuery();

            while(this.resultSet.next()){
                userRoleResult = new UserRole();              
		userRoleResult.setUserName(this.resultSet.getString("user_name"));
                userRoleResult.setRoleName(this.resultSet.getString("role_name"));
                userRoleList.add(userRoleResult);
            }
            statement.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	return userRoleList;
    }
    
    /*
    * Query if user is stored in database with userName and password. 
    */
    public User queryUserWithPassword(String userName, String password) {
    	User userResult = null;
        String sql = "SELECT * FROM users WHERE user_name = ? AND hashed_password = ?";
        String hashedPassword = UserManager.encryptText(password);
	try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName);
            this.statement.setString(2, hashedPassword);
            this.resultSet = this.statement.executeQuery();

            if(this.resultSet.next()){
                userResult = new User();
                userResult.setUserName(this.resultSet.getString("user_name"));
                userResult.setHashedPassword(this.resultSet.getString("hashed_password"));
		userResult.setEmail(this.resultSet.getString("email"));
                userResult.setHashedAnswer(this.resultSet.getString("hashed_answer"));
                userResult.setIsActivated(this.resultSet.getInt("is_activated"));
                userResult.setPublicKey(this.resultSet.getString("pubkey"));
            }
            this.statement.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	return userResult;
    }
    
    /*
    * Query if user is stored in database with userName and hashed answer. 
    */
    public User queryUserWithAnswer(String userName, String securityAnswer) {
    	User userResult = null;
        String sql = "SELECT * FROM users WHERE user_name = ? AND hashed_answer = ?";
        String hashedAnswer = UserManager.encryptText(securityAnswer);
	try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName);
            this.statement.setString(2, hashedAnswer);
            this.resultSet = this.statement.executeQuery();

            if(this.resultSet.next()){
                userResult = new User();
                userResult.setUserName(this.resultSet.getString("user_name"));
                userResult.setHashedPassword(this.resultSet.getString("hashed_password"));
		userResult.setEmail(this.resultSet.getString("email"));
                userResult.setHashedAnswer(this.resultSet.getString("hashed_answer"));
                userResult.setIsActivated(this.resultSet.getInt("is_activated"));
                userResult.setPublicKey(this.resultSet.getString("pubkey"));
            }
            this.statement.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
	return userResult;
    }
	
    /*
    * Query all users in database.
    */
    public List<User> queryAllUsers() {
    	List<User> userList = new ArrayList<>();
	User user;
        String sql = "SELECT * FROM users";
	try {	
            this.statement = connection.prepareStatement(sql);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
		user = new User();
                user.setUserName(this.resultSet.getString("user_name"));
                user.setHashedPassword(this.resultSet.getString("hashed_password"));
		user.setEmail(this.resultSet.getString("email"));
                user.setHashedAnswer(this.resultSet.getString("hashed_answer"));
                user.setIsActivated(this.resultSet.getInt("is_activated"));
                user.setPublicKey(this.resultSet.getString("pubkey"));
		userList.add(user);
            }	
            this.statement.close();
	} 
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
    
    /*
    * Query all users/roles in database.
    */
    public List<UserRole> queryAllUsersRoles() {
    	List<UserRole> userRoleList = new ArrayList<>();
	UserRole userRole;
        String sql = "SELECT * FROM users_roles";
	try {	
            this.statement = connection.prepareStatement(sql);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
		userRole = new UserRole();
                userRole.setUserName(this.resultSet.getString("user_name"));
                userRole.setRoleName(this.resultSet.getString("role_name"));
		userRoleList.add(userRole);
            }	
            this.statement.close();
	} 
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userRoleList;
    }
	
    /*
    * Add new user
    */
    public boolean addUser(User newUser) {
        boolean addResult = false;
	String sql = "INSERT INTO users"
                    + "(user_name, hashed_password, email, hashed_answer, is_activated, pubkey) VALUES"
                    + "(? , ? , ? , ? , ?, ?)";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, newUser.getUserName());
            this.statement.setString(2, newUser.getHashedPassword());
            this.statement.setString(3, newUser.getEmail());    
            this.statement.setString(4, newUser.getHashedAnswer());    
            this.statement.setInt(5, newUser.getIsActivated()); 
            this.statement.setString(6, "");
            this.statement.executeUpdate();
            addResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addResult; 
    }
    
    /*
    * Add new user/role pair
    */
    public boolean addUserRole(String userName, String roleName) {
        boolean addResult = false;
	String sql = "INSERT INTO users_roles"
                    + "(user_name, role_name) VALUES"
                    + "(? , ?)";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName);
            this.statement.setString(2, roleName);
            this.statement.executeUpdate();
            addResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addResult; 
    }
    
    
    /**
     * add public key when first time generate key pair
     */
    public boolean addPublickKey(String userName, String publicKey) {
        boolean addResult = false;
        String sql = "UPDATE users SET pubkey = ? WHERE user_name = ?";
        try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, publicKey);
            this.statement.setString(2, userName);
            this.statement.executeUpdate();
            addResult = true;
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return addResult;
    }
    
    /**
     * find receiver's public key when admin wants to send message
     */
    public String findPublicKey(String receiverName) {
        String publicKey = "";
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, receiverName);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
                publicKey = this.resultSet.getString("pubkey");
            }	
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publicKey;
    }
    
    /*
    * Update user's information
    */
    public boolean updateUser(User user, String oldName) {
    	boolean updateResult = false;
	String sql = "UPDATE users SET user_name = ? , hashed_password = ? , email = ? , hashed_answer = ?, is_activated = ? WHERE user_name = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, user.getUserName());
            this.statement.setString(2, user.getHashedPassword());
            this.statement.setString(3, user.getEmail());
            this.statement.setString(4, user.getHashedAnswer());
            this.statement.setInt(5, user.getIsActivated());
            if(oldName == null)
                this.statement.setString(6, user.getUserName());
            else
                this.statement.setString(6, oldName);
            this.statement.executeUpdate();
            updateResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult; 
    }
    
    /*
    * Update user/role pair
    */
    public boolean updateUserRole(String oldUserName, String oldRoleName, String newUserName, String newRoleName) {
    	boolean updateResult = false;
	String sql = "UPDATE users_roles SET user_name = ? , role_name = ? WHERE user_name = ? and role_name = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, newUserName);
            this.statement.setString(2, newRoleName);
            this.statement.setString(3, oldUserName);
            this.statement.setString(4, oldRoleName);
            
            this.statement.executeUpdate();
            updateResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult; 
    }
    
    /*
    * Delete user
    */
    public boolean deleteUser(User currentUser) {
        boolean deleteResult = false;
	String sql = "DELETE FROM users WHERE user_name = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, currentUser.getUserName());      
            this.statement.executeUpdate();
            deleteResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResult; 
    }
    
    /*
    * Delete user/role pair
    */
    public boolean deleteUserRole(String userName, String roleName) {
        boolean deleteResult = false;
        String sql;
        if(roleName == null)
            sql = "DELETE FROM users_roles WHERE user_name = ?";
        else
            sql = "DELETE FROM users_roles WHERE user_name = ? and role_name = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, userName); 
            if(roleName != null)
                this.statement.setString(2, roleName);
            this.statement.executeUpdate();
            deleteResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResult; 
    }
    
    /* 
    * Close database connection.
    */
    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } 
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * create new message
     * @param message
     * @return  
     */
    public boolean addMessage(Message message) {
        boolean addResult = false;
	String sql = "INSERT INTO message"
                    + "(sender, receiver, time, content, is_read) VALUES"
                    + "(? , ? , NOW(), ? , ?)";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, message.getSender());
            this.statement.setString(2, message.getReceiver());
            this.statement.setString(3, message.getContent());    
            this.statement.setInt(4, message.getIs_read());    
            this.statement.executeUpdate();
            addResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addResult; 
    }
    
    /**
     * list all message sent by sender
     * @param sender
     * @return 
     */
    public List<Message> queryAllMessages(String sender) {
        List<Message> messageList = new ArrayList<>();
	Message message;
        String sql = "SELECT * FROM message WHERE sender = ? ORDER BY time";
	try {	
            this.statement = connection.prepareStatement(sql);
            statement.setString(1, sender);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
		message = new Message();
                message.setMid(this.resultSet.getInt("mid"));
                message.setSender(this.resultSet.getString("sender"));
                message.setReceiver(this.resultSet.getString("receiver"));
                message.setContent(this.resultSet.getString("content"));
                message.setTime(this.resultSet.getTimestamp("time"));
                message.setIs_read(this.resultSet.getInt("is_read"));
		messageList.add(message);
            }	
            this.statement.close();
	} 
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageList;
    }
    
    /**
     * list all messages for one receiver
     * @param receiver
     * @return 
     */
    public List<Message> queryAllMessagesForReceiver(String receiver) {
        List<Message> messageList = new ArrayList<>();
	Message message;
        String sql = "SELECT * FROM message WHERE receiver = ? ORDER BY time";
	try {	
            this.statement = connection.prepareStatement(sql);
            statement.setString(1, receiver);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
		message = new Message();
                message.setMid(this.resultSet.getInt("mid"));
                message.setSender(this.resultSet.getString("sender"));
                message.setContent(this.resultSet.getString("content"));
                message.setTime(this.resultSet.getTimestamp("time"));
                message.setIs_read(this.resultSet.getInt("is_read"));
		messageList.add(message);
            }	
            this.statement.close();
	} 
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageList;
    }
    
    /**
     * after receiver read the message, the message status change to read
     * @param mid
     * @return 
     */
    public boolean updateMessageToRead(int mid) {
        boolean updateResult = false;
        String sql = "UPDATE message SET is_read = 1 WHERE mid = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setInt(1, mid);
            this.statement.executeUpdate();
            updateResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult; 
    }
    
    /**
     * admin delete the message which sent to a receiver
     * @param sender
     * @param mid
     * @return 
     */
    public boolean deleteMessageBySender(User sender, int mid) {
        boolean deleteResult = false;
        String sql = "DELETE FROM message WHERE mid = ? AND sender = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setInt(1, mid);  
            this.statement.setString(2, sender.getUserName());
            this.statement.executeUpdate();
            deleteResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleteResult; 
    }
    
    /**
     * admin update message content and set unread
     * @param mid
     * @param content
     * @return 
     */
    public boolean updateMessageContent (int mid, String content) {
        boolean updateResult = false;
        String sql = "UPDATE message SET is_read = 0 , content = ?, time = NOW() WHERE mid = ?";
    	try {			
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, content);
            this.statement.setInt(2, mid);
            this.statement.executeUpdate();
            updateResult = true;
            this.statement.close();
	}
        catch(SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateResult; 
    }
    
    public Message findMessage(int mid) {
        Message message = new Message();
        String sql = "SELECT * FROM message WHERE mid = ?";
        try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setInt(1, mid);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()) {
                message.setMid(this.resultSet.getInt("mid"));
                message.setSender(this.resultSet.getString("sender"));
                message.setReceiver(this.resultSet.getString("receiver"));
                message.setContent(this.resultSet.getString("content"));
                message.setIs_read(this.resultSet.getInt("is_read"));
                message.setTime(this.resultSet.getTimestamp("time"));
            }	
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}