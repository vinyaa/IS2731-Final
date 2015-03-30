package models.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hab81
 */
public class SessionManager {
    /*
    * Use MD5 aogorithm to generate code (signature) of an active session.
    */
    public static String generateSessionCode(String plainText) {
        String hashString = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = messageDigest.digest(plainText.getBytes("UTF-8"));
            
            BigInteger bigInt = new BigInteger(1,hashedBytes);
            hashString = bigInt.toString(16);    
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashString;
    }
}
