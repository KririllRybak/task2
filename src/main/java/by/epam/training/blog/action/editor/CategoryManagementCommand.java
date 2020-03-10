package by.epam.training.blog.action.editor;

import by.epam.training.blog.action.Command;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.logic.api.CategoryLogic;
import by.epam.training.blog.logic.api.MainLogic;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManagementCommand extends EditorCommand {
    private static final String DELETE_SUCCESS = "delete";
    private static final String IS_EXISTS = "isExists";
    private static final String SUCCESS = "success";
    private static final String EMPTY_STRING = "";
    private static final String DELETE_COMMAND = "/deleteCategory";
    private static final String CREATE_COMMAND = "/createCategory";
    private final Forward CATEGORIES_FORWARD = new Forward("/blog/categories", false);

    private CategoryLogic logic;

    private MainLogic mainLogic;
    private HttpSession session;
    private static Map<String, String> messages = new HashMap<>();

    static {
        messages.put(DELETE_SUCCESS, "successfully deleted");
        messages.put(IS_EXISTS, "this category already exists");
        messages.put(SUCCESS, "successfully created");
    }

    public CategoryManagementCommand(CategoryLogic categoryLogic,MainLogic mainLogic) {
        this.logic =  categoryLogic;
        this.mainLogic =  mainLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        Command command = (Command) request.getAttribute("command");
        String commandName = command.getName();
        if (DELETE_COMMAND.equals(commandName)) {
            deleteCategory(request);
        }
        if (CREATE_COMMAND.equals(commandName)) {
            createCategory(request);
        }
        return CATEGORIES_FORWARD;
    }

    private void createCategory(HttpServletRequest request) {
        String category = request.getParameter("categoryName");
        if (logic.isExists(category)) {
            CATEGORIES_FORWARD.getAttributes().put(IS_EXISTS, messages.get(IS_EXISTS));
        } else {
            if (!EMPTY_STRING.equals(category)) {
                logic.createCategory(category);
                CATEGORIES_FORWARD.getAttributes().put(SUCCESS, messages.get(SUCCESS));
                List<DbCategory> categories = mainLogic.getCategory();
                session.setAttribute("categories", categories);
            }
        }
    }

    private void deleteCategory(HttpServletRequest request) {
        String categoryIdString = request.getParameter("categoryId");
        if (NumberUtils.isCreatable(categoryIdString)) {
            Integer categoryId = Integer.valueOf(categoryIdString);
            boolean flag = logic.deleteCategory(categoryId);
            if (flag) {
                CATEGORIES_FORWARD.getAttributes().put(DELETE_SUCCESS, messages.get(DELETE_SUCCESS));
                List<DbCategory> categories = mainLogic.getCategory();
                session.setAttribute("categories", categories);
            }
        }
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(false);
    }
}
