package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.DbPost;

import java.io.InputStream;
import java.util.List;

public interface PostLogic {
    void deletePost(Integer postId);

    ApplicationUser getUser(Integer id);

    void createPost(String title, Integer authorId, String text,
                    String[] categoryName, List<InputStream> imgStream);

    DbPost getPost(Integer postId);

    void updatePost(Integer postId, String title, String text);

    void addComment(Integer author, Integer postId, String commentText);
    void deleteComment (Integer commentId);
}
