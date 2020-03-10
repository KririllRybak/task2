package by.epam.training.blog.action;


import java.io.Serializable;

public class NavbarPart implements Serializable {
    private String url;
    private String name;

    public NavbarPart(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}