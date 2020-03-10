package by.epam.training.blog.action.command_for_any_kind_of_user.post;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.logic.api.MainLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ForwardToPostCreation extends UserCommand {
    private final Forward CREATE_CATEGORY_FORWARD =  new Forward("/blog/createpost",false);
    private MainLogic mainLogic;
    private List<DbCategory> categories;
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        CREATE_CATEGORY_FORWARD.getAttributes().put("categories",categories);
        return CREATE_CATEGORY_FORWARD;
    }

    public ForwardToPostCreation(MainLogic logic ) {
        this.mainLogic = logic;
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        categories = mainLogic.getCategory();
    }
}
