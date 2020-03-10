package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.db_entity.DbComment;

import java.util.List;

public interface CommentDao extends CrudDao<DbComment>{
    List<DbComment> getCommentForUser(Integer userId) ;
    List<DbComment> getCommentForPost(Integer postId) ;
    Integer showAuthor(Integer commentId) ;
    Integer showComment(Integer commentId) ;
}
