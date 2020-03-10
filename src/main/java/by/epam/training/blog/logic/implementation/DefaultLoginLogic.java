package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.LoginLogic;
import by.epam.training.blog.logic.bcrypt.PasswordChecker;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DefaultLoginLogic implements LoginLogic {

    private UserService userService;
    private UserConverter userConverter;

    public DefaultLoginLogic(UserService userService,
                             Converter userConverter) {
        this.userConverter = (UserConverter)userConverter;
        this.userService = userService;
    }

    @Override
    public boolean checkAuthorizationData(String login, String password) {
        User dbUser = null;
        dbUser = userService.getByLogin(login);
        if (dbUser != null) {
            String hash = dbUser.getPassword();
            boolean isValid = PasswordChecker.checkPassword(password, hash);
            return isValid;
        }
        return false;
    }

    @Override
    public ApplicationUser getUser(String login, String password) {
        User dbUser = null;
        dbUser = userService.getByLogin(login);
        if (dbUser != null) {
            String hash = dbUser.getPassword();
            ApplicationUser applicationUser = userConverter.
                    convertDbEntityToApplicationEntity(dbUser);
            return applicationUser;
        }
        return null;
    }
}
