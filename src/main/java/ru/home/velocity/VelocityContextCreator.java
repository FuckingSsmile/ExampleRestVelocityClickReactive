package ru.home.velocity;

import org.apache.velocity.VelocityContext;

/**
 * Интерфейс `VelocityContextCreator` определяет метод для создания объекта `VelocityContext`
 */
public interface VelocityContextCreator {
    /**
     * Создает объект VelocityContext и добавляет переменные в него.
     *
     * @return объект VelocityContext с добавленными переменными
     */
    VelocityContext toVelocityContext();
}