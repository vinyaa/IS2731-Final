package models.user;

/**
 *
 * @author hab81
 */
public class UserRole {
    private String userName;
    private String roleName;
    
    public UserRole() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
