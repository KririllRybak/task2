package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.RegistrationLogic;
import by.epam.training.blog.logic.bcrypt.Cryptographer;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
public class DefaultRegistrationLogic implements RegistrationLogic {

    private UserService userService;

    public DefaultRegistrationLogic(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean checkForLogin(String login) {
        User dbUser = null;
        try {
            dbUser = userService.getByLogin(login);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPasswordValidation(String pass) {
        if (pass.length() >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public boolean comparePasswords(String password, String confimPassword) {
        boolean isValid = password.equals(confimPassword);
        return isValid;
    }

    private String encodePassword(String password) {
        String hash = Cryptographer.encrypt(password);
        return hash;
    }

    @Override
    public boolean registerUser(String login, String password, String email, String contextPath) {
        User dbUser = new User();
        String hash = encodePassword(password);
        dbUser.setPassword(hash);
        dbUser.setLogin(login);
        dbUser.setEmail(email);
        int result = userService.save(dbUser);
        if (result != 0) {
            return true;
        }
        return false;

    }
}