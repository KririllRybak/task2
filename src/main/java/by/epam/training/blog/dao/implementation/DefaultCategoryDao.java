package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.dao.api.CategoryDao;
import by.epam.training.blog.dao.mapper.CategoryMapper;
import by.epam.training.blog.domain.db_entity.DbCategory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultCategoryDao implements CategoryDao {

    private static final String CREATE_CATEGORY = "INSERT INTO public.category(name) VALUES(?);";
    private static final String SHOW_CATEGORY_NAME = "SELECT name FROM public.category WHERE id = ?;";
    private static final String SHOW_CATEGORY_ID = "SELECT id FROM public.category WHERE name = ?;";
    private static final String GET_ALL = "SELECT id,name FROM public.category";
    private static final String CATEGORY_EXISTS = "SELECT EXISTS(SELECT id,name FROM public.category " +
            "WHERE category.name = ?)";
    private static final String DELETE_CATEGORY = "DELETE FROM public.category WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    private CategoryMapper categoryMapper;

    public DefaultCategoryDao(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Integer addNewCategory(String name) {
        return jdbcTemplate.update(CREATE_CATEGORY, new Object[]{name});
    }

    public String returnCategoryName(Integer categoryId) {
        return jdbcTemplate.queryForObject(SHOW_CATEGORY_NAME, new Object[]{categoryId}, String.class);
    }

    @Override
    public int returnCategoryIdByName(String name) {
        return jdbcTemplate.queryForObject(SHOW_CATEGORY_ID, new Object[]{name}, Integer.class);
    }

    @Override
    public List<DbCategory> getAllCategories() {
        return jdbcTemplate.query(GET_ALL, categoryMapper);
    }

    @Override
    public boolean isExists(String categoryName) {
        return jdbcTemplate.queryForObject(CATEGORY_EXISTS,new Object[]{categoryName} ,Boolean.class);
    }

    @Override
    public void deleteCategory(Integer idCategory) {
        jdbcTemplate.update(DELETE_CATEGORY, new Object[]{idCategory});
    }


}
