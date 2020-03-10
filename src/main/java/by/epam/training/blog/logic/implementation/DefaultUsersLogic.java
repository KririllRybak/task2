package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.UsersLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultUsersLogic implements UsersLogic {

    private UserService userService;
    private UserConverter userConverter;

    public DefaultUsersLogic(UserService userService,
                             Converter userConverter) {
        this.userService =  userService;
        this.userConverter = (UserConverter) userConverter;
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        List<User> users = null;
        List<ApplicationUser> appUsers = new ArrayList<>();
        users = userService.findAllUsers();
        for (User user : users) {
            User userFromDb = userService.findUser(user.getId());
            appUsers.add(userConverter.convertDbEntityToApplicationEntity(userFromDb));
        }
        return appUsers;
    }

    @Override
    public void deleteUser(Integer userId) {
        userService.deleteUser(userId);
    }

    @Override
    public void changeRole(Integer role, Integer userId) {
        userService.changeRole(role, userId);
    }


}
