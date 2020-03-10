package by.epam.training.blog.domain.db_entity;

import by.epam.training.blog.domain.Entity;
import lombok.ToString;

@ToString
public class DbComment extends Entity implements DbEntity  {
    private int author;
    private int post;
    private String comment;

    public DbComment() {
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getIdPost() {
        return post;
    }

    public void setIdPost(int appPost) {
        this.post = appPost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
