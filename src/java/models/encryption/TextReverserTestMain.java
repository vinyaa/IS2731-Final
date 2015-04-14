package models.encryption;

/**
 *
 * @author hab81
 */
public class TextReverserTestMain {
    public static void main(String[] args) throws Exception{
        System.out.println(new String(TextReverser.getReversedText("this is a test sentence.", 1024)));
        System.out.println(TextReverser.getReversedText("hello", 1024).length);
    }
}
