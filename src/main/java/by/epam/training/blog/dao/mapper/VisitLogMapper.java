package by.epam.training.blog.dao.mapper;

import by.epam.training.blog.domain.db_entity.VisitEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitLogMapper implements RowMapper<VisitEntry> {
    @Override
    public VisitEntry mapRow(ResultSet resultSet, int i) throws SQLException {
        VisitEntry entry = new VisitEntry();
        entry.setId(resultSet.getInt("id"));
        entry.setLogin(resultSet.getString("login"));
        entry.setEnter(resultSet.getString("enter_time"));
        entry.setOut(resultSet.getString("out_time"));
        return entry;
    }
}
