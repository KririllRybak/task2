package by.epam.training.blog.dao.api;

import java.util.List;

public interface UserSubscriberDao{
    List<Integer> findUserSubscriber(Integer id)  ;
    void subscribeOnUser(Integer sub,Integer user) ;
    void unsubscribeOnUser(Integer sub,Integer user) ;
    boolean isSigned(Integer sub,Integer user)  ;
    List<Integer> showUserSubscriptions(Integer id)  ;
}
