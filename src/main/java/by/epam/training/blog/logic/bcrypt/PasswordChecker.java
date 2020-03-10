package by.epam.training.blog.logic.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordChecker {
    public static boolean checkPassword(String password, String generatedSecuredPasswordHash){
        boolean matched = BCrypt.checkpw(password, generatedSecuredPasswordHash);
        return matched;
    }
}
