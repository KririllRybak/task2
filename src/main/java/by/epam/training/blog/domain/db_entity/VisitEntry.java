package by.epam.training.blog.domain.db_entity;

import by.epam.training.blog.domain.Entity;

public class VisitEntry extends Entity {
    private String login;
    private String enter;
    private String out;

    public VisitEntry() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
