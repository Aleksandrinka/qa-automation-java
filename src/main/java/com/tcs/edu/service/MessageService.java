package com.tcs.edu.service;

import com.tcs.edu.decorator.OrderDecorator;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.util.MessageUtils;

/**
 * Класс для управления модификациями сообщения
 *
 * @author a.chvarova
 */

public class MessageService {
    /**
     * Метод пропускает полученное сообщение через имеющиеся декораторы сообщений, после чего направляет итоговое сообщение принтеру
     *
     * @param level    Уровень важности сообщения
     * @param order    Порядок вывода сообщений в полученном массиве
     * @param message  Обязательное сообщение
     * @param messages Набор сообщений для печати
     */
    public static void process(Severity level, MessageOrder order, String message, String... messages) {

        if (message != null) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(level, message)));
        }

        if (order.equals(MessageOrder.DESC)) {
            for (int currentMessageCount = messages.length - 1; currentMessageCount >= 0; currentMessageCount--) {
                String currentMessage = messages[currentMessageCount];
                if (currentMessage != null) {
                    final String orderDecoratedMessage = OrderDecorator.decorate(currentMessage, currentMessageCount + 1);
                    final String severityDecoratedMessage = SeverityDecorator.decorate(level, orderDecoratedMessage);
                    final String timestampDecoratedMessage = TimestampMessageDecorator.decorate(severityDecoratedMessage);
                    ConsolePrinter.print(timestampDecoratedMessage);
                }
            }
        } else {
            Integer currentMessageCount = 1;
            for (String currentMessage : messages) {
                if (currentMessage != null) {
                    final String orderDecoratedMessage = OrderDecorator.decorate(currentMessage, currentMessageCount);
                    final String severityDecoratedMessage = SeverityDecorator.decorate(level, orderDecoratedMessage);
                    final String timestampDecoratedMessage = TimestampMessageDecorator.decorate(severityDecoratedMessage);
                    currentMessageCount++;
                    ConsolePrinter.print(timestampDecoratedMessage);
                }
            }
        }
    }

    /**
     * Перегрузка метода выше. При получении параметра типа Doubling проверяем полученный массив на то, что нужно сделать с дублями
     * Если вывод с дублями разрешен, вызываем метод выше. Иначе перед выводом проверяем, не выводилось ли сообщение ранее
     *
     * @param level    Уровень важности сообщения
     * @param order    Порядок вывода сообщений в полученном массиве
     * @param doubling Разрешено ли дублирование сообщений
     * @param message  Обязательное сообщение
     * @param messages Набор сообщений для печати
     */
    public static void process(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        if (doubling.equals(Doubling.DISTINCT)) {
            if (message != null) {
                ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(level, message)));
            }

            String[] alreadyPrintedMessages = new String[messages.length];
            if (order.equals(MessageOrder.DESC)) {
                for (int currentMessageCount = messages.length - 1; currentMessageCount >= 0; currentMessageCount--) {
                    String currentMessage = messages[currentMessageCount];
                    if (currentMessage != null & !MessageUtils.isMessageAlreadyInArray(currentMessage, alreadyPrintedMessages)) {
                        alreadyPrintedMessages[currentMessageCount] = currentMessage;
                        final String orderDecoratedMessage = OrderDecorator.decorate(currentMessage, currentMessageCount + 1);
                        final String severityDecoratedMessage = SeverityDecorator.decorate(level, orderDecoratedMessage);
                        final String timestampDecoratedMessage = TimestampMessageDecorator.decorate(severityDecoratedMessage);
                        ConsolePrinter.print(timestampDecoratedMessage);
                    }
                }
            } else {
                Integer currentMessageCount = 1;
                for (String currentMessage : messages) {
                    if (currentMessage != null & !MessageUtils.isMessageAlreadyInArray(currentMessage, alreadyPrintedMessages)) {
                        alreadyPrintedMessages[currentMessageCount] = currentMessage;
                        final String orderDecoratedMessage = OrderDecorator.decorate(currentMessage, currentMessageCount);
                        final String severityDecoratedMessage = SeverityDecorator.decorate(level, orderDecoratedMessage);
                        final String timestampDecoratedMessage = TimestampMessageDecorator.decorate(severityDecoratedMessage);
                        currentMessageCount++;
                        ConsolePrinter.print(timestampDecoratedMessage);
                    }
                }
            }
        } else {
            MessageService.process(level, order, message, messages);
        }
    }
}
