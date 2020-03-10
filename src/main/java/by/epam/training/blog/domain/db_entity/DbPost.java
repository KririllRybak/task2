package by.epam.training.blog.domain.db_entity;

import by.epam.training.blog.domain.Entity;
import lombok.ToString;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

@ToString
public class DbPost extends Entity implements DbEntity {
    private String title;
    private Integer author;
    private String text;
    private Date creationStamp;

    public DbPost() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationStamp() {
        return creationStamp;
    }

    public void setCreationStamp(Date creationStamp) {
        this.creationStamp = creationStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbPost)) return false;
        if (!super.equals(o)) return false;
        DbPost post = (DbPost) o;
        return title.equals(post.title) &&
                author.equals(post.author) &&
                text.equals(post.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, text);
    }
}
