package by.epam.training.blog.domain.application_entity;

import by.epam.training.blog.domain.Entity;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.domain.db_entity.User;
import lombok.ToString;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@ToString
public class ApplicationPost extends Entity implements ApplicationEntity {

    private List<DbCategory> categories = new ArrayList<>();
    private List<ApplicationComment> comments = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    private String title;
    private User author;
    private String text;
    private Date creationStamp;

    public ApplicationPost() {
    }

    public ApplicationPost(List<DbCategory> categories, List<ApplicationComment> comments, List<String> images, String title, User author, String text, Date creationStamp, Date changeDate) {
        this.categories = categories;
        this.comments = comments;
        this.images = images;
        this.title = title;
        this.author = author;
        this.text = text;
        this.creationStamp = creationStamp;
    }

    public List<DbCategory> getCategories() {
        return categories;
    }

    public void addCategory(DbCategory category) {
        categories.add(category);
    }

    public List<ApplicationComment> getComments() {
        return comments;
    }

    public void addComment(ApplicationComment comment) {
        comments.add(comment);
    }

    public List<String> getImages() {
        return images;
    }

    public void addImage(String image) {
      images.add( image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
}
