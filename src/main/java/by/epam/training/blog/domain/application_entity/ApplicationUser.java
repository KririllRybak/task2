package by.epam.training.blog.domain.application_entity;

import by.epam.training.blog.domain.Entity;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.db_entity.User;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@ToString
public class ApplicationUser extends Entity implements ApplicationEntity{
    private String login;
    private String password;
    private String email;
    private Date creationStamp;
    private String img;
    private List<ApplicationPost> appPosts = new ArrayList<>();
    private List<User> subscribers = new ArrayList<>();
    private List<ApplicationComment> appComments = new ArrayList<>();
    private Role role;
    private String aboutMe;

    public ApplicationUser() {
    }

    public ApplicationUser(String login, String password, String email, Date creationStamp, String img, List<ApplicationPost> appPosts, List<User> subscribers, List<ApplicationComment> appComments, Role role, String aboutMe) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.creationStamp = creationStamp;
        this.img = img;
        this.appPosts = appPosts;
        this.subscribers = subscribers;
        this.appComments = appComments;
        this.role = role;
        this.aboutMe = aboutMe;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationStamp() {
        return creationStamp;
    }

    public void setCreationStamp(Date creationStamp) {
        this.creationStamp = creationStamp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<ApplicationComment> getAppComments() {
        return appComments;
    }

    public void addAppComment(ApplicationComment appComment) {
        appComments.add(appComment);
    }

    public List<ApplicationPost> getAppPosts() {
        return appPosts;
    }

    public void addAppPost(ApplicationPost appPost) {
        appPosts.add(appPost);
    }
    public List<User> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(User subscriber) {
       subscribers.add(subscriber);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
