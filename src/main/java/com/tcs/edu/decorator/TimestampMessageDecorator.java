package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс для модификации получаемых сообщений
 *
 * @author a.chvarova
 */
public class TimestampMessageDecorator {

    /**
     * Метод для добавления текущих даты и времени к полученному сообщению
     *
     * @param message строка для модификации
     * @return к полученной на вход строке добавляется текущая дата и время с помощью Instant.now()
     */

    public static String decorate(String message) {
        String decoratedMessage = Instant.now() + " " + message;
        return decoratedMessage;
    }
}
