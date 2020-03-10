package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.api.SearchLogic;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.PostConverter;
import by.epam.training.blog.service.api.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class DefaultSearchLogic implements SearchLogic {

    private PostService postService;
    private PostConverter postConverter;

    public DefaultSearchLogic(Converter postConverter,
                              PostService postService){
        this.postConverter = (PostConverter) postConverter;
        this.postService = postService;
    }

    public Set<ApplicationPost> showFoundPosts(String request) {
        List<DbPost> posts = postService.findPostsByContent(request);
        Set<ApplicationPost> appPost = new HashSet<>();
        for (DbPost post : posts) {
            appPost.add(postConverter.convertDbEntityToApplicationEntity(post));
        }
        return appPost;
    }
}
