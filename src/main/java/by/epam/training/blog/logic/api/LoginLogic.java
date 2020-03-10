package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationUser;

public interface LoginLogic {
    boolean checkAuthorizationData(String login, String password);

    ApplicationUser getUser(String login, String password);
}
