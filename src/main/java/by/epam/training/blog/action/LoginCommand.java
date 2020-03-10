package by.epam.training.blog.action;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.LoginLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class LoginCommand extends Command {

    private static Map<Role, List<NavbarPart>> navbar = new ConcurrentHashMap<>();

    static {
        navbar.put(Role.ADMIN, new ArrayList<>(Arrays.asList(
                new NavbarPart("/blog/users", "users"),
                new NavbarPart("/blog/profile", "profile"),
                new NavbarPart("/blog/logout", "logout"))));

        navbar.put(Role.EDITOR, new ArrayList<>(Arrays.asList(
                new NavbarPart("/blog/categories", "category_management"),
                new NavbarPart("/blog/profile", "profile"),
                new NavbarPart("/blog/logout", "logout"))));
        navbar.put(Role.USER, new ArrayList<>(Arrays.asList(
                new NavbarPart("/blog/profile", "profile"),
                new NavbarPart("/blog/logout", "logout"))));
    }

    private final Forward FORWARD_MAIN = new Forward("/blog/main");
    private LoginLogic loginLogic;
    private HttpSession session;


    public LoginCommand(LoginLogic loginLogic) {
        this.loginLogic =  loginLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        Optional<Boolean> isAuthenticated = Optional.ofNullable((Boolean) request.getAttribute("security"));
        if (isAuthenticated.isPresent() && isAuthenticated.get() == true) {
            if (!checkUser(request)) {
                checkMessages(request);
                String login = (String) request.getParameter("login");
                String password = (String) request.getParameter("password");
                ApplicationUser user = loginLogic.getUser(login, password);
                checkJSESSIONID(request, response, session);
                setSessionAttribute(user, login, request);
            }
            return FORWARD_MAIN;
        } else {
            return null;
        }
    }

    private void setSessionAttribute(ApplicationUser user, String login, HttpServletRequest request) {
        session.setAttribute("locale", "en");
        log.info(String.format("Session was created session id: \"%s\"", session.getId()));
        session.setAttribute("authorizedUser", user);
        session.setAttribute("menu", navbar.get(user.getRole()));
        session.setAttribute("icon", user.getImg());
        log.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
    }


    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(true);
    }

    private void checkJSESSIONID(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session) {
        if (request.getParameter("JSESSIONID") != null) {
            Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
            response.addCookie(userCookie);
        } else {
            String sessionId = session.getId();
            Cookie userCookie = new Cookie("JSESSIONID", sessionId);
            response.addCookie(userCookie);
        }
    }

    private void checkMessages(HttpServletRequest request) {
        if (request.getParameter("wrongData") != null) {
            request.removeAttribute("wrongData");
        }
        if (request.getAttribute("successfulRegistration") != null) {
            request.removeAttribute("successfulRegistration");
        }
    }

    private boolean checkUser(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("authorizedUser") != null) {
                return true;
            }
        }
        return false;
    }


}
