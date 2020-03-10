package by.epam.training.blog.filter;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.LoginLogic;
import by.epam.training.blog.logic.implementation.DefaultLoginLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class SecurityFilter implements Filter {

    private LoginLogic loginLogic;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        loginLogic = (LoginLogic) AppContext.context.getBean("loginLogic");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");

        HttpSession session = request.getSession(false);

        if (session != null) {
            ApplicationUser user = (ApplicationUser) session.getAttribute("authorizedUser");
            if (user != null) {
                request.setAttribute("security", true);
                log.debug("SecurityFilter: session id " + session.getId());
                Command command = (Command) request.getAttribute("command");
                command.setAuthorizedUser(user);
            } else {
              checkLogin(request,login,password);
            }
        } else {
            checkLogin(request,login,password);
        }
        chain.doFilter(request, response);

    }

    private void checkLogin(HttpServletRequest request,String login,String password){
        if (loginLogic.checkAuthorizationData(login, password)) {
            request.setAttribute("security", true);
        } else {
            request.setAttribute("wrongData", "Incorrect login or password.");
            request.setAttribute("security", false);
        }
    }

    @Override
    public void destroy() {

    }
}
