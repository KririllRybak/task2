package by.epam.training.blog.dao.implementation;

import by.epam.training.blog.dao.api.UserSubscriberDao;
import by.epam.training.blog.dao.mapper.SubscriberIdMapper;
import by.epam.training.blog.dao.mapper.UserIdMapperInSubscriptions;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Log4j2
public class DefaultUserSubscriberDao implements UserSubscriberDao {

    private static final String FIND_USER_SUBS = "SELECT subscriber_id FROM public.user_subscription" +
            " WHERE user_subscription.user_id = ?";
    private static final String SUBSCRIBE_ON_USER = "INSERT INTO public.user_subscription(subscriber_id,user_id)" +
            "VALUES (?,?);";
    private static final String UNSUBSCRIBE_ON_USER = "DELETE FROM public.user_subscription WHERE user_subscription.subscriber_id = ? " +
            "AND user_subscription.user_id = ?;";
    private static final String IS_SIGNED = "SELECT EXISTS(SELECT user_id, subscriber_id FROM public.user_subscription " +
            "WHERE user_subscription.subscriber_id = ? AND user_subscription.user_id = ?);";
    private static final String SHOW_USER_SUBSCRIPTIONS = "SELECT user_id FROM " +
            "public.user_subscription WHERE user_subscription.subscriber_id = ?;";
    private JdbcTemplate jdbcTemplate;
    private UserIdMapperInSubscriptions userIdMapperInSubscriptions;
    private SubscriberIdMapper subscriberIdMapper;

    public DefaultUserSubscriberDao(JdbcTemplate jdbcTemplate, UserIdMapperInSubscriptions userIdMapperInSubscriptions,
                                    SubscriberIdMapper subscriberIdMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userIdMapperInSubscriptions = userIdMapperInSubscriptions;
        this.subscriberIdMapper = subscriberIdMapper;
    }


    @Override
    public List<Integer> findUserSubscriber(Integer id) {
       return jdbcTemplate.query(FIND_USER_SUBS,new Object[]{id}, subscriberIdMapper);
    }

    @Override
    public void subscribeOnUser(Integer sub, Integer user)  {
        Object[] params = new Object[]{sub,user};
        jdbcTemplate.update(SUBSCRIBE_ON_USER,params);
    }

    @Override
    public void unsubscribeOnUser(Integer sub, Integer user)  {
        Object[] params = new Object[]{sub,user};
        jdbcTemplate.update(UNSUBSCRIBE_ON_USER,params);
    }

    @Override
    public boolean isSigned(Integer sub, Integer user)  {
        Object[] params = new Object[]{sub,user};
       return jdbcTemplate.queryForObject(IS_SIGNED, params,Boolean.class);
    }

    @Override
    public List<Integer> showUserSubscriptions(Integer id)  {
        return jdbcTemplate.query(SHOW_USER_SUBSCRIPTIONS,new Object[]{id}, userIdMapperInSubscriptions);
    }
}
