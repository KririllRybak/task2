package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.PostLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.CommentService;
import by.epam.training.blog.service.api.PostService;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Log4j2
public class DefaultPostLogic implements PostLogic {
    private int counter = 0;
    private final int MAX_IMG_VALUE = 3;

    private PostService postService;
    private CommentService commentService;
    private CategoryService categoryService;
    private UserService userService;
    private UserConverter userConverter;

    public DefaultPostLogic(PostService postService,
                            CommentService commentService,
                            CategoryService categoryService, UserService userService,
                            Converter userConverter) {
        this.postService = postService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userConverter =(UserConverter) userConverter;
    }
 @Override
    public void deletePost(Integer postId) {
        postService.deletePost(postId);
    }

    @Override
    public ApplicationUser getUser(Integer id) {
        User userFromDb = userService.findUser(id);
        return  userConverter.convertDbEntityToApplicationEntity(userFromDb);
    }

    @Override
    public void createPost(String title, Integer authorId, String text,
                           String[] categoryName, List<InputStream> imgStream) {
        DbPost newPost = new DbPost();
        int newPostId = 0;
        newPost.setTitle(title);
        newPost.setAuthor(authorId);
        newPost.setText(text);
        postService.save(newPost);
        newPostId = getNewPostId(authorId, text, title);
        for (InputStream img : imgStream) {
            if (counter < MAX_IMG_VALUE) {
                addPicture(newPostId, img);
            }
        }
        for (int i = 0; i < categoryName.length; i++) {
            linkPostAndCatery(i, categoryName, newPostId);
        }

    }

    @Override
    public DbPost getPost(Integer postId) {
        return postService.findPost(postId);
    }

    @Override
    public void updatePost(Integer postId, String title, String text) {
        DbPost post = postService.findPost(postId);
        post.setTitle(title);
        post.setText(text);
        postService.updatePost(post);
    }

    @Override
    public void addComment(Integer author, Integer postId, String commentText) {
        DbComment comment = new DbComment();
        comment.setAuthor(author);
        comment.setIdPost(postId);
        comment.setComment(commentText);
        commentService.createComment(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentService.deleteComment(commentId);
    }

    private void linkPostAndCatery(int pointer, String[] categories, int newPostId) {
        int categoryId = categoryService.returnCategoryIdByName(categories[pointer]);
        postService.linkPostWithcategory(newPostId, categoryId);
    }

    private void addPicture(int postId, InputStream inputStream) {
        postService.addImgToPost(postId, inputStream);
    }

    private Integer getNewPostId(Integer authorId, String text, String title) {
        int postId = 0;
        List<DbPost> dbPosts = postService.findUserPosts(authorId);
        for (DbPost post : dbPosts) {
            if (post.getText().equals(text) && post.getTitle().equals(title)) {
                postId = post.getId();
            }
        }
        return postId;
    }


}
