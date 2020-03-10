package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.ProfileLogic;
import by.epam.training.blog.logic.bcrypt.Cryptographer;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultProfileLogic implements ProfileLogic {
    private final String EMPTY_STRING = "";
    private UserService userService;
    private UserConverter userConverter;

    public DefaultProfileLogic(UserService userService,
                               Converter userConverter) {
        this.userService = userService;
        this.userConverter = (UserConverter) userConverter;
    }

    @Override
    public User collectUpdatedUser
            (String email, String password, String confirm, String aboutMe, ApplicationUser appUser) {
        User user = new User();
        if (password.equals(EMPTY_STRING) || password == null) {
            user.setPassword(appUser.getPassword());
        } else {
            if (password.equals(confirm)) {
                String encryptPassword = Cryptographer.encrypt(password);
                user.setPassword(encryptPassword);
            }
        }
        user.setId(appUser.getId());
        if (email.equals(EMPTY_STRING) || email == null) {
            user.setEmail(appUser.getEmail());
        } else {
            user.setEmail(email);
        }
        if (aboutMe.equals(EMPTY_STRING) || aboutMe == null) {
        } else {
            user.setAboutMe(aboutMe);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @Override
    public ApplicationUser updateAuthorizedUser(User user, ApplicationUser appUser) {
        ApplicationUser newAppUser = new ApplicationUser(appUser.getLogin(), user.getPassword(),
                user.getEmail(), appUser.getCreationStamp(),
                appUser.getImg(), appUser.getAppPosts(), appUser.getSubscribers(),
                appUser.getAppComments(), appUser.getRole(), user.getAboutMe());
        newAppUser.setId(user.getId());
        return newAppUser;
    }

    @Override
    public ApplicationUser uploadImg(Integer userId, InputStream inputStream) {
        ApplicationUser appUser = null;
        userService.addPicture(userId, inputStream);
        User dbUser = userService.findUser(userId);

        return appUser;
    }

    @Override
    public List<ApplicationUser> getMySubscriptions(Integer userId) {
        List<ApplicationUser> mySubscriptions = new ArrayList<>();
            List<User> dbUsers = userService.showUserSubscriptions(userId);
            for (User user : dbUsers) {
                ApplicationUser appUser = userConverter.convertDbEntityToApplicationEntity(user);
                mySubscriptions.add(appUser);
            }
        return mySubscriptions;
    }
}
