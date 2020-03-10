package by.epam.training.blog.action.command_for_any_kind_of_user.profile;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.ProfileLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class SubscriptionCommand extends UserCommand {
    private final  Forward SUBSCRIPTIONS_FORWARD = new Forward("/blog/subscriptions", false);
    private ProfileLogic logic;


    public SubscriptionCommand(ProfileLogic logic ) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        HttpSession session = request.getSession(false);
        ApplicationUser applicationUser = (ApplicationUser) session.getAttribute("authorizedUser");
        List<ApplicationUser> subscriptions = logic.getMySubscriptions(applicationUser.getId());
        SUBSCRIPTIONS_FORWARD.getAttributes().put("subscriptions", subscriptions);
        return SUBSCRIPTIONS_FORWARD;
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
    }
}
