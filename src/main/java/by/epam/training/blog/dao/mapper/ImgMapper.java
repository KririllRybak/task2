package by.epam.training.blog.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class ImgMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        byte[] blob = resultSet.getBytes("image");
        if (blob != null) {
            String imgString = Base64.getEncoder().encodeToString(blob);
             return imgString;
        }
        return null;
    }
}
