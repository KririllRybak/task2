package by.epam.training.blog.action.command_for_any_kind_of_user;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.domain.Role;
import by.epam.training.blog.domain.application_entity.ApplicationPost;
import by.epam.training.blog.domain.application_entity.ApplicationUser;
import by.epam.training.blog.domain.db_entity.DbCategory;
import by.epam.training.blog.logic.api.MainLogic;
import by.epam.training.blog.logic.implementation.DefaultMainLogic;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j2
public class MainCommand extends UserCommand {
    private final Forward FORWARD_MAIN = new Forward("/blog/main", false);

    private MainLogic logic;
    private HttpSession session;
    private Integer currentPage;
    private int numberOfPages;
    private ApplicationUser appUser;

    public MainCommand( MainLogic mainLogic) {
        this.logic =  mainLogic;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        initParams(request);
        setCurrentPage(request);
        List<ApplicationPost> posts = logic.showPosts(currentPage);
        List<DbCategory> categories = logic.getCategory();
        addAttributesToSession(posts, categories);
        if (appUser.getRole() == Role.EDITOR) {
            FORWARD_MAIN.getAttributes().put("createCategory", true);
        }
        return FORWARD_MAIN;
    }

    private void addAttributesToSession(List<ApplicationPost> posts,
                                        List<DbCategory> categories) {
        session.setAttribute("numberOfPages", numberOfPages);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("posts", posts);
        session.setAttribute("categories", categories);
    }

    private void setCurrentPage(HttpServletRequest request) {
        Object currentPageObj = request.getAttribute("entityId");
        if (currentPageObj == null) {
            currentPage = 1;
        } else {
           setValue(currentPageObj);
        }
        if (currentPage > numberOfPages) {
            currentPage = 1;
        }
    }

    private void setValue(Object currentpageObj){
        String currentPageString = (String)currentpageObj;
        if (checkValue(currentPageString)){
            currentPage =Integer.parseInt(currentPageString);
        }else {
            currentPage = 1;
        }
    }

    private boolean checkValue(String curentPage){
        return NumberUtils.isCreatable(curentPage);
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
        session = request.getSession(false);
        currentPage = null;
        numberOfPages = logic.getNumberOfPages();
        appUser = (ApplicationUser) session.getAttribute("authorizedUser");
    }
}