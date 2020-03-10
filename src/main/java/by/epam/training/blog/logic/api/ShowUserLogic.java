package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationUser;

public interface ShowUserLogic  {
    ApplicationUser showUser(String login);

    void subscribeOnUser(Integer subscriberId, Integer userId);

    void unsubscribeOnUser(Integer subscriberId, Integer userId);

    boolean isSigned(Integer sub, Integer user);
}
