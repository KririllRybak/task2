package by.epam.training.blog.action.command_for_any_kind_of_user.sort;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.logic.api.SearchLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Log4j2
public class SearchCommand extends UserCommand {
    private final Forward FORWARD_SEARCH = new Forward("/blog/search", false);
    private SearchLogic logic;

    HttpSession session;
    Set<ApplicationPost> posts;

    public SearchCommand(SearchLogic logic ) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (session != null) {
            removeOldAttributes(request);
            String content = request.getParameter("content");
            content = content.trim();
            log.debug("Content search: " + content);
            posts = logic.showFoundPosts(content);
            checkPost();
        }
        return FORWARD_SEARCH;
    }

    private void checkPost() {
        if (posts != null && !posts.isEmpty()) {
            session.setAttribute("foundPosts", posts);
        } else {
            session.setAttribute("noContent", "nothing found for your request");
        }
    }

    private void removeOldAttributes(HttpServletRequest request) {
        if (session.getAttribute("foundPosts") != null) {
            session.removeAttribute("foundPosts");
        }
        if (session.getAttribute("noContent") != null) {
            session.removeAttribute("noContent");
        }
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(false);
    }
}
