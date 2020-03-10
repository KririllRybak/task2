package by.epam.training.blog.domain;

import java.util.Arrays;
import java.util.List;

public enum Role {
    EDITOR(0,"редактор"),
    USER(1,"пользователь"),
    ADMIN(2,"администратор");

    private String name;
    private int roleId;

    private Role(int roleId,String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public int getRoleId(){
        return roleId;
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }

    public static List<Role> getRoles(){
        List roles = Arrays.asList(Role.values());
        return roles;
    }
}
