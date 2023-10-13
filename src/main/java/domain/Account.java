package domain;

import infrastructure.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс Account представляет собой счет.
 * Он содержит информацию о балансе и транзакциях.
 */
public class Account {
    private double balance;
    private final Map<Long, Transaction> transactions;

    /**
     * Создает новый банковский счет с автоматическим балансом и пустым списком транзакций.
     */
    public Account() {
        this.balance = 0;
        this.transactions = new HashMap<>();
    }

    /**
     * Метод добавляет транзакцию.
     *
     * @param transaction новая транзакция
     */
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    /**
     * Метод получает транзакции.
     */
    public Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Метод проверяет на уникальность транзакции по ID
     *
     * @param transactionId ID транзакции
     */
    public boolean checkTransactionUnique(Long transactionId) {
        return !transactions.containsKey(transactionId);
    }

    /**
     * Метод возвращет баланс
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод устанавливает баланс
     *
     * @param balance новый баланс
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод снимает сумму со счета и проверяет уникальность ID транзакции
     *
     * @param amount сумма для снятия
     * @throws InsufficientFundsException Если сумма превыщает сумме на балансе.
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount >= balance) {
            throw new InsufficientFundsException("Not enough funds!");
        }

        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount);
        if (checkTransactionUnique(transaction.getId())) {
            balance -= amount;
            addTransaction(transaction);
        } else {
            System.out.println("Error! The transaction ID is not unique.");
        }
    }

    /**
     * Метод вносит сумму на счет и проверяет уникальность ID транзакции
     *
     * @param amount сумма для внесения
     */
    public void deposit(double amount) {
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount);
        if (checkTransactionUnique(transaction.getId())) {
            balance += amount;
            addTransaction(transaction);
        } else {
            System.out.println("Error! The transaction ID is not unique.");
        }
    }
}
