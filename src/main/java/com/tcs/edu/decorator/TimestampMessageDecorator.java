package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс для модификации получаемых сообщений
 *
 * @author a.chvarova
 */
public class TimestampMessageDecorator {
    public static Integer messageCount = 0;


    /**
     * Метод для добавления текущих даты и времени к полученному сообщению
     *
     * @param message строка для модификации
     * @return к полученной на вход строке добавляется счетчик выведенных сообщений, текущая дата и время
     */
    public static String decorate(String message) {
        String decoratedMessage = messageCount.toString() + " " + Instant.now() + " " + message;
        return decoratedMessage;
    }
}
