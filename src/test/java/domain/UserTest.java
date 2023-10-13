package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {
    User user;
    String login;
    String password;

    @BeforeEach
    void setUp() {
        login = "abc";
        password = "1234";
        user = new User(login, password);
    }

    @Test
    void testGetLogin() {
        assertEquals(login, user.getLogin());
    }

    @Test
    void getPassword() {
        assertEquals(password, user.getPassword());
    }

    @Test
    void getAccount() {
        assertNotNull(user.getAccount());
    }
}