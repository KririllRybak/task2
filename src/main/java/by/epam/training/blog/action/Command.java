package by.epam.training.blog.action;

import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class Command {
    private Set<Role> allowRoles = new HashSet<>();
    private ApplicationUser authorizedUser;
    private String name;

    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    public ApplicationUser getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(ApplicationUser authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public Command.Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public static class Forward {
        private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
        private String forward;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        public Forward(String forward, boolean redirect) {
            this.forward = forward;
            this.redirect = redirect;
        }

        public Forward(String forward) {
            this(forward, true);
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(boolean redirect) {
            this.redirect = redirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void goTo404(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            request.getServletContext().getRequestDispatcher(ERROR_JSP).forward(request, response);
        }

    }

    abstract protected void initParams(HttpServletRequest request) throws IOException, ServletException;
}