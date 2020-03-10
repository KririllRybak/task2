package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationPost;

import java.util.List;

public interface CategoryLogic {

    List<ApplicationPost> showPosts(Integer categoryId);

    String getCategoryName(int id);

    boolean isExists(String name);

    boolean deleteCategory(Integer categoryId);

    void createCategory(String category);
}
