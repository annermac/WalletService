package infrastructure;

/**
 * Класс InsufficientFundsException представляет собой исключение,
 * которое возникает при попытке выполнить операцию, которая приводит
 * к недостаточному количеству средств на счете игрока.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
