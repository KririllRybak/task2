package by.epam.training.blog.dao.api;

import java.util.List;

public interface PostWithCategoryDao{
    List<Integer> findPostByCategory(Integer categoryId)  ;
    List<Integer> findCategoryByPost(Integer postId)  ;
    void linkCategoryAndPost(Integer postId,Integer categoryId );
}
