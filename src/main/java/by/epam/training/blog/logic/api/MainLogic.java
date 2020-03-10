package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.db_entity.DbCategory;

import java.util.List;

public interface MainLogic {
    List<ApplicationPost> showPosts(int currentPage);

    List<DbCategory> getCategory();

    int getNumberOfPages();

    Role getUserRole(String login);
}
