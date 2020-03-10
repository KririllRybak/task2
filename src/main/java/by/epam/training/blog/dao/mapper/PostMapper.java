package by.epam.training.blog.dao.mapper;

import by.epam.training.blog.domain.db_entity.DbPost;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<DbPost> {
    @Override
    public DbPost mapRow(ResultSet resultSet, int i) throws SQLException {
        DbPost dbPost = new DbPost();
        dbPost.setId(resultSet.getInt("id"));
        dbPost.setTitle(resultSet.getString("title"));
        dbPost.setText(resultSet.getString("post_text"));
        dbPost.setCreationStamp(resultSet.getDate("created_at"));
        dbPost.setAuthor(resultSet.getInt("author"));
        return dbPost;
    }
}
