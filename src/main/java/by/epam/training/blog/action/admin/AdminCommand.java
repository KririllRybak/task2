package by.epam.training.blog.action.admin;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.domain.Role;

public abstract class AdminCommand extends Command {
    public AdminCommand() {
        getAllowRoles().add(Role.ADMIN);
    }
}
