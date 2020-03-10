package by.epam.training.blog.logic.entity_converter.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationCategory;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
public class CategoryConverter implements Converter<DbCategory, ApplicationCategory> {

    private CategoryService categoryService;
    private PostService postService;

    public CategoryConverter(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @Override
    public ApplicationCategory convertDbEntityToApplicationEntity(DbCategory dbCategory) {
        ApplicationCategory appCategory = new ApplicationCategory();
        appCategory.setId(dbCategory.getId());
        appCategory.setName(dbCategory.getName());
        List<Integer> postIdCollection = categoryService.
                findPostByCategory(dbCategory.getId());
        List<DbPost> dbPosts = new ArrayList<>();
        for (Integer postId : postIdCollection) {
            DbPost dbPost = postService.findPost(postId);
            dbPosts.add(dbPost);
        }
        return appCategory;
    }
}
