package by.epam.training.blog.configuration;

import by.epam.training.blog.action.ForwardToRegistrationPageCommand;
import by.epam.training.blog.action.LoginCommand;
import by.epam.training.blog.action.LogoutCommand;
import by.epam.training.blog.action.RegistrationCommand;
import by.epam.training.blog.action.admin.UserListCommand;
import by.epam.training.blog.action.admin.UserManagementCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.ChangeLanguageCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.MainCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.post.*;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.EditProfileCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.ProfileCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.ShowUserCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.profile.SubscriptionCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.sort.CategoryCommand;
import by.epam.training.blog.action.command_for_any_kind_of_user.sort.SearchCommand;
import by.epam.training.blog.action.editor.CategoryManagementCommand;
import by.epam.training.blog.action.editor.ForwardToCategories;
import by.epam.training.blog.dao.api.*;
import by.epam.training.blog.dao.implementation.*;
import by.epam.training.blog.dao.mapper.*;
import by.epam.training.blog.domain.application_entity.ApplicationComment;
import by.epam.training.blog.domain.db_entity.DbComment;
import by.epam.training.blog.logic.api.*;
import by.epam.training.blog.logic.entity_converter.Converter;
import by.epam.training.blog.logic.entity_converter.implementation.CategoryConverter;
import by.epam.training.blog.logic.entity_converter.implementation.CommentConverter;
import by.epam.training.blog.logic.entity_converter.implementation.PostConverter;
import by.epam.training.blog.logic.entity_converter.implementation.UserConverter;
import by.epam.training.blog.logic.implementation.*;
import by.epam.training.blog.service.api.*;
import by.epam.training.blog.service.implementation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@ComponentScan("by.epam.training.blog")
@PropertySource("classpath:datasource.properties")
public class ApplicationConfig {

