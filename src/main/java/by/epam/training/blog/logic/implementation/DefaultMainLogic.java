package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.api.MainLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.PostConverter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultMainLogic implements MainLogic {
    private static final int NUMBER_OF_RECORDS_PER_PAGE = 6;

    private PostService postService;
    private CategoryService categoryService;
    private PostConverter postConverter;

    public DefaultMainLogic(Converter postConverter,
                            CategoryService categoryService,
                            PostService postService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.postConverter = (PostConverter) postConverter;
    }

    @Override
    public List<ApplicationPost> showPosts(int currentPage) {
        List<ApplicationPost> appPosts = new ArrayList<>();
        List<DbPost> dbPosts;
        dbPosts = postService.showAllPost(currentPage, NUMBER_OF_RECORDS_PER_PAGE);
        for (DbPost dbPost : dbPosts) {
            appPosts.add(postConverter.convertDbEntityToApplicationEntity(dbPost));
        }
        return appPosts;
    }

    @Override
    public List<DbCategory> getCategory() {
        List<DbCategory> categories = null;
        categories = categoryService.getAllCategory();
        return categories;
    }

    @Override
    public int getNumberOfPages() {
        int rows = 0;
        int nOfPages = 0;
        rows = postService.getNumberOfRows();
        nOfPages = rows / NUMBER_OF_RECORDS_PER_PAGE;
        if (nOfPages % NUMBER_OF_RECORDS_PER_PAGE > 0) {
            nOfPages++;
        }
        return nOfPages;
    }

    @Override
    public Role getUserRole(String login) {
        return Role.USER;
    }
}

