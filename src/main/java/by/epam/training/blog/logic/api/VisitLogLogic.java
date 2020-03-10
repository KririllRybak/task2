package by.epam.training.blog.logic.api;

import by.epam.training.blog.domain.db_entity.VisitEntry;

import java.util.Date;
import java.util.List;

public interface VisitLogLogic {
    Integer registerEntry(String login, String sessionId, Date enterTime);

    Integer registerOut(String login, String sessionId, Date outTime);

    List<VisitEntry> getVisitLog();
}
