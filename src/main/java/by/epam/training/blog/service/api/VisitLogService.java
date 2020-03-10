package by.epam.training.blog.service.api;

import by.epam.training.blog.domain.db_entity.VisitEntry;
import org.springframework.stereotype.Component;

import java.util.List;

public interface VisitLogService {
    Integer registerEntry(String login, String sessionId, String enterTime);

    Integer registerOut(String login, String sessionId, String outTime);

    List<VisitEntry> getVisitLog();

}
