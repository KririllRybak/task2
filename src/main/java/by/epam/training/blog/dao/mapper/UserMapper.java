package by.epam.training.blog.dao.mapper;

import by.epam.training.blog.domain.db_entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User dbUser = new User();
        dbUser.setId(resultSet.getInt("id"));
        dbUser.setLogin(resultSet.getString("login"));
        dbUser.setPassword(resultSet.getString("password"));
        dbUser.setEmail(resultSet.getString("email"));
        dbUser.setCreationStamp(resultSet.getDate("created_at"));
        dbUser.setAboutMe(resultSet.getString("about_me"));
        byte[] blob = resultSet.getBytes("img");
        if (blob != null) {
            String imgString = Base64.getEncoder().encodeToString(blob);
            dbUser.setImg(imgString);
        }
        dbUser.setRole(resultSet.getInt("role"));
        return dbUser;
    }
}
