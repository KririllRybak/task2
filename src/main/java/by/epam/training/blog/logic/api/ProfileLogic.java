package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;

import java.io.InputStream;
import java.util.List;

public interface ProfileLogic {
    User collectUpdatedUser
            (String email, String password, String confirm, String aboutMe, ApplicationUser appUser);

    void updateUser(User user);

    ApplicationUser updateAuthorizedUser(User user, ApplicationUser appUser);

    ApplicationUser uploadImg(Integer userId, InputStream inputStream);

    List<ApplicationUser> getMySubscriptions(Integer userId);
}
