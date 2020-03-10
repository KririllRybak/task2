package by.epam.training.blog.logic.api;

public interface RegistrationLogic   {
    boolean checkForLogin(String login);

    boolean checkPasswordValidation(String pass);

    boolean comparePasswords(String password, String confimPassword);

    boolean registerUser(String login, String password, String email, String contextPath);
}
