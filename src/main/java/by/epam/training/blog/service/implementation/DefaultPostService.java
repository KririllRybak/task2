package by.epam.training.blog.service.implementation;

import by.epam.training.blog.dao.api.ImageDao;
import by.epam.training.blog.dao.api.PostDao;
import by.epam.training.blog.dao.api.PostWithCategoryDao;
import by.epam.training.blog.domain.db_entity.DbPost;
import by.epam.training.blog.service.api.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultPostService implements PostService {

    private PostDao postDao;
    private PostWithCategoryDao postWithCategoryDao;
    private ImageDao imageDao;

    public DefaultPostService(PostDao postDao, PostWithCategoryDao postWithCategoryDao,
                              ImageDao imageDao) {
        this.postDao = postDao;
        this.postWithCategoryDao = postWithCategoryDao;
        this.imageDao = imageDao;
    }

    @Override
    public List<DbPost> showAllPost(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<DbPost> posts = postDao.showAllPost(start, recordsPerPage);
        return posts;
    }

    @Override
    public int getNumberOfRows() {
        return postDao.getNumberOfRows();
    }

    @Override
    public List<DbPost> findPostsByContent(String content) {
        List<DbPost> postsMatchByText = postDao.findPostByText(content);
        List<DbPost> postsMatchByTitle = postDao.findPostByTitle(content);
        postsMatchByText.addAll(postsMatchByTitle);
        return postsMatchByText;
    }

    @Override
    public List<DbPost> findUserPosts(Integer dbUser) {
        List<DbPost> dbPosts = postDao.findUserPosts(dbUser);
        return dbPosts;
    }

    @Override
    public List<DbPost> findPostsByCategory(Integer categoryId) {
        List<Integer> ids = postWithCategoryDao.findPostByCategory(categoryId);
        List<DbPost> dbPosts = new ArrayList<>();
        for (Integer id : ids) {
            dbPosts.add(postDao.read(id));
        }
        return dbPosts;
    }

    @Override
    public Integer addImgToPost(Integer postId, InputStream fis) {
        return imageDao.addImgToPost(postId, fis);
    }

    @Override
    public List<String> showPostImg(Integer postId) {
        return imageDao.showPostImg(postId);
    }

    @Override
    public Integer deletePost(Integer id) {
        return postDao.delete(id);
    }

    @Override
    public Integer updatePost(DbPost dbPost) {
        return postDao.update(dbPost);
    }

    @Override
    public DbPost findPost(Integer id) {
        DbPost post = postDao.read(id);
        return post;
    }

    @Override
    public Integer save(DbPost dbPost) {
        Integer id = postDao.create(dbPost);
        return id;
    }

    @Override
    public void linkPostWithcategory(Integer postId, Integer categoryId) {
        postWithCategoryDao.linkCategoryAndPost(postId, categoryId);
    }
}
