package com.tcs.edu.decorator;

import com.tcs.edu.enums.Severity;

/**
 * Класс, дополняющий сообщение уровнем важности
 *
 * @author a.cvarova
 */

public class SeverityDecorator {
    /**
     * Метод, добавляющий к сообщению уровень его важности
     *
     * @param level   Уровень важности сообщения
     * @param message Сообщение
     * @return Итоговое сообщение, содержащее уровень его важности
     * @see Severity
     */
    public static String decorate(Severity level, String message) {
        String severityString = null;
        switch (level) {
            case MAJOR:
                severityString = "(!!!)";
                break;
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
        }

        return String.format("%s %s", message, severityString);
    }
}
