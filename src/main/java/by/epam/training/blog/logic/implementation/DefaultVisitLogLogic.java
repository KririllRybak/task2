package by.epam.training.blog.logic.implementation;

import by.epam.training.blog.domain.db_entity.VisitEntry;
import by.epam.training.blog.logic.api.VisitLogLogic;
import by.epam.training.blog.service.api.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DefaultVisitLogLogic implements VisitLogLogic {

    private VisitLogService visitLogService;

    public DefaultVisitLogLogic(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    private String convertDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss");
        String time = df.format(date);
        return time;
    }

    @Override
    public Integer registerEntry(String login, String sessionId, Date enterTime) {
        String time = convertDate(enterTime);
        visitLogService.registerEntry(login, sessionId, time);
        return 0;
    }

    @Override
    public Integer registerOut(String login, String sessionId, Date outTime) {
        String time = convertDate(outTime);
        visitLogService.registerOut(login, sessionId, time);
        return 0;
    }

    @Override
    public List<VisitEntry> getVisitLog() {
        List<VisitEntry> entries = visitLogService.getVisitLog();
        return entries;
    }
}
