package by.epam.training.blog.listener;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.VisitLogLogic;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

@Log4j2
public class SessionListener implements HttpSessionAttributeListener {
    private final static String USER = "authorizedUser";

    private VisitLogLogic logLogic ;

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logLogic =  (VisitLogLogic) AppContext.context.getBean("visitLogLogic");
        HttpSession session = event.getSession();
        String sessionId = session.getId();
        String attributeName = event.getName();
        Object attributeValue = event.getValue();
        if (USER.equals(attributeName)) {
            ApplicationUser appUser = (ApplicationUser) attributeValue;
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            String login = appUser.getLogin();
            try {
                logLogic.registerEntry(login, sessionId, date);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        String data = "";
        File file = new File("/META-INF/MANIFEST.MF");
        try (Scanner scan = new Scanner(file)) {
            scan.useDelimiter("\r\n");
            while (scan.hasNext()) {
                String[] substring = scan.next().split(" ");
                data += substring;
            }
        } catch (FileNotFoundException e) {
            log.error("File is not exist.");
        }
        session.setAttribute("version", data);

        log.debug(" listener works correctly ");


    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        String sessionId = session.getId();
        String attributeName = event.getName();
        Object attributeValue = event.getValue();
        if (USER.equals(attributeName)) {
            ApplicationUser appUser = (ApplicationUser) attributeValue;
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            String login = appUser.getLogin();
            try {
                logLogic.registerOut(login, sessionId, date);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
