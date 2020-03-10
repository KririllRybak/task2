package by.epam.training.blog.action.command_for_any_kind_of_user.sort;

import by.epam.training.blog.action.command_for_any_kind_of_user.UserCommand;
import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.logic.api.CategoryLogic;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class CategoryCommand extends UserCommand {
    private final Forward FORWARD_CATEGORY = new Forward("/blog/category", false);
    private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";

    private HttpSession session;
    private Integer categoryId;
    private CategoryLogic logic;

    public CategoryCommand(CategoryLogic logic ) {
        this.logic = logic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        if (session != null) {
            removeOldAttributes(request);
            if (categoryId != null) {
                collectCategoryWithPosts();
            } else {
               FORWARD_CATEGORY.goTo404(request,response);
            }
        }
        return FORWARD_CATEGORY;
    }

    private void collectCategoryWithPosts() {
        List<ApplicationPost> posts = logic.showPosts(categoryId);
        String category = logic.getCategoryName(categoryId);
        session.setAttribute("categoryName", category);
        if (posts.isEmpty()) {
            session.setAttribute("isEmpty", "Posts with that category is not exist's. ");
        } else {
            session.setAttribute("posts", posts);
        }
    }

    private void removeOldAttributes(HttpServletRequest request) {
        session.removeAttribute("posts");
        if (session.getAttribute("isEmpty") != null) {
            session.removeAttribute("isEmpty");
        }
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(false);
        String categoryIdString =(String) request.getAttribute("entityId");
        if(NumberUtils.isCreatable(categoryIdString)) {
            categoryId = Integer.parseInt(categoryIdString);
        }else {
            categoryId = null;
        }
    }
}
