package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.db_entity.User;

import java.io.InputStream;
import java.util.List;

public interface UserDao extends CrudDao<User>{
    void changeRole(Integer role, Integer userId);

    User getByLogin(String login) ;
    List<User> getAllUsers() ;
    void addPicture(Integer userId, InputStream fis);
}
