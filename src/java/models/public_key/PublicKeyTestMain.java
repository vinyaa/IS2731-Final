package models.public_key;

import java.io.IOException;
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
        try{
            String message = "This is just a message for test, see if the public/private key works.";

            //generate public and private keys
            KeyPair keyPair = KeysManager.generateKeyPairs();
            publicKeyTest = keyPair.getPublic();
            privateKeyTest = keyPair.getPrivate();
           
            //enctypt the message
            byte[] enctyptedMessageByte = KeysManager.encryptMessage(message.getBytes(), privateKeyTest);

            // decrypt the message
            byte[] decryptedMessageByte = KeysManager.decryptMessage(enctyptedMessageByte, publicKeyTest);
            String decryptedMessage = new String(decryptedMessageByte);
            
            System.out.println(decryptedMessage);
        }
        catch(Exception exception){
            System.out.println("In RsaAlice.main(): "+exception.toString());
        }
    }
}
