package models.encryption;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 *
 * @author hab81
 */
public class PublicKeyTestMain {
    public static void main(String[] args) 
    {
        PublicKey publicKeyTest;
        PrivateKey privateKeyTest;
       // try{
            String message = "Message";

            //generate public and private keys
            KeyPair keyPair = KeysManager.generateKeyPairs();
            publicKeyTest = keyPair.getPublic();
            privateKeyTest = keyPair.getPrivate();
            
            byte[] publicBytes = publicKeyTest.getEncoded();
            byte[] privateBytes = privateKeyTest.getEncoded();
            
            String mypublic = javax.xml.bind.DatatypeConverter.printBase64Binary(publicBytes);
            String myprivate = javax.xml.bind.DatatypeConverter.printBase64Binary(privateBytes);
            
            System.out.println("My public key: " + mypublic);
            System.out.println("My private key: " + myprivate);
            
            // Reverse Bytes
            byte[] byteMessage = message.getBytes();
            String firstBytes = javax.xml.bind.DatatypeConverter.printHexBinary(byteMessage);
            byte[] reversedMessage = byteMessage;
            reverse(reversedMessage);
            String secondBytes = javax.xml.bind.DatatypeConverter.printHexBinary(reversedMessage);
            //enctypt the message
            byte[] enctyptedMessageByte = KeysManager.encryptMessage(reversedMessage, privateKeyTest);
            String encryptedMessage = javax.xml.bind.DatatypeConverter.printBase64Binary(enctyptedMessageByte);
            
            // decrypt the message
            byte[] decryptedMessageByte = KeysManager.decryptMessage(enctyptedMessageByte, publicKeyTest);
            String decryptedMessage = new String(decryptedMessageByte);
            
            System.out.println("Byte Array (in hex): " + firstBytes);
            System.out.println("Byte Array Reversed (in hex): " + secondBytes);
            System.out.println("Encrypted Reversed Bytes: " + encryptedMessage);
            System.out.println("Decrypted Reversed Bytes: " + decryptedMessage);
        //}
        //catch(Exception exception){
        //    System.out.println("In RsaAlice.main(): "+exception.toString());
        //}
    }
    
    // http://www.java2s.com/Code/Java/Collections-Data-Structure/Reversestheorderofthegivenbytetypearray.htm
    public static void reverse(byte[] array) {
         if (array == null) {
             return;
         }
         int i = 0;
         int j = array.length - 1;
         byte tmp;
         while (j > i) {
             tmp = array[j];
             array[j] = array[i];
             array[i] = tmp;
             j--;
             i++;
         }
     }   
}
