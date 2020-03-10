package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.api.CategoryLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.PostConverter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.CommentService;
import by.epam.training.blog.service.api.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultCategoryLogic implements CategoryLogic {

    private PostService postService;
    private CategoryService categoryService;
    private PostConverter postConverter;

    public DefaultCategoryLogic(PostService postService, CategoryService categoryService,
                                PostConverter postConverter) {
        this.postConverter =  postConverter;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    public List<ApplicationPost> showPosts(Integer categoryId) {
        List<ApplicationPost> appPosts = new ArrayList<>();
        List<DbPost> dbPosts = new ArrayList<>();
        dbPosts = postService.findPostsByCategory(categoryId);
        for (DbPost dbPost : dbPosts) {
            appPosts.add(postConverter.convertDbEntityToApplicationEntity(dbPost));
        }
        return appPosts;
    }

    public String getCategoryName(int id) {
        String name = categoryService.returnCategoryName(id);
        if (name.isEmpty()) {
            return "Category";
        }
        return name;
    }

    @Override
    public boolean isExists(String name) {
        boolean isExists = false;
        isExists = categoryService.isExists(name);
        return isExists;
    }

    @Override
    public boolean deleteCategory(Integer categoryId) {
        boolean result = false;
        result = categoryService.deleteCategory(categoryId);
        return result;
    }

    @Override
    public void createCategory(String category) {
        categoryService.addNewCategory(category);
    }
}
