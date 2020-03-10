package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.dao.api.PostDao;
import by.epam.training.blog.dao.mapper.PostMapper;
import by.epam.training.blog.domain.db_entity.DbPost;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultPostDao implements PostDao {

    private static final String CREATE_POST = "INSERT INTO public.post(title,author,post_text) VALUES(?,?,?);";
    private static final String FIND_POST_BY_SIMILAR_TITLE = "SELECT id,title,author,post_text,created_at" +
            " FROM public.post  WHERE post.title LIKE ?;";
    private static final String FIND_POST_BY_SIMILAR_TEXT = "SELECT id,title,author,post_text,created_at" +
            " FROM public.post  WHERE post.post_text LIKE ? ;";
    private static final String FIND_POST_BY_ID = "SELECT id,title,author,post_text,created_at" +
            " FROM public.post WHERE id = ?;";
    private static final String UPDATE_POST = "UPDATE public.post SET title = ?, post_text = ? WHERE id = ?;";
    private static final String DELETE_POST = "DELETE FROM public.post WHERE id = ?;";
    private static final String SHOW_AUTHOR = "SELECT author FROM public.post WHERE id = ?;";
    private static final String SHOW_USER_POST = "SELECT id,title,author,post_text,created_at" +
            " FROM public.post WHERE author = ?;";
    private static final String SHOW_ALL_POST = "SELECT id,title,author,post_text,created_at" +
            " FROM public.post ORDER BY id DESC LIMIT ? OFFSET ? ; ";
    private static final String GET_ROW_COUNT = "SELECT COUNT(id) FROM post;";
    private JdbcTemplate jdbcTemplate;
    private PostMapper postMapper;

    public DefaultPostDao(JdbcTemplate jdbcTemplate, PostMapper postMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.postMapper = postMapper;
    }


    @Override
    public List<DbPost> findPostByTitle(String content) {
        content = "%"+content+"%";
        return findPostBy(content, FIND_POST_BY_SIMILAR_TITLE);
    }

    @Override
    public List<DbPost> findPostByText(String content) {
        content = "%"+content+"%";
        return findPostBy(content, FIND_POST_BY_SIMILAR_TEXT);
    }

    private List<DbPost> findPostBy(String content, String findPostBySimilarContent) {
        return jdbcTemplate.query(findPostBySimilarContent, new Object[]{content}, postMapper);
    }


    @Override
    public List<DbPost> findUserPosts(Integer userId) {
        return jdbcTemplate.query(SHOW_USER_POST, new Object[]{userId}, postMapper);
    }

    @Override
    public List<DbPost> showAllPost(int start, int recordsPerPage) {
        return jdbcTemplate.query(SHOW_ALL_POST,new Object[]{recordsPerPage,start}, postMapper);
    }

    @Override
    public int getNumberOfRows() {
        return jdbcTemplate.queryForObject(GET_ROW_COUNT, Integer.class);
    }

    @Override
    public Integer create(DbPost entity) {
        Object[] params = new Object[]{entity.getTitle(),
                entity.getAuthor(), entity.getText()};
        return jdbcTemplate.update(CREATE_POST,params);
    }

    @Override
    public DbPost read(Integer id) {
        return jdbcTemplate.queryForObject(FIND_POST_BY_ID,new Object[]{id},postMapper);
    }

    @Override
    public Integer update(DbPost entity) {
        Object[] params = new Object[]{entity.getTitle(),
                entity.getText(), entity.getId()};
      return jdbcTemplate.update(UPDATE_POST,params);
    }

    @Override
    public Integer delete(Integer id) {
        return jdbcTemplate.update(DELETE_POST,new Object[]{id});
    }


}
