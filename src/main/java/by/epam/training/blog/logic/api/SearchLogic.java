package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.application_entity.ApplicationPost;

import java.util.Set;

public interface SearchLogic   {
     Set<ApplicationPost> showFoundPosts(String request);
}
