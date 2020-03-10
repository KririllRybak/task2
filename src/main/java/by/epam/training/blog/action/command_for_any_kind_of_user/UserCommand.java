package by.epam.training.blog.action.command_for_any_kind_of_user;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.domain.Role;

import java.util.Arrays;

public abstract class UserCommand extends Command {
    public UserCommand() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
