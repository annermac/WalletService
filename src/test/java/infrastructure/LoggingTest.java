package infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoggingTest {
    Logging logging;
    LocalDateTime time;
    String message;

    @BeforeEach
    void setUp() {
        message = "Test message";
        time = LocalDateTime.now();
        logging = new Logging(message);
    }

    @Test
    void testGetMessage() {
        String actualMessage = logging.getMessage();
        assertEquals(message, actualMessage);
    }

    @Test
    void testGetTime() {
        LocalDateTime actualTime = logging.getTime();
        assertEquals(time, actualTime);
    }

    @Test
    void testToString() {
        String expectedString = "{message: '" + message + "', time: " + time + "}";
        String actualString = logging.toString();
        assertEquals(expectedString, actualString);
    }
}