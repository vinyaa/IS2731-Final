package models.encryption;

/**
 *
 * @author hab81
 * This class reverse text.
 */
public class TextReverser {
    
    //length is the required length (number of bytes) of the output   
    public static byte[] getReversedText(String originalString, int length) {
        //the the length of input String
        if(originalString.length() > length)
            return null;
        
        byte[] byteText = new byte[length];
        //copy the original string byte by byte
        System.arraycopy(originalString.getBytes(), 0, byteText, 0, originalString.getBytes().length);

        int frontIndex = 0;
        int rearIndex = originalString.getBytes().length - 1;
        byte tempByte;
        //reverse the byte array byte by byte.
        while(frontIndex < rearIndex) {
            tempByte = byteText[rearIndex];
            byteText[rearIndex] = byteText[frontIndex];
            byteText[frontIndex] = tempByte;
            frontIndex ++;
            rearIndex --;
        }
        //put '0' at the rear of the byte array (fill out the blank items).
        for(int i = originalString.getBytes().length; i < length; i++) {
            byteText[i] = '0';
        }
        //return the reversed text (byte array).
        return byteText;
    }
}
