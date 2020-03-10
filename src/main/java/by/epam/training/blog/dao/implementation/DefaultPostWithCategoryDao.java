package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.dao.api.PostWithCategoryDao;
import by.epam.training.blog.dao.mapper.CategoryIdMapper;
import by.epam.training.blog.dao.mapper.PostIdMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultPostWithCategoryDao implements PostWithCategoryDao {

    private static final String FIND_POST_BY_CATEGORY = "SELECT post_id FROM public.post_category" +
            " WHERE category_id = ?;";
    private static final String FIND_CATEGORY_BY_POST = "SELECT category_id FROM public.post_category" +
            " WHERE post_id = ?;";
    private static final String LINK_CATEGORY_AND_POST = "INSERT INTO public.post_category(post_id,category_id) VALUES (?,?);";

    private JdbcTemplate jdbcTemplate;
    private PostIdMapper postIdMapper;
    private CategoryIdMapper categoryIdMapper;

    public DefaultPostWithCategoryDao(JdbcTemplate jdbcTemplate, PostIdMapper postIdMapper,
                                      CategoryIdMapper categoryIdMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.postIdMapper = postIdMapper;
        this.categoryIdMapper = categoryIdMapper;
    }

    @Override
    public List<Integer> findPostByCategory(Integer categoryId) {
        return jdbcTemplate.query(FIND_POST_BY_CATEGORY, new Object[]{categoryId}, postIdMapper);
    }

    @Override
    public List<Integer> findCategoryByPost(Integer postId) {
        return jdbcTemplate.query(FIND_CATEGORY_BY_POST, new Object[]{postId},categoryIdMapper );
    }

    @Override
    public void linkCategoryAndPost(Integer postId, Integer categoryId) {
        Object[] params = new Object[]{postId, categoryId};
        jdbcTemplate.update(LINK_CATEGORY_AND_POST, params);
    }
}
