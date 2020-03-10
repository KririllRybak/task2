package by.epam.training.blog.action.admin;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.VisitEntry;
import by.epam.training.blog.logic.api.UsersLogic;
import by.epam.training.blog.logic.api.VisitLogLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j2
public class UserListCommand extends AdminCommand {
    private final Forward USERS_FORWARD = new Forward("/blog/users", false);

    private UsersLogic logic;
    private VisitLogLogic logLogic;
    private HttpSession session;
    private List<Role> roles;

    public UserListCommand(UsersLogic usersLogic,VisitLogLogic visitLogLogic) {
        this.logic = usersLogic;
        this.logLogic = visitLogLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        List<ApplicationUser> allUsers = logic.getAllUsers();
            List<VisitEntry> entries = logLogic.getVisitLog();
            session.setAttribute("entries", entries);
            USERS_FORWARD.getAttributes().put("roles", roles);
            USERS_FORWARD.getAttributes().put("users", allUsers);
            return USERS_FORWARD;
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException {
        session = request.getSession(false);
        roles = Role.getRoles();
    }
}
