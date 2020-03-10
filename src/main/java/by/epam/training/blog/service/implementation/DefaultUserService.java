package by.epam.training.blog.service.implementation;

import by.epam.training.blog.dao.api.UserDao;
import by.epam.training.blog.dao.api.UserSubscriberDao;
import by.epam.training.blog.domain.db_entity.User;
import by.epam.training.blog.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DefaultUserService implements UserService {

    private UserDao userDao;
    private UserSubscriberDao userSubDao;

    public DefaultUserService(UserDao userDao, UserSubscriberDao userSubDao) {
        this.userDao = userDao;
        this.userSubDao = userSubDao;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> dbUsers = userDao.getAllUsers();
        return dbUsers;
    }

    @Override
    public List<User> showUserSubscribers(Integer userId) {
        List<User> dbUsers = new ArrayList<>();
        List<Integer> ids = userSubDao.findUserSubscriber(userId);
        for (Integer id : ids) {
            dbUsers.add(userDao.read(id));
        }
        return dbUsers;
    }

    @Override
    public List<User> showUserSubscriptions(Integer userId) {
        List<User> dbUsers = new ArrayList<>();
        List<Integer> ids = userSubDao.showUserSubscriptions(userId);
        for (Integer id : ids) {
            dbUsers.add(userDao.read(id));
        }
        return dbUsers;
    }

    @Override
    public void addPicture(Integer userId, InputStream fis) {
        userDao.addPicture(userId, fis);
    }

    @Override
    public User getByLogin(String login) {
        User dbUser = userDao.getByLogin(login);
        return dbUser;
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.delete(id);
    }


    @Override
    public Integer updateUser(User dbUser) {
        return userDao.update(dbUser);
    }

    @Override
    public User findUser(Integer id) {
        User dbUser = null;
        dbUser = userDao.read(id);
        return dbUser;
    }

    @Override
    public Integer save(User dbUser) {
        return userDao.create(dbUser);
    }

    @Override
    public void subscribe(Integer sub, Integer user) {
        userSubDao.subscribeOnUser(sub, user);
    }

    @Override
    public void unsubscribe(Integer sub, Integer user) {
        userSubDao.unsubscribeOnUser(sub, user);
    }

    @Override
    public boolean isSigned(Integer sub, Integer user) {
        return userSubDao.isSigned(sub, user);
    }

    @Override
    public void changeRole(Integer role, Integer userId) {
        userDao.changeRole(role, userId);
    }
}
