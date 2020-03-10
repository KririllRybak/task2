package by.epam.training.blog.service.implementation;

import by.epam.training.blog.dao.api.CategoryDao;
import by.epam.training.blog.dao.api.PostWithCategoryDao;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.service.api.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultCategoryService implements CategoryService {

    private CategoryDao categoryDao;
    private PostWithCategoryDao postWithCategoryDao;


    public DefaultCategoryService(CategoryDao categoryDao,
                                  PostWithCategoryDao postWithCategoryDao) {
        this.categoryDao = categoryDao;
        this.postWithCategoryDao = postWithCategoryDao;
    }

    @Override
    public List<DbCategory> showPostCategories(Integer postId) {
        List<Integer> ids = postWithCategoryDao.findCategoryByPost(postId);
        List<DbCategory> categories = new ArrayList<>();
        for (Integer id : ids) {
            categories.add(collectCategory(id));
        }
        return categories;
    }

    @Override
    public DbCategory addNewCategory(String name) {
        int categoryId = categoryDao.addNewCategory(name);
        return collectCategory(categoryId);
    }

    @Override
    public String returnCategoryName(Integer categoryId) {
        String name = categoryDao.returnCategoryName(categoryId);
        return name;
    }

    @Override
    public int returnCategoryIdByName(String name) {
        Integer categoryId = categoryDao.returnCategoryIdByName(name);
        return categoryId;
    }

    @Override
    public List<Integer> findPostByCategory(Integer categoryId) {
        List<Integer> postIdCollection = postWithCategoryDao.findPostByCategory(categoryId);
        return postIdCollection;
    }

    @Override
    public List<Integer> findCategoryByPost(Integer postId) {
        List<Integer> categoryIdCollection = postWithCategoryDao.findCategoryByPost(postId);
        return categoryIdCollection;
    }

    @Override
    public List<DbCategory> getAllCategory() {
        List<DbCategory> categories = categoryDao.getAllCategories();
        return categories;
    }

    @Override
    public boolean isExists(String name) {
        boolean result = categoryDao.isExists(name);
        return result;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        String name = returnCategoryName(id);
        categoryDao.deleteCategory(id);
        if (isExists(name)) {
            return false;
        } else {
            return true;
        }
    }

    private DbCategory collectCategory(int id) {
        DbCategory dbCategory = new DbCategory();
        dbCategory.setName(categoryDao.returnCategoryName(id));
        List<Integer> posts = postWithCategoryDao.findPostByCategory(id);
        for (Integer postId : posts) {
            dbCategory.addPost(postId);
        }
        return dbCategory;
    }
}
