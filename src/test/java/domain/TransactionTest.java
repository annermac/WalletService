package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction transaction1;
    Transaction transaction2;
    LocalDateTime expectedTime;

    @BeforeEach
    void setUp() {
        transaction1 = new Transaction(TransactionType.WITHDRAW, 200);
        expectedTime = LocalDateTime.now();
        transaction2 = new Transaction(TransactionType.DEPOSIT, 300);
    }

    @Test
    void testGetId() {
        assertTrue(transaction1.getId() > 0);
    }

    @Test
    void testGetTime() {
        assertEquals(expectedTime, transaction1.getTime());
    }

    @Test
    void testGenerateTransactionId() {
        assertNotEquals(transaction1.getId(), transaction2.getId());
    }

    @Test
    void testToString() {
        String exceptedString = "{" +
                "id: " + transaction1.getId() +
                ", type: " + TransactionType.WITHDRAW +
                ", amount: " + 200.0 +
                ", time: " + expectedTime +
                "}";
        assertEquals(exceptedString, transaction1.toString());
    }

}