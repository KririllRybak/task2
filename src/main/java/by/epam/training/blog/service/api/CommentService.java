package by.epam.training.blog.service.api;

import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.domain.db_entity.User;

import java.util.List;
public interface CommentService {
    Integer createComment(DbComment comment);

    Integer deleteComment(Integer commentId);

    List<DbComment> getCommentForUser(Integer userId);

    List<DbComment> getCommentForPost(Integer postId);

    User showAuthor(Integer commentId);

    DbComment showComment(Integer commentId);
}
