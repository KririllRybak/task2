package by.epam.training.blog.action.command_for_any_kind_of_user.profile;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.logic.api.ProfileLogic;
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

@Log4j2
public class EditProfileCommand extends UserCommand {
    private final Forward PROFILE_FORWARD = new Forward("/blog/profile");

    private String email;
    private String password;
    private String confirm;
    private String aboutMe;
    HttpSession session;
    ApplicationUser appUser;
    private ProfileLogic logic;


    public EditProfileCommand(ProfileLogic logic) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (request.getParameter("file") != null) {
            addAvatar(request);
        }
        if (email == null || password == null || confirm == null || aboutMe == null) {
            session.setAttribute("authorizedUser", appUser);
        } else {
            updateUser();
        }
        return PROFILE_FORWARD;
    }

    private void addAvatar(HttpServletRequest request) throws IOException, ServletException {
        log.debug("Process upload user image");
        Part filePart = request.getPart("file");
        if (filePart != null) {
            InputStream inputStream = filePart.getInputStream();
            appUser = logic.uploadImg(appUser.getId(), inputStream);
            session.setAttribute("icon", appUser.getImg());
        }
    }

    private void updateUser() {
        User user = logic.collectUpdatedUser(email, password, confirm, aboutMe, appUser);
        if (user.getPassword() == null) {
            session.setAttribute("incorrectConfirm", "Пароль и подтверждение не совпадают");
        } else {
            session.setAttribute("authorizedUser", logic.updateAuthorizedUser(user, appUser));
            logic.updateUser(user);
        }
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        email = request.getParameter("email");
        password = request.getParameter("password");
        confirm = request.getParameter("confirm");
        aboutMe = request.getParameter("aboutMe");
        session = request.getSession(false);
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
    }
}
