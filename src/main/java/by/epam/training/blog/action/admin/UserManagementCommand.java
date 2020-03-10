package by.epam.training.blog.action.admin;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.action.LogoutCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserManagementCommand extends AdminCommand {
    private static final String DELETE_COMMAND = "/deleteUser";
    private static final String CHANGE_ROLE_COMMAND = "/changeRole";
    private final Forward USERS_FORWARD = new Forward("/blog/users");

    private UsersLogic logic;

    private LogoutCommand logout;
    private Command command;
    private String commandName;

    public UserManagementCommand(UsersLogic usersLogic) {
        this.logic = usersLogic ;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (DELETE_COMMAND.equals(commandName)) {
            deleteUser(request, response);
        }
        if (CHANGE_ROLE_COMMAND.equals(commandName)) {
            changeRole(request, response);
        }
        return USERS_FORWARD;
    }

    protected void initParams(HttpServletRequest request) {
        command = (Command) request.getAttribute("command");
        commandName = command.getName();
        logout = new LogoutCommand();
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        logic.deleteUser(userId);
        HttpSession session = request.getSession(false);
        ApplicationUser user = (ApplicationUser) session.getAttribute("authorizedUser");
        if (userId == user.getId()) {
            logout.exec(request, response);
        }
    }

    private void changeRole(HttpServletRequest request, HttpServletResponse response) {
        String userIdString = request.getParameter("userId");
        Integer userId = Integer.valueOf(userIdString);
        Integer role = Integer.valueOf(request.getParameter("role"));
        logic.changeRole(role, userId);
    }
}
