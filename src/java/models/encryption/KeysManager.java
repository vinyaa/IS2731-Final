package models.encryption;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author hab81
 */
public class KeysManager {
    
    public static KeyPair generateKeyPairs(){
        KeyPairGenerator keyPairGenerator;
        KeyPair keyPair = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //the size of key is 1024.
            keyPairGenerator.initialize(1024);
            //keyPair consists of public key and private key.
            keyPair = keyPairGenerator.generateKeyPair();
        } 
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keyPair;
    }
    
    protected static byte[] encryptMessage(byte[] message, PublicKey publicKey) {
        byte[] encryptedMessage = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
            //encrypt the message with public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedMessage = cipher.doFinal(message);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(KeysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    
    protected static byte[] encryptMessage(byte[] message, PrivateKey privateKey) {
        byte[] encryptedMessage = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
            //encrypt the message with public key
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            encryptedMessage = cipher.doFinal(message);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(KeysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    
    protected static byte[] decryptMessage(byte[] message, PrivateKey privateKey) {
        byte[] decryptedMessage = null;
        Cipher cipher;
        try {
             cipher = Cipher.getInstance("RSA");
            // decrypt the message with private key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedMessage = cipher.doFinal(message);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(KeysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decryptedMessage;
    }
    
    protected static byte[] decryptMessage(byte[] message, PublicKey publicKey) {
        byte[] decryptedMessage = null;
        Cipher cipher;
        try {
             cipher = Cipher.getInstance("RSA");
            // decrypt the message with private key
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            decryptedMessage = cipher.doFinal(message);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(KeysManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decryptedMessage;
    }
}
