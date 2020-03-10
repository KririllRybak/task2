package by.epam.training.blog.logic.entity_converter.implementation;

import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationComment;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.service.api.CategoryService;
import by.epam.training.blog.service.api.CommentService;
import by.epam.training.blog.service.api.PostService;
import by.epam.training.blog.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class UserConverter implements Converter<User, ApplicationUser> {
    private Converter postConverter;
    private Converter commentConverter;
    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    public UserConverter(Converter postConverter,Converter commentConverter,
                         UserService userService, PostService postService,
                         CommentService commentService) {
        this.postConverter = postConverter;
        this.commentConverter = commentConverter;
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public ApplicationUser convertDbEntityToApplicationEntity(User dbUser) {
        ApplicationUser appUser = new ApplicationUser();
        appUser.setId(dbUser.getId());
        appUser.setLogin(dbUser.getLogin());
        appUser.setEmail(dbUser.getEmail());
        appUser.setPassword(dbUser.getPassword());
        appUser.setCreationStamp(dbUser.getCreationStamp());
        appUser.setImg(dbUser.getImg());
        appUser.setAboutMe(dbUser.getAboutMe());
        appUser.setRole(Role.getByIdentity(dbUser.getRole()));
        List<DbPost> dbPosts = postService.findUserPosts(dbUser.getId());
        for (DbPost dbPost : dbPosts) {
            ApplicationPost appPost = (ApplicationPost)
                    postConverter.convertDbEntityToApplicationEntity(dbPost);
            appUser.addAppPost(appPost);
        }
        List<DbComment> dbComments = commentService.getCommentForUser(dbUser.getId());
        for (DbComment dbComment : dbComments) {
            ApplicationComment appComment = (ApplicationComment)
                    commentConverter.convertDbEntityToApplicationEntity(dbComment);
            appUser.addAppComment(appComment);
        }
        List<User> dbUsers = userService.showUserSubscribers(dbUser.getId());
        for (User sub : dbUsers) {
            appUser.addSubscriber(sub);

        }

        return appUser;
    }
}
