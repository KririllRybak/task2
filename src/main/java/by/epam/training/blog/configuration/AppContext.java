package by.epam.training.blog.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {
    public static final ApplicationContext context =
            new AnnotationConfigApplicationContext(ApplicationConfig.class);
}
