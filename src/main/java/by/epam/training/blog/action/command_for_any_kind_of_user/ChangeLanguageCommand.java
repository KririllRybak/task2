package by.epam.training.blog.action.command_for_any_kind_of_user;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand extends UserCommand {
    private static final String COOKIE_LANG = "locale";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String lang = (String) request.getAttribute("entityId");
            session.setAttribute("locale", lang);
            return new Forward("/blog/main");
        }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {

    }
}

