package by.epam.training.blog.action.editor;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.domain.Role;

public abstract class EditorCommand extends Command {
    public EditorCommand(){
        getAllowRoles().add(Role.EDITOR);
    }
}
