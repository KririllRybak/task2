package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationUser;

import java.util.List;

public interface UsersLogic {
    List<ApplicationUser> getAllUsers();

    void deleteUser(Integer userId);

    void changeRole(Integer role, Integer userId);
}
