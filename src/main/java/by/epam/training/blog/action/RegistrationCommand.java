package by.epam.training.blog.action;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.logic.api.RegistrationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand extends Command {
    private final Forward FORWARD_TO_LOGIN = new Forward("/blog/login", false);
    private final Forward FORWARD_TO_REGISTRATION = new Forward("/blog/registration", false);
    private final static String SUCCESS = "successfulRegistration";
    private final static String SMT_WRONG = "smtWrong";
    private final static String COMPARE_FAILED = "compareFailed";
    private final static String SHORT_PASSWORD = "shortPass";
    private final static String LOGIN_EXISTS = "existingLogin";
    private static Map<String, String> messages = new HashMap<>();

    static {
        messages.put(SUCCESS, "You have successfully registered.");
        messages.put(SMT_WRONG, "Something wrong, try later.");
        messages.put(COMPARE_FAILED, "Password confirmation did not match");
        messages.put(SHORT_PASSWORD, "Password should be longer than 4 symbols.");
        messages.put(LOGIN_EXISTS, "Login is already exists.");
    }

    private RegistrationLogic logic;
    private String login;
    private String pass;
    private String confirmedPass;
    private String email;

    public RegistrationCommand(RegistrationLogic logic) {
        this.logic = logic;;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (checkLoginEmptiness(request)){
            return FORWARD_TO_REGISTRATION;
        }
        if (logic.checkForLogin(login)) {
            if (logic.checkPasswordValidation(pass)) {
                if (logic.comparePasswords(pass, confirmedPass)) {
                    if (logic.registerUser(login, pass, email, request.getContextPath())) {
                        confirmRegistrationSuccess(request);
                        return FORWARD_TO_LOGIN;
                    } else {
                        request.setAttribute(SMT_WRONG,
                                messages.get(SMT_WRONG));
                        return FORWARD_TO_LOGIN;
                    }
                } else {
                    request.setAttribute(COMPARE_FAILED,
                            messages.get(COMPARE_FAILED));
                    return FORWARD_TO_REGISTRATION;
                }
            } else {
                request.setAttribute(SHORT_PASSWORD,
                        messages.get(SHORT_PASSWORD));
                return FORWARD_TO_REGISTRATION;
            }
        } else {
            request.setAttribute(LOGIN_EXISTS,
                    messages.get(LOGIN_EXISTS));
            return FORWARD_TO_REGISTRATION;
        }
    }

    private void confirmRegistrationSuccess(HttpServletRequest request) {
        if (request.getAttribute("wrongData") != null) {
            request.removeAttribute("wrongData");
        }
        request.setAttribute(SUCCESS,
                messages.get(SUCCESS));
    }

    private boolean checkLoginEmptiness(HttpServletRequest request) {
        if (login.length() == 0 || pass.length() == 0 ||
                confirmedPass.length() == 0 || email.length() == 0) {
            request.setAttribute("emptyField", "Fill in all the fields.");
           return true;
        }
        return false;
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        login = (String) request.getParameter("login");
        login = login.trim();
        pass = (String) request.getParameter("password");
        confirmedPass = (String) request.getParameter("password_confirm");
        email = (String) request.getParameter("email");
    }
}
