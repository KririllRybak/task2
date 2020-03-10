package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.configuration.AppContext;
import by.epam.training.blog.dao.api.LogDao;
import by.epam.training.blog.dao.mapper.VisitLogMapper;
import by.epam.training.blog.domain.db_entity.VisitEntry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
public class DefaultLogDao implements LogDao {
    private static final String REGISTER_ENTRY = "INSERT INTO public.visit_log(login,session_id,enter_time) " +
            "VALUES (?,?,?);";
    private static final String REGISTER_OUT = "UPDATE public.visit_log SET out_time = ?" +
            " WHERE visit_log.login LIKE ? AND visit_log..session_id LIKE ?;";
    private static final String SHOW_VISIT_LOG = "SELECT id,login,enter_time,out_time " +
            "FROM public.visit_log ORDER BY id DESC;";

    private JdbcTemplate jdbcTemplate;
    private VisitLogMapper visitLogMapper;

    public DefaultLogDao(JdbcTemplate jdbcTemplate, VisitLogMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.visitLogMapper = mapper;
    }

    @Override
    public Integer registerEntry(String login, String sessionId, String enterTime) {
        return jdbcTemplate.update(REGISTER_ENTRY, new Object[]{login, sessionId, enterTime});
    }

    @Override
    public Integer registerOut(String login, String sessionId, String outTime) {
        return jdbcTemplate.update(REGISTER_OUT, new Object[]{outTime,login,sessionId});
    }

    @Override
    public List<VisitEntry> getVisitLog() {
        return jdbcTemplate.query(SHOW_VISIT_LOG, visitLogMapper);
    }

}
