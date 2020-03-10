package by.epam.training.blog.action.command_for_any_kind_of_user.profile;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.ShowUserLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class ShowUserCommand extends UserCommand {
    private final Forward USER_PROFILE_FORWARD = new Forward("/blog/user", false);
    private final String EMPTY_STRING = "";

    private ShowUserLogic logic;
    private HttpSession session;
    private String userLogin;

    public ShowUserCommand(ShowUserLogic logic ) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        log.debug("User login: " + userLogin);
        if (session != null) {
            if (!EMPTY_STRING.equals(userLogin)) {
                ApplicationUser subscriber = (ApplicationUser) session.getAttribute("authorizedUser");
                ApplicationUser user = logic.showUser(userLogin);
                if (request.getParameter("subscription") != null) {
                    followAction(request, subscriber, user);
                }
                selectButtonColour(subscriber, user);
                USER_PROFILE_FORWARD.getAttributes().put("someUser", user);
            }
        }
        return USER_PROFILE_FORWARD;
    }

    private void followAction(HttpServletRequest request, ApplicationUser subscriber,
                              ApplicationUser user) {
        String subscribe = request.getParameter("subscription");
        if ("sub".equals(subscribe)) {
            logic.subscribeOnUser(subscriber.getId(), user.getId());
            user = logic.showUser(userLogin);
        } else {
            logic.unsubscribeOnUser(subscriber.getId(), user.getId());
            user = logic.showUser(userLogin);
        }
    }

    private void selectButtonColour(ApplicationUser subscriber, ApplicationUser user) {
        if (logic.isSigned(subscriber.getId(), user.getId())) {
            USER_PROFILE_FORWARD.getAttributes().put("btn", 0);
        } else {
            USER_PROFILE_FORWARD.getAttributes().put("btn", 1);
        }
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(false);
        userLogin = String.valueOf((String) request.getAttribute("entityId"));
    }
}

