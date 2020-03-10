package by.epam.training.blog.action.command_for_any_kind_of_user.post;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.PostLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CreatePostCommand extends UserCommand {
    private final Forward FORWARD_MAIN = new Forward("/blog/main");
    private static final String EMPTY_STRING = "";

    private PostLogic logic;

    private String[] categoryName;
    private String title;
    private String text;
    private HttpSession session;
    private ApplicationUser appUser;
    private List<Part> parts;
    private List<InputStream> imgs;

    public CreatePostCommand(PostLogic postLogic) {
        this.logic = postLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        imgPartToInputStream();
        if (validData()) {
            createPost(appUser, session, imgs);
            return FORWARD_MAIN;
        } else {
            return FORWARD_MAIN;
        }
    }

    private boolean validData() {
        boolean result = (!(categoryName == null)
                && !EMPTY_STRING.equals(text)
                && !EMPTY_STRING.equals(title));
        return result;
    }

    private void createPost(ApplicationUser appUser, HttpSession session,
                            List<InputStream> imgs) {
        logic.createPost(title, appUser.getId(), text, categoryName, imgs);
        ApplicationUser updateUpplicationUser = null;
            updateUpplicationUser = logic.getUser(appUser.getId());
        session.setAttribute("authorizedUser", updateUpplicationUser);
    }

    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        categoryName = request.getParameterValues("selectCategories");
        title = request.getParameter("title");
        text = request.getParameter("text");
        session = request.getSession(false);
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
        parts = (List<Part>) request.getParts();
        imgs = new ArrayList<>();
    }

    private void imgPartToInputStream() throws IOException {
        for (Part part : parts) {
            if (part.getName().equalsIgnoreCase("imgs")) {
                imgs.add(part.getInputStream());
            }
        }
    }
}