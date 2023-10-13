package domain;

import infrastructure.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setBalance(500);
    }

    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, 500);
        account.addTransaction(transaction);
        Map<Long, Transaction> transactions = account.getTransactions();
        Long transactionId = transaction.getId();
        assertTrue(transactions.containsKey(transactionId));
        assertEquals(transaction, transactions.get(transactionId));
    }

    @Test
    void testGetTransactions() {
        assertTrue(account.getTransactions().isEmpty());
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, 500);
        account.addTransaction(transaction);
        Map<Long, Transaction> transactions = account.getTransactions();
        assertEquals(1, transactions.size());
    }

    @Test
    void testCheckTransactionUnique() {
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, 500);
        account.addTransaction(transaction);
        Long transactionId = transaction.getId();
        assertFalse(account.checkTransactionUnique(transactionId));
        assertTrue(account.checkTransactionUnique(12345L));
    }

    @Test
    void testGetBalance() {
        assertEquals(500, account.getBalance());
    }

    @Test
    void testSetBalance() {
        account.setBalance(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void testWithdrawSufficientFunds() throws InsufficientFundsException {
        double amount = 200.0;
        account.withdraw(amount);
        double expectedBalance = 300.0;
        double actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void testWithdrawInsufficientFunds() {
        double amount = 600.0;
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(amount));
    }

    @Test
    void testDeposit() {
        double amount = 200.0;
        account.deposit(amount);
        double expectedBalance = 700.0;
        double actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }
}