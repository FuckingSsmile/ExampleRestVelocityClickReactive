package ru.home.velocity;

import lombok.Getter;

/**
 * Шаблон для поиска нужных данных с clickhouse
 */
@Getter
public enum TemplateType {
    PERSON,
    COUNT,
    SOME_PERSONS,
    ALL_PERSONS
}
