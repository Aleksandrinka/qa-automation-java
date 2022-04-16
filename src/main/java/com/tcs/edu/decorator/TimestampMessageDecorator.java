package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс для модификации получаемых сообщений
 *
 * @author a.chvarova
 */
public class TimestampMessageDecorator {
    public static Integer messageCount = 0;
    public static final Integer PAGE_SIZE = 4;


    /**
     * Метод для добавления текущих даты и времени к полученному сообщению
     *
     * @param message строка для модификации
     * @return к полученной на вход строке добавляется счетчик выведенных сообщений, текущая дата и время
     */
    public static String decorate(String message) {
        messageCount++;
        String format = "%s %s %s";
        if (messageCount % PAGE_SIZE == 0){
            format = String.format("%s%s", format, "\n---");
        }
        final var decoratedMessage = String.format(format, messageCount, Instant.now(), message);
        return decoratedMessage;
    }
}
