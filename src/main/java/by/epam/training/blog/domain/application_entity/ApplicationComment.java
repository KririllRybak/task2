package by.epam.training.blog.domain.application_entity;

import by.epam.training.blog.domain.Entity;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.domain.db_entity.User;
import lombok.ToString;

@ToString
public class ApplicationComment extends Entity implements ApplicationEntity{
    private User author;
    private DbPost appPost;
    private String comment;

    public ApplicationComment() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public DbPost getDbPost() {
        return appPost;
    }

    public void setDbPost(DbPost appPost) {
        this.appPost = appPost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
