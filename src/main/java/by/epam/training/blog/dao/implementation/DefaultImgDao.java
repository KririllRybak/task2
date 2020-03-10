package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.dao.api.ImageDao;
import by.epam.training.blog.dao.mapper.ImgMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Log4j2
public class DefaultImgDao implements ImageDao {

    private static final String ADD_PICTURE = "INSERT INTO public.image (post_id, image) VALUES (?,?);";
    private static final String SHOW_POST_PICTURES = "SELECT image FROM public.image WHERE post_id=?;";

    private JdbcTemplate jdbcTemplate;
    private ImgMapper imgMapper;

    public DefaultImgDao(JdbcTemplate jdbcTemplate, ImgMapper imgMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.imgMapper = imgMapper;
    }


    @Override
    public Integer addImgToPost(Integer postId, InputStream inputStream) {
        return jdbcTemplate.update(ADD_PICTURE,inputStream);
    }

    @Override
    public List<String> showPostImg(Integer postId) {
        return jdbcTemplate.query(SHOW_POST_PICTURES, new Object[]{postId},imgMapper);
    }
}
