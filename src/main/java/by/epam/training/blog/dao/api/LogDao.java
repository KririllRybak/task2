package by.epam.training.blog.dao.api;

import by.epam.training.blog.domain.db_entity.VisitEntry;

import java.util.List;

public interface LogDao  {
    Integer registerEntry(String login,String sessionId, String enterTime)  ;
    Integer registerOut(String login,String sessionId, String outTime) ;
    List<VisitEntry> getVisitLog() ;
}
