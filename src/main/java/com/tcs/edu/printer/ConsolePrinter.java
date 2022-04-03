package com.tcs.edu.printer;

import com.tcs.edu.decorator.TimestampMessageDecorator;

/**
 * Класс для работы с выводом в консоль
 *
 * @author a.chvarova
 */

public class ConsolePrinter {
    /**
     * Метод, выводящий строку в консоль
     * Вызов метода приведет к изменению значения глобальной переменной messageCount
     *
     * @param message сообщение для вывода в консоль
     */
    public static void print(String message) {
        TimestampMessageDecorator.messageCount++;
        System.out.println(TimestampMessageDecorator.decorate(message));
    }
}
