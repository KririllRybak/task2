package by.epam.training.blog.logic.entity_converter.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationComment;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.CommentService;
import by.epam.training.blog.service.api.PostService;
import by.epam.training.blog.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class PostConverter implements Converter<DbPost, ApplicationPost> {

    private Converter commentConverter;
    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private CommentService commentService;

    public PostConverter(Converter commentConverter, UserService userService,
                         PostService postService, CategoryService categoryService,
                         CommentService commentService) {
        this.commentConverter = commentConverter;
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @Override
    public ApplicationPost convertDbEntityToApplicationEntity(DbPost dbPost) {
        ApplicationPost appPost = new ApplicationPost();
        appPost.setId(dbPost.getId());
        appPost.setTitle(dbPost.getTitle());
        appPost.setText(dbPost.getText());
        appPost.setCreationStamp(dbPost.getCreationStamp());
        appPost.setAuthor(userService.findUser(dbPost.getAuthor()));
        List<String> imgs = postService.showPostImg(dbPost.getId());
        for (String img : imgs) {
            appPost.addImage(img);
        }
        List<DbComment> dbComments =
                commentService.getCommentForPost(dbPost.getId());
        for (DbComment dbComment : dbComments) {
            ApplicationComment appComment = (ApplicationComment) commentConverter.
                    convertDbEntityToApplicationEntity(dbComment);
            appPost.addComment(appComment);
        }
        List<DbCategory> dbCategories =categoryService.
                showPostCategories(dbPost.getId());
        for (DbCategory dbCategory : dbCategories) {
            appPost.addCategory(dbCategory);
        }
        return appPost;
    }
}
