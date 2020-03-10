package by.epam.training.blog.action.command_for_any_kind_of_user.post;

import by.epam.training.blog.action.Command;
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
import java.io.IOException;
@Log4j2
public class CommentManagementCommand extends UserCommand {
    private static final String CREATE_COMMENT = "/addComment";
    private static final String DELETE_COMMENT = "/deleteComment";
    private static final String EMPTY_STRING = "";
    private final Forward MAIN_FORWARD = new Forward("/blog/main");

    private PostLogic logic;

    private HttpSession session;
    private ApplicationUser appUser;
    private Command command;
    private String commandName;

    public CommentManagementCommand( PostLogic postLogic) {
        this.logic = postLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (CREATE_COMMENT.equals(commandName)) {
            createComment(request, appUser, session);
        }
        if (DELETE_COMMENT.equals(commandName)) {
            deleteComment(request, appUser, session);
        }
        return MAIN_FORWARD;
    }

    protected void initParams(HttpServletRequest request) {
        session = request.getSession(false);
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
        command = (Command) request.getAttribute("command");
        commandName = command.getName();
    }


    private void updateUser(ApplicationUser appUser, HttpSession session) {
        ApplicationUser updatableUser = logic.getUser(appUser.getId());
        session.setAttribute("authorizedUser", updatableUser);
    }

    private void deleteComment(HttpServletRequest request, ApplicationUser appUser,
                               HttpSession session) {
        String commentIdString = request.getParameter("commentId");
        Integer commentId = Integer.valueOf(commentIdString);
        logic.deleteComment(commentId);
        updateUser(appUser, session);
    }

    private void createComment(HttpServletRequest request, ApplicationUser appUser,
                               HttpSession session) {
        String comment = request.getParameter("comment");
        String postIdString = request.getParameter("postId");
        Integer postId = Integer.valueOf(postIdString);
        if (!EMPTY_STRING.equals(comment)) {
            logic.addComment(appUser.getId(), postId, comment);
            updateUser(appUser, session);
        }
    }
}
