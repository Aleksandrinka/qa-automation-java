package com.tcs.edu.service;

import com.tcs.edu.enums.Severity;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;

/**
 * Класс для управления модификациями сообщения
 *
 * @author a.chvarova
 */

public class MessageService {
    /**
     * Метод пропускает полученное сообщение через имеющиеся декораторы сообщений, после чего направляет итоговое сообщение принтеру
     *
     * @param level   Уровень важности сообщения
     * @param message Сообщение
     */
    public static void process(Severity level, String message) {
        final String decoratedMessage = TimestampMessageDecorator.decorate(message);
        final String severityDecoratedMessage = SeverityDecorator.decorate(level, decoratedMessage);
        ConsolePrinter.print(severityDecoratedMessage);
    }
}
