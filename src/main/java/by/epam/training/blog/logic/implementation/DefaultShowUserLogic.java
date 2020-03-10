package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.ShowUserLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
public class DefaultShowUserLogic implements ShowUserLogic {

    private UserService userService;
    private UserConverter userConverter;

    public DefaultShowUserLogic(UserService userService,
                                Converter userConverter) {
        this.userConverter = (UserConverter) userConverter;
        this.userService = userService;
    }

    @Override
    public ApplicationUser showUser(String login) {
        ApplicationUser appUser = null;
        User user = userService.getByLogin(login);
        appUser = userConverter.convertDbEntityToApplicationEntity(user);
        return appUser;
    }

    @Override
    public void subscribeOnUser(Integer subscriberId, Integer userId) {
        userService.subscribe(subscriberId, userId);
    }

    @Override
    public void unsubscribeOnUser(Integer subscriberId, Integer userId) {
        userService.unsubscribe(subscriberId, userId);
    }

    @Override
    public boolean isSigned(Integer sub, Integer user) {
        boolean result = false;
        result = userService.isSigned(sub, user);
        return result;
    }
}
