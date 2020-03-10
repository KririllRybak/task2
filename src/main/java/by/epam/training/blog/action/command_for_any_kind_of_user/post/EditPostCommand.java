package by.epam.training.blog.action.command_for_any_kind_of_user.post;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.logic.api.PostLogic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class EditPostCommand extends UserCommand {
    private final Forward PROFILE_FORWARD = new Forward("/blog/profile");
    private static final String EMPTY_STRING = "";
    private static final Forward MAIN_FORWARD = new Forward("/blog/main");

    private PostLogic logic;

    private HttpSession session;
    private ApplicationUser appUser;
    private String postIdString;
    private Integer postId;
    private String title;
    private String text;

    public EditPostCommand(PostLogic postLogic) {
        this.logic = postLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (postId != null) {
            logic.updatePost(postId, title, text);
        }
        session.setAttribute("authorizedUser", logic.getUser(appUser.getId()));
        PROFILE_FORWARD.getAttributes().put("edit", true);
        if (appUser.getRole() == Role.EDITOR) {
            return MAIN_FORWARD;
        }
        return PROFILE_FORWARD;
    }

    protected void initParams(HttpServletRequest request) {
        session = request.getSession(false);
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
        postIdString = request.getParameter("postId");
        postId = null;
        if (postIdString != null || !EMPTY_STRING.equals(postIdString)) {
            postId = Integer.valueOf(postIdString);
        }
        title = request.getParameter("title");
        text = request.getParameter("text");
    }
}
