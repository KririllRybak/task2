package by.epam.training.blog.action;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class LogoutCommand extends UserCommand {

    private final Forward FORWARD_LOGIN = new Forward("/");

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        removeAttributes(request);
        request.setAttribute("security", false);
        log.debug(String.format("Session value : \"%s\"", session));
        invalidateSession(session);
        return FORWARD_LOGIN;
    }

    private void invalidateSession(HttpSession session) {
        if (session != null) {
            session.invalidate();
            log.debug("Session was invalidated");
        }
    }

    private void removeAttributes(HttpServletRequest request) {
        request.removeAttribute("login");
        request.removeAttribute("password");
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {

    }

}