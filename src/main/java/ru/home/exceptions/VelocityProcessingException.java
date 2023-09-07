package ru.home.exceptions;

/**
 * Исключение, выбрасываемое при возникновении ошибки в процессе обработки шаблона Velocity.
 */
public class VelocityProcessingException extends RuntimeException {
    /**
     * Конструктор для создания исключения VelocityProcessingException
     *
     * @param message сообщение об ошибке
     * @param cause   причина ошибки
     */
    public VelocityProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}