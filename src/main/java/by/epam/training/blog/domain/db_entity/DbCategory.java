package by.epam.training.blog.domain.db_entity;

import by.epam.training.blog.domain.Entity;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class DbCategory extends Entity implements DbEntity {
    private String name;
    private List<Integer> postIdCollection = new ArrayList<>();

    public DbCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPostIdCollection() {
        return postIdCollection;
    }

    public void addPost(Integer postId) {
        postIdCollection.add(postId);
    }
}
