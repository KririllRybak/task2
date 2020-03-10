package by.epam.training.blog.domain.application_entity;

import by.epam.training.blog.domain.Entity;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
public class ApplicationCategory extends Entity implements ApplicationEntity {
    private String name;
    private List<ApplicationPost> appPosts = new ArrayList<>();

    public ApplicationCategory() {
    }

    public List<ApplicationPost> getAppPosts() {
        return appPosts;
    }

    public void addPost(ApplicationPost applicationPost){
        appPosts.add(applicationPost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
