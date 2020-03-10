package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.dao.api.UserDao;
import by.epam.training.blog.dao.mapper.UserMapper;
import by.epam.training.blog.domain.db_entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.util.List;

@Log4j2
public class DefaultUserDao implements UserDao {

    private static final String CREATE_USER = "INSERT INTO public.users(login,password,email) VALUES(?,?,?);";
    private static final String FIND_USER_BY_ID = "SELECT " +
            "id,login,password,email,created_at,img,role,about_me" +
            " FROM public.users WHERE users.id = ?;";
    private static final String ADD_USER_PICTURE = "UPDATE public.users SET users.img = ? WHERE users.id = ?;";
    private static final String FIND_USER_BY_LOGIN = "SELECT " +
            "id,login,password,email,created_at,img,role,about_me" +
            " FROM public.users WHERE users.login = ?;";
    private static final String FIND_ALL_USERS = "SELECT " +
            "id,login,password,email,created_at,img,role,about_me FROM users " +
            "ORDER BY users.login;";
    private static final String DELETE_USER_BY_ID = "DELETE FROM public.users WHERE users.id = ?;";
    private static final String UPDATE_USER = "UPDATE public.users SET password = ?, email = ?,about_me = ? WHERE users.id = ?;";
    private static final String CHANGE_ROLE ="UPDATE public.users SET role = ? WHERE users.id =?;";

    private JdbcTemplate jdbcTemplate;
    private UserMapper userMapper;


    public DefaultUserDao(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public void changeRole(Integer role,Integer userId) {
        Object [] params = new Object[]{role,userId};
        jdbcTemplate.update(CHANGE_ROLE,params);
    }

    @Override
    public Integer create(User dbUser) {
        Object[] params = new Object[]{dbUser.getLogin(),
                dbUser.getPassword(),dbUser.getEmail()};
        int result = jdbcTemplate.update(CREATE_USER,params);
        return result;
    }


    @Override
    public User read(Integer id){
        Object[] params = new Object[]{id};
       return jdbcTemplate.queryForObject(FIND_USER_BY_ID,params,userMapper);
    }

    @Override
    public Integer update(User entity) {
        Object [] params = new  Object[]{entity.getPassword(),
                entity.getEmail(),entity.getAboutMe(),entity.getAboutMe(),
                entity.getId()};
        return jdbcTemplate.update(UPDATE_USER,params);
    }

    @Override
    public Integer delete(Integer id){
        Object[] params = new Object[]{id};
        return jdbcTemplate.update(DELETE_USER_BY_ID,params);
    }

    @Override
    public User getByLogin(String login){
        Object[] params = new Object[]{login};
        try {
            User user = jdbcTemplate.queryForObject(FIND_USER_BY_LOGIN, params, userMapper);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(FIND_ALL_USERS,userMapper);
    }

    @Override
    public void addPicture(Integer userId, InputStream fis) {
        Object[] params = new Object[]{userId, fis};
        jdbcTemplate.update(ADD_USER_PICTURE, params);
    }



}
