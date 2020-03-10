package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.db_entity.DbCategory;

import java.util.List;

public interface CategoryDao {
     Integer addNewCategory(String name) ;
     String returnCategoryName(Integer categoryId);
     int returnCategoryIdByName(String name) ;
     List<DbCategory> getAllCategories() ;
     boolean isExists(String categoryName);
     void deleteCategory(Integer idCategory) ;
}
