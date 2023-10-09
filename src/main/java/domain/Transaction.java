package domain;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Класс Transaction представляет собой транзакцию.
 * Он содержит ID транзакции, тип транзакции, время и сумму.
 */
public class Transaction {
    private final Long transactionId;
    private final TransactionType transactionType;
    private final LocalDateTime time;
    private final double amount;

    /**
     * Создает новую транзакцию с типом и суммой.
     *
     * @param transactionType тип транзакции
     * @param amount          сумма
     */
    public Transaction(TransactionType transactionType, double amount) {
        this.transactionId = generateTransactionId();
        this.transactionType = transactionType;
        this.time = LocalDateTime.now();
        this.amount = amount;
    }

    /**
     * Метод генерирует ID транзакции
     */
    private Long generateTransactionId() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }

    /**
     * Метод возвращает ID транзакции
     */
    public Long getId() {
        return transactionId;
    }

    /**
     * Метод возвращает время транзакции
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Переопрделение метода toString
     */
    @Override
    public String toString() {
        return "{" +
                "id: " + transactionId +
                ", type: " + transactionType +
                ", amount: " + amount +
                ", time: " + time +
                '}';
    }
}
