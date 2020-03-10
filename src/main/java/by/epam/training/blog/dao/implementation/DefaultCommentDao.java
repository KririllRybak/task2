package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.dao.api.CommentDao;
import by.epam.training.blog.dao.mapper.AuthorMapper;
import by.epam.training.blog.dao.mapper.CommentMapper;
import by.epam.training.blog.domain.db_entity.DbComment;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultCommentDao implements CommentDao {

    private static final String CREATE_COMMENT = "INSERT INTO public.comment(author,post_id,comment_text) VALUES(?,?,?);";
    private static final String DELETE_COMMENT_BY_ID = "DELETE FROM public.comment WHERE id = ?;";
    private static final String FIND_COMMENT_BY_ID = "SELECT " +
            "id,author,post_id,comment_text FROM public.comment WHERE id = ?;";
    private static final String UPDATE_COMMENT = "UPDATE public.comment SET comment_text = ? WHERE id = ?;";
    private static final String SHOW_USER_COMMENTS = "SELECT id,author,post_id,comment_text FROM public.comment WHERE comment.author = ?;";
    private static final String SHOW_POST_COMMENTS = "SELECT id,author,post_id,comment_text FROM public.comment WHERE post_id = ?;";
    private static final String SHOW_AUTHOR = "SELECT author FROM public.comment WHERE id = ?;";
    private static final String SHOW_POST = "SELECT post_id FROM public.comment WHERE id = ?;";

    private JdbcTemplate jdbcTemplate;
    private CommentMapper commentMapper;
    private AuthorMapper authorMapper;

    public DefaultCommentDao(JdbcTemplate jdbcTemplate, CommentMapper commentMapper,
                             AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.commentMapper = commentMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<DbComment> getCommentForUser(Integer userId) {
        return jdbcTemplate.query(SHOW_USER_COMMENTS, new Object[]{userId}, commentMapper);
    }

    @Override
    public List<DbComment> getCommentForPost(Integer postId) {
        return jdbcTemplate.query(SHOW_POST_COMMENTS, new Object[]{postId}, commentMapper);
    }

    @Override
    public Integer showAuthor(Integer commentId) {
        return jdbcTemplate.queryForObject(SHOW_AUTHOR, new Object[]{commentId}, authorMapper);
    }

    @Override
    public Integer showComment(Integer commentId) {
        return jdbcTemplate.queryForObject(SHOW_POST, new Object[]{commentId}, Integer.class);
    }

    @Override
    public Integer create(DbComment entity) {
        int author = entity.getAuthor();
        int postId = entity.getIdPost();
        String text = entity.getComment();
        Object[] params = new Object[]{author,postId,text};
        return jdbcTemplate.update(CREATE_COMMENT, params);
    }

    @Override
    public DbComment read(Integer id) {
        return jdbcTemplate.queryForObject(FIND_COMMENT_BY_ID, new Object[]{id}, commentMapper);
    }

    @Override
    public Integer update(DbComment entity) {
        return jdbcTemplate.update(UPDATE_COMMENT, entity);
    }

    @Override
    public Integer delete(Integer id) {
        return jdbcTemplate.update(DELETE_COMMENT_BY_ID, new Object[]{id});
    }
}
