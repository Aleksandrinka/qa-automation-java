package com.tcs.edu.service;

import com.tcs.edu.decorator.OrderDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
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
    ConsolePrinter consolePrinter;
    TimestampMessageDecorator timestampMessageDecorator;
    SeverityDecorator severityDecorator;
    OrderDecorator orderDecorator;

    public MessageService() {
        this.consolePrinter = new ConsolePrinter();
        this.timestampMessageDecorator = new TimestampMessageDecorator();
        this.severityDecorator = new SeverityDecorator();
        this.orderDecorator = new OrderDecorator();
    }

    public MessageService(ConsolePrinter consolePrinter, TimestampMessageDecorator timestampMessageDecorator, SeverityDecorator severityDecorator, OrderDecorator orderDecorator) {
        this.consolePrinter = consolePrinter;
        this.timestampMessageDecorator = timestampMessageDecorator;
        this.severityDecorator = severityDecorator;
        this.orderDecorator = orderDecorator;
    }

    /**
     * Метод пропускает полученное сообщение через имеющиеся декораторы сообщений, после чего направляет итоговое сообщение принтеру
     *
     * @param order    Порядок вывода сообщений в полученном массиве
     * @param message  Обязательное сообщение
     * @param messages Набор сообщений для печати
     */
    public void process(MessageOrder order, Message message, Message... messages) {

        consolePrinter = new ConsolePrinter();

        if (message.getBody() != null) {
            consolePrinter.print(
                    timestampMessageDecorator.decorate(
                            severityDecorator.decorate(message.getSeverity(), message.getBody())));
        }

        if (order.equals(MessageOrder.DESC)) {
            for (int currentMessageCount = messages.length - 1; currentMessageCount >= 0; currentMessageCount--) {
                Message currentMessage = messages[currentMessageCount];
                if (currentMessage.getBody() != null) {
                    final String decoratedMessage =
                            getAllDecorations(currentMessage, currentMessageCount + 1);
                    consolePrinter.print(decoratedMessage);
                }
            }
        } else {
            Integer currentMessageCount = 1;
            for (Message currentMessage : messages) {
                if (currentMessage != null) {
                    final String decoratedMessage =
                            getAllDecorations(currentMessage, currentMessageCount);
                    currentMessageCount++;
                    consolePrinter.print(decoratedMessage);
                }
            }
        }
    }

    /**
     * Перегрузка метода выше. При получении параметра типа Doubling проверяем полученный массив на то, что нужно сделать с дублями
     * Если вывод с дублями разрешен, вызываем метод выше. Иначе перед выводом проверяем, не выводилось ли сообщение ранее
     *
     * @param order    Порядок вывода сообщений в полученном массиве
     * @param doubling Разрешено ли дублирование сообщений
     * @param message  Обязательное сообщение
     * @param messages Набор сообщений для печати
     */
    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        if (doubling.equals(Doubling.DISTINCT)) {
            if (message.getBody() != null) {
                consolePrinter.print(timestampMessageDecorator.decorate(
                        severityDecorator.decorate(message.getSeverity(), message.getBody())));
            }

            String[] alreadyPrintedMessages = new String[messages.length];
            if (order.equals(MessageOrder.DESC)) {
                for (int currentMessageCount = messages.length - 1; currentMessageCount >= 0; currentMessageCount--) {
                    Message currentMessage = messages[currentMessageCount];
                    if (currentMessage.getBody() != null & !MessageUtils.isMessageAlreadyInArray(currentMessage.getBody(), alreadyPrintedMessages)) {
                        alreadyPrintedMessages[currentMessageCount] = currentMessage.getBody();
                        final String decoratedMessage =
                                getAllDecorations(currentMessage, currentMessageCount + 1);
                        consolePrinter.print(decoratedMessage);
                    }
                }
            } else {
                Integer currentMessageCount = 1;
                for (Message currentMessage : messages) {
                    if (currentMessage.getBody() != null & !MessageUtils.isMessageAlreadyInArray(currentMessage.getBody(), alreadyPrintedMessages)) {
                        alreadyPrintedMessages[currentMessageCount] = currentMessage.getBody();
                        final String decoratedMessage =
                                getAllDecorations(currentMessage, currentMessageCount);
                        currentMessageCount++;
                        consolePrinter.print(decoratedMessage);
                    }
                }
            }
        } else {
            new MessageService().process(order, message, messages);
        }
    }

    public String getAllDecorations(Message message, Integer number) {
        final String orderDecoratedMessage =
                orderDecorator.decorate(message.getBody(), number);
        final String severityDecoratedMessage =
                severityDecorator.decorate(message.getSeverity(), orderDecoratedMessage);
        final String timestampMessageDecoratedMessage =
                timestampMessageDecorator.decorate(severityDecoratedMessage);
        return timestampMessageDecoratedMessage;
    }
}
