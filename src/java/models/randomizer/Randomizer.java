package models.randomizer;

import java.util.Random;
import models.user.UserManager;

/**
 *
 * @author hab81
 */
public class Randomizer {
    public static String getRandomToken() {
        //use Nano time as random seed
        Random generator = new Random(System.nanoTime());
        long number = generator.nextLong();
        String result = UserManager.encryptText(Long.toString(number));
        return result;
    }
}
