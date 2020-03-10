package by.epam.training.blog.service.api;

import by.epam.training.blog.domain.db_entity.DbPost;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

public interface PostService {
    List<DbPost> showAllPost(int currentPage, int recordsPerPage);

    int getNumberOfRows();

    List<DbPost> findPostsByContent(String content);

    List<DbPost> findUserPosts(Integer dbUser);

    List<DbPost> findPostsByCategory(Integer categoryId);

    Integer addImgToPost(Integer postId, InputStream fis);

    List<String> showPostImg(Integer postId);

    Integer deletePost(Integer postId);

    Integer updatePost(DbPost dbPost);

    DbPost findPost(Integer id);

    Integer save(DbPost dbPost);

    void linkPostWithcategory(Integer postId, Integer categoryId);
}
