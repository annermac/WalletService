package domain;

/**
 * Класс User представляет собой игрока системы.
 * Он содержит логин, пароль и счет.
 */
public class User {
    private String login;
    private String password;
    private Account account;

    /**
     * Создает нового игрока
     *
     * @param login    логин
     * @param password пароль
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.account = new Account();
    }

    /**
     * Метод получает логин игрока
     */
    public String getLogin() {
        return login;
    }

    /**
     * Метод получает пароль игрока
     */
    public String getPassword() {
        return password;
    }

    /**
     * Метод получает счет игрока
     */
    public Account getAccount() {
        return account;
    }

}
