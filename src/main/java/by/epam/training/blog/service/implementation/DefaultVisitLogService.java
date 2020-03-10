package by.epam.training.blog.service.implementation;

import by.epam.training.blog.dao.api.LogDao;
import by.epam.training.blog.domain.db_entity.VisitEntry;
import by.epam.training.blog.service.api.VisitLogService;
import org.springframework.stereotype.Component;

import java.util.List;


public class DefaultVisitLogService implements VisitLogService {

    private LogDao logDao;

    public DefaultVisitLogService(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public Integer registerEntry(String login, String sessionId, String enterTime) {
        int row = logDao.registerEntry(login, sessionId, enterTime);
        return row;
    }

    @Override
    public Integer registerOut(String login, String sessionId, String outTime) {
        int row = logDao.registerOut(login, sessionId, outTime);
        return row;
    }

    @Override
    public List<VisitEntry> getVisitLog() {
        List<VisitEntry> visitLog = logDao.getVisitLog();
        return visitLog;
    }
}
