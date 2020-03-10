package by.epam.training.blog.logic.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class Cryptographer {
    public static String encrypt(String password){
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return generatedSecuredPasswordHash;
    }
}
