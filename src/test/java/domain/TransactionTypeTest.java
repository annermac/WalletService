package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTypeTest {

    @Test
    void testValues() {
        TransactionType[] values = TransactionType.values();
        assertEquals(2, values.length);
        assertEquals(TransactionType.DEPOSIT, values[0]);
        assertEquals(TransactionType.WITHDRAW, values[1]);
    }

    @Test
    void testValueOf() {
        TransactionType deposit = TransactionType.valueOf("DEPOSIT");
        assertEquals(TransactionType.DEPOSIT, deposit);

        TransactionType withdraw = TransactionType.valueOf("WITHDRAW");
        assertEquals(TransactionType.WITHDRAW, withdraw);
    }
}