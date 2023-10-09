package infrastructure;

import java.time.LocalDateTime;

/**
 * Класс Logging представляет собой инструмент для логирования событий в приложении.
 */
public class Logging {
    private String message;
    private LocalDateTime time;

    /**
     * Создает новый лог
     *
     * @param message сообщение
     */
    public Logging(String message) {
        this.message = message;
        this.time = LocalDateTime.now();
    }

    /**
     * Метод возвращает сообщение лога
     */
    public String getMessage() {
        return message;
    }

    /**
     * Метод возвращает время лога
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
                "message: '" + message + '\'' +
                ", time: " + time +
                '}';
    }
}
