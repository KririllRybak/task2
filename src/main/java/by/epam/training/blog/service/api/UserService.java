package by.epam.training.blog.service.api;

import by.epam.training.blog.domain.db_entity.User;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    List<User> showUserSubscribers(Integer userId);

    List<User> showUserSubscriptions(Integer userId);

    void addPicture(Integer userId, InputStream fis);

    User getByLogin(String login);

    Integer deleteUser(Integer id);

    Integer updateUser(User dbUser);

    User findUser(Integer id);

    Integer save(User dbUser);

    void subscribe(Integer sub, Integer user);

    void unsubscribe(Integer sub, Integer user);

    boolean isSigned(Integer sub, Integer user);

    void changeRole(Integer role, Integer userId);
}