    @Value("${jdbcUrl}")
    private String URL;
    @Value("${dataSource.password}")
    private String USER;
    @Value("${driver}")
    private String DRIVER;
    @Value("${dataSource.password}")
    private String PASSWORD;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public CategoryDao categoryDao() {
        return new DefaultCategoryDao(jdbcTemplate(), categoryMapper());
    }

    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapper();
    }

    @Bean
    public CommentDao commentDao() {
        return new DefaultCommentDao(jdbcTemplate(), commentMapper(), authorMapper());
    }

    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper();
    }

    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapper();
    }

    @Bean
    public ImageDao imageDao() {
        return new DefaultImgDao(jdbcTemplate(), imgMapper());
    }

    @Bean
    public ImgMapper imgMapper() {
        return new ImgMapper();
    }

    @Bean
    public LogDao visitLogDao() {
        return new DefaultLogDao(jdbcTemplate(), visitLogMapper());
    }

    @Bean
    public VisitLogMapper visitLogMapper() {
        return new VisitLogMapper();
    }

    @Bean
    public PostDao postDao() {
        return new DefaultPostDao(jdbcTemplate(), postMapper());
    }

    @Bean
    public PostMapper postMapper() {
        return new PostMapper();
    }

    @Bean
    public PostWithCategoryDao postWithCategoryDao() {
        return new DefaultPostWithCategoryDao(jdbcTemplate(), postIdMapper(),categoryIdMapper());
    }

    @Bean
    public CategoryIdMapper categoryIdMapper() {
        return new CategoryIdMapper();
    }

    @Bean
    public PostIdMapper postIdMapper() {
        return new PostIdMapper();
    }

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(jdbcTemplate(), userMapper());
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public SubscriberIdMapper subsIdMapper(){
        return new SubscriberIdMapper();
    }

    @Bean
    UserSubscriberDao userSubscriberDao() {
        return new DefaultUserSubscriberDao(jdbcTemplate(), userIdMapperInSubscriptions(),
                subsIdMapper());
    }

    @Bean
    public UserIdMapperInSubscriptions userIdMapperInSubscriptions() {
        return new UserIdMapperInSubscriptions();
    }

    @Bean
    public CategoryService categoryService() {
        return new DefaultCategoryService(categoryDao(), postWithCategoryDao());
    }

    @Bean
    public CommentService commentService() {
        return new DefaultCommentService(commentDao(), userDao());
    }

    @Bean
    public PostService postService() {
        return new DefaultPostService(postDao(), postWithCategoryDao(), imageDao());
    }

    @Bean
    public UserService userService() {
        return new DefaultUserService(userDao(), userSubscriberDao());
    }

    @Bean
    public VisitLogService visitLogService() {
        return new DefaultVisitLogService(visitLogDao());
    }

    @Bean
    public CategoryConverter categoryConverter() {
        return new CategoryConverter(categoryService(), postService());
    }

    @Bean
    public Converter<DbComment, ApplicationComment> commentConverter() {
        return new CommentConverter(postService(), userService());
    }

    @Bean
    public PostConverter postConverter() {
        return new PostConverter(commentConverter(), userService(), postService(), categoryService(), commentService());
    }

    @Bean
    public UserConverter userConverter() {
        return new UserConverter(postConverter(), commentConverter(), userService(), postService(), commentService());
    }

    @Bean
    public CategoryLogic categoryLogic() {
        return new DefaultCategoryLogic(postService(), categoryService(), postConverter());
    }

    @Bean
    public LoginLogic loginLogic() {
        return new DefaultLoginLogic(userService(), userConverter());
    }

    @Bean
    public MainLogic mainLogic() {
        return new DefaultMainLogic(postConverter(), categoryService(), postService());
    }

    @Bean
    public PostLogic postLogic() {
        return new DefaultPostLogic(postService(), commentService(), categoryService(),
                userService(), userConverter());
    }

    @Bean
    public ProfileLogic profileLogic() {
        return new DefaultProfileLogic(userService(), userConverter());
    }

    @Bean
    public RegistrationLogic registrationLogic() {
        return new DefaultRegistrationLogic(userService());
    }

    @Bean
    public SearchLogic searchLogic() {
        return new DefaultSearchLogic(postConverter(), postService());
    }

    @Bean
    public ShowUserLogic showUserLognic() {
        return new DefaultShowUserLogic(userService(), userConverter());
    }

    @Bean
    public UsersLogic usersLogic() {
        return new DefaultUsersLogic(userService(), userConverter());
    }

    @Bean
    public VisitLogLogic visitLogLogic() {
        return new DefaultVisitLogLogic(visitLogService());
    }

    @Bean
    public MainCommand mainCommand(){
        return new MainCommand(mainLogic());
    }

    @Bean
    public ForwardToRegistrationPageCommand forwardToRegistrationPageCommand(){
        return new ForwardToRegistrationPageCommand();
    }

    @Bean
    public RegistrationCommand registrationCommand(){
        return new RegistrationCommand(registrationLogic());
    }

    @Bean
    public LoginCommand loginCommand(){
        return new LoginCommand(loginLogic());
    }

    @Bean
    public ProfileCommand profileCommand(){
        return new ProfileCommand(profileLogic());
    }

    @Bean
    public LogoutCommand logoutCommand(){
        return new LogoutCommand();
    }

    @Bean
    public CategoryCommand categoryCommand(){
        return new CategoryCommand(categoryLogic());
    }

    @Bean
    public  SearchCommand searchCommand(){
        return new SearchCommand(searchLogic());
    }

    @Bean
    public EditProfileCommand editProfileCommand(){
        return new EditProfileCommand(profileLogic());
    }

    @Bean
    public ShowUserCommand showUserCommand(){
        return new ShowUserCommand(showUserLognic());
    }

    @Bean
    public ForwardToPostCreation forwardToPostCreation(){
        return new ForwardToPostCreation(mainLogic());
    }

    @Bean
    public CreatePostCommand createPostCommand(){
        return new CreatePostCommand(postLogic());
    }

    @Bean
    public DeleteOrEditCommand deleteOrEditCommand(){
        return new DeleteOrEditCommand(postLogic());
    }

    @Bean
    public EditPostCommand editPostCommand(){
        return new EditPostCommand(postLogic());
    }

    @Bean
    public UserListCommand userListCommand(){
        return new UserListCommand(usersLogic(),visitLogLogic());
    }

    @Bean
    public UserManagementCommand userManagementCommand(){
        return new UserManagementCommand(usersLogic());
    }

    @Bean
    public ForwardToCategories forwardToCategories(){
        return new ForwardToCategories();
    }

    @Bean
    public CategoryManagementCommand categoryManagementCommand(){
        return new CategoryManagementCommand(categoryLogic(),
                mainLogic());
    }

    @Bean
    public CommentManagementCommand commentManagementCommand(){
        return new CommentManagementCommand(postLogic());
    }

    @Bean
    public SubscriptionCommand subscriptionCommand(){
        return new SubscriptionCommand(profileLogic());
    }

    @Bean
    public ChangeLanguageCommand changeLanguageCommand(){
        return new ChangeLanguageCommand();
    }
}