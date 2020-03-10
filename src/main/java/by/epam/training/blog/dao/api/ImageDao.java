package by.epam.training.blog.dao.api;

import java.io.InputStream;
import java.util.List;

public interface ImageDao {
    Integer addImgToPost(Integer postId, InputStream inputStream) ;
    List<String> showPostImg(Integer postId)  ;
}
