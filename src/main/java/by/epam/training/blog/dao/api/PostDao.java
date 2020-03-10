package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.db_entity.DbPost;

import java.util.List;

public interface PostDao extends CrudDao<DbPost> {
    List<DbPost> findPostByTitle(String content)  ;
    List<DbPost> findPostByText(String content)  ;
    List<DbPost> findUserPosts(Integer dbUser) ;
    List<DbPost> showAllPost(int start, int recordsPerPage) ;
    int getNumberOfRows()  ;
}
