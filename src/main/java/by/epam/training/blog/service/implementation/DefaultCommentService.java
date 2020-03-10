package by.epam.training.blog.service.implementation;

import by.epam.training.blog.dao.api.CommentDao;
import by.epam.training.blog.dao.api.UserDao;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.service.api.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultCommentService implements CommentService {

    private CommentDao commentDao;
    private UserDao userDao;

    public DefaultCommentService(CommentDao commentDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    @Override
    public Integer createComment(DbComment comment) {
        return commentDao.create(comment);
    }

    @Override
    public Integer deleteComment(Integer commentId) {
        return commentDao.delete(commentId);
    }

    @Override
    public List<DbComment> getCommentForUser(Integer userId) {
        List<DbComment> dbComments = commentDao.getCommentForUser(userId);
        return dbComments;
    }

    @Override
    public List<DbComment> getCommentForPost(Integer postId) {
        List<DbComment> dbComments = commentDao.getCommentForPost(postId);
        return dbComments;
    }

    @Override
    public User showAuthor(Integer commentId) {
        User dbUser = userDao.read(commentDao.showAuthor(commentId));
        return dbUser;
    }

    @Override
    public DbComment showComment(Integer commentId) {
        DbComment comment = commentDao.read(commentId);
        return comment;
    }
}
