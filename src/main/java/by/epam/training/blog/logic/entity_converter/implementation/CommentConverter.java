package by.epam.training.blog.logic.entity_converter.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationComment;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.service.api.PostService;
import by.epam.training.blog.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CommentConverter implements Converter<DbComment, ApplicationComment> {

    private PostService postService;
    private UserService userService;

    public CommentConverter(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public ApplicationComment convertDbEntityToApplicationEntity(DbComment dbComment) {
        ApplicationComment appComment = new ApplicationComment();
        appComment.setId(dbComment.getId());
        appComment.setDbPost(postService.findPost(dbComment.getIdPost()));
        appComment.setAuthor(userService.findUser(dbComment.getAuthor()));
        appComment.setComment(dbComment.getComment());
        return appComment;
    }
}
