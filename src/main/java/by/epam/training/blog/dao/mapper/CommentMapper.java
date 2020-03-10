package by.epam.training.blog.dao.mapper;

import by.epam.training.blog.domain.db_entity.DbComment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CommentMapper implements RowMapper<DbComment> {
    @Override
    public DbComment mapRow(ResultSet resultSet, int i) throws SQLException {
        DbComment dbComment = new DbComment();
        dbComment.setId(resultSet.getInt("id"));
        dbComment.setComment(resultSet.getString("comment_text"));
        dbComment.setIdPost(resultSet.getInt("post_id"));
        dbComment.setAuthor(resultSet.getInt("author"));
        return dbComment;
    }
}
