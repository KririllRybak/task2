package by.epam.training.blog.dao.mapper;

import by.epam.training.blog.domain.db_entity.DbCategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
public class CategoryMapper implements RowMapper<DbCategory> {
    @Override
    public DbCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        DbCategory category = new DbCategory();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
