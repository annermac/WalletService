package application;

import infrastructure.Logging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {
    @BeforeEach
    void setUp() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testScanNumber() {
        AccountService.scanNumber();
        assertEquals(1, AccountService.option);
    }

    @Test
    void addLogging() {
        String message = "Tests";
        Logging logging = new Logging(message);
        List<Logging> loggingList = new ArrayList<>();
        loggingList.add(logging);
        AccountService.addLogging(message);
        assertEquals(loggingList.size(), AccountService.loggingList.size());
        assertEquals(loggingList.get(0).getMessage(), AccountService.loggingList.get(0).getMessage());
    }
}