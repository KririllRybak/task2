package by.epam.training.blog.filter;

import by.epam.training.blog.action.*;
import by.epam.training.blog.action.admin.UserListCommand;
import by.epam.training.blog.action.admin.UserManagementCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.ChangeLanguageCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.MainCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.post.*;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.EditProfileCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.ProfileCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.ShowUserCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.SubscriptionCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.sort.CategoryCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.sort.SearchCommand;
import by.epam.training.blog.action.editor.CategoryManagementCommand;
import by.epam.training.blog.action.editor.ForwardToCategories;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class CommandUrlFilter implements Filter {

    private static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
    private static Map<String, Class<? extends Command>> actions = new ConcurrentHashMap<>();
    private static final int URI_PREFIX = 5;

    static {
        actions.put("/main", MainCommand.class);
        actions.put("/registrationPage", ForwardToRegistrationPageCommand.class);
        actions.put("/registration", RegistrationCommand.class);
        actions.put("/login", LoginCommand.class);
        actions.put("/profile", ProfileCommand.class);
        actions.put("/logout", LogoutCommand.class);
        actions.put("/category", CategoryCommand.class);
        actions.put("/search", SearchCommand.class);
        actions.put("/updateProfile", EditProfileCommand.class);
        actions.put("/user", ShowUserCommand.class);
        actions.put("/postEditor", ForwardToPostCreation.class);
        actions.put("/createPost", CreatePostCommand.class);
        actions.put("/postChanges", DeleteOrEditCommand.class);
        actions.put("/editPost", EditPostCommand.class);
        actions.put("/users", UserListCommand.class);
        actions.put("/deleteUser", UserManagementCommand.class);
        actions.put("/changeRole", UserManagementCommand.class);
        actions.put("/categories", ForwardToCategories.class);
        actions.put("/createCategory", CategoryManagementCommand.class);
        actions.put("/deleteCategory", CategoryManagementCommand.class);
        actions.put("/addComment", CommentManagementCommand.class);
        actions.put("/deleteComment", CommentManagementCommand.class);
        actions.put("/subscriptions", SubscriptionCommand.class);
        actions.put("/changeLang", ChangeLanguageCommand.class);
    }

    private String commandName;
    private Set<Role> allowRoles;
    private Command command;
    private ApplicationUser user = null;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (request instanceof HttpServletRequest) {
            findCommandName(httpRequest, request);
            Class<? extends Command> actionClass = actions.get(commandName);
            try {
                setCommandToRequest(httpRequest, actionClass);
                HttpSession session = httpRequest.getSession(false);
                if (session != null) {
                    setAuthorizedUserToCommand(command, session);
                }
                if (canExecute(user)) {
                    chain.doFilter(request, response);
                } else {
                    if (session != null) {
                        request.getServletContext().getRequestDispatcher(ERROR_JSP).forward(request, response);
                    } else {
                        request.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                log.error("It is impossible to use HTTP filter");
                forwardTo(request, response);
            }
        } else {
            request.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }
    }

    private void forwardTo(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        if (((HttpServletRequest) request).getSession(false) == null ||
                ((HttpServletRequest) request).getSession(false).getAttribute("authorizedUser") == null) {
            if (request.getAttribute("successfulRegistration") != null) {
                request.removeAttribute("wrongData");
            }
            request.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
        } else {
            request.getServletContext().getRequestDispatcher(ERROR_JSP).forward(request, response);
        }
    }

    private boolean canExecute(ApplicationUser user) {
        boolean canExecute = allowRoles.isEmpty();
        if (user != null) {
            canExecute = canExecute || allowRoles.contains(user.getRole());
        }
        return canExecute;
    }

    private void setAuthorizedUserToCommand(Command command, HttpSession session) {
        user = (ApplicationUser) session.getAttribute("authorizedUser");
        command.setAuthorizedUser(user);
    }

    private void setCommandToRequest(HttpServletRequest httpRequest, Class<? extends Command> actionClass) throws IllegalAccessException, InstantiationException {
        command = AppContext.context.getBean(actionClass);
        command.setName(commandName);
        allowRoles = command.getAllowRoles();
        httpRequest.setAttribute("command", command);
    }

    private void findCommandName(HttpServletRequest httpRequest, ServletRequest request) {
        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();
        log.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
        int beginCommand = contextPath.length() + URI_PREFIX;
        int endCommand = uri.length();
        if (endCommand >= 0) {
            commandName = uri.substring(beginCommand, endCommand);
        } else {
            commandName = uri.substring(beginCommand);
        }
        int lastIndexSlash = commandName.lastIndexOf("/");
        if (lastIndexSlash != 0 && lastIndexSlash > 0) {
            commandName = commandName.substring(0, lastIndexSlash);
            int urlLastIndexSlash = uri.lastIndexOf("/");
            String entityId = uri.substring(urlLastIndexSlash + 1);
            request.setAttribute("entityId", entityId);
        }
        log.debug(String.format("Command name \"%s\"", commandName));
    }


    @Override
    public void destroy() {

    }
}
