package test;

import models.email.EmailNotifier;
import models.user.UserManager;

/**
 *
 * @author hab81
 */
public class UserTestMain {
    public static void main(String[] args) {
        EmailNotifier email = new EmailNotifier();
        email.sendMail("test@test.com", "hello", "this is a test.");
    }
}
