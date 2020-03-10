package by.epam.training.blog.service.api;

import by.epam.training.blog.domain.db_entity.DbCategory;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CategoryService {
    public List<DbCategory> showPostCategories(Integer postId);

    DbCategory addNewCategory(String name);

    String returnCategoryName(Integer categoryId);

    int returnCategoryIdByName(String name);

    List<Integer> findPostByCategory(Integer categoryId);

    List<Integer> findCategoryByPost(Integer postId);

    List<DbCategory> getAllCategory();

    boolean isExists(String name);

    boolean deleteCategory(Integer id);
}
