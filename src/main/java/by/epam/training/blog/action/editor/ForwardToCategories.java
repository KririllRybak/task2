package by.epam.training.blog.action.editor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardToCategories extends EditorCommand {
    private final Forward CATEGORY_FORWARD = new Forward("/blog/categories",false);
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return CATEGORY_FORWARD;
    }

    @Override
    protected void initParams(HttpServletRequest request) throws IOException, ServletException {
    }


}
