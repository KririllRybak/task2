package by.epam.training.blog.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardToRegistrationPageCommand extends Command {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/blog/registration",false);
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
    }
}
