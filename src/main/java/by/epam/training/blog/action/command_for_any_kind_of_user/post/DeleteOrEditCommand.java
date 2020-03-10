package by.epam.training.blog.action.command_for_any_kind_of_user.post;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.logic.api.PostLogic;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class DeleteOrEditCommand extends UserCommand {
    private static final String EDIT = "update";
    private static final String DELETE = "delete";
    private static final Forward PROFILE_FORWARD = new Forward("/blog/profile", false);
    private final Forward FORWARD_TO_POST_EDITOR = new Forward("/blog/postedit", false);
    private final Forward MAIN_FORWARD = new Forward("/blog/main");

    private PostLogic logic;

    private HttpSession session;
    private String editRequest;
    private ApplicationUser appUser;
    private Integer postId;

    public DeleteOrEditCommand(PostLogic logic) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (EDIT.equals(editRequest)) {
            editPost(request, response);
            return FORWARD_TO_POST_EDITOR;
        }
        if (DELETE.equals(editRequest)) {
            deletePost();
            if (appUser.getRole() == Role.EDITOR) {
                return MAIN_FORWARD;
            }
        }
        PROFILE_FORWARD.getAttributes().put("edit", true);
        return PROFILE_FORWARD;
    }

    protected void initParams(HttpServletRequest request) {
        session = request.getSession(false);
        editRequest = request.getParameter("edit");
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
        if (NumberUtils.isCreatable(request.getParameter("postId"))) {
            postId = Integer.valueOf(request.getParameter("postId"));
        } else {
            postId = null;
        }
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbPost post = null;
        if (postId != null) {
            post = logic.getPost(postId);
        } else {
            FORWARD_TO_POST_EDITOR.goTo404(request, response);
        }
        FORWARD_TO_POST_EDITOR.getAttributes().put("post", post);
    }

    private void deletePost() {
        logic.deletePost(postId);
        session.setAttribute("authorizedUser", logic.getUser(appUser.getId()));
    }
}
