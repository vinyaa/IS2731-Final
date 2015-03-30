package models.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hab81
 * This class authenticates if a new comer can login as a valid user.
 */
public class UserValidator {
    
    private final String specialCharRegExp = "[^0-9a-zA-Z]";
    
    public UserValidator() {
    }

    /*
    * Validate if a user who has userName
    */
    public boolean validateUserName(String userName) {
        //Check user name length
        if(userName.length() == 0 || userName.length() > 20) {
            return false;
        }
        //check characters of user name, only numbers and letters are allowed.
        Pattern pattern = Pattern.compile(specialCharRegExp);
        Matcher matcher = pattern.matcher(userName);
        return matcher.find() != true;
    }
    
    /*
    * Validate the password.
    */
    public boolean validateUserPassword(String password) {
        //check password length
        return (password.length() > 0 && password.length() <= 20);
    }
}
