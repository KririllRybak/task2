package by.epam.training.blog.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

@Log4j2
public class ContextListener implements ServletContextListener {
    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("Context listener is alive");
        ResourceBundle build = ResourceBundle.getBundle("build");
        String number = build.getString("number");
        String time = build.getString("time");
        log.debug("BUILD NUMBER "+ number);
        log.debug("BUILD TIME "+ time);
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("number", number);
        context.setAttribute("time",time);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
