package com.tcs.edu.decorator;

import com.tcs.edu.enums.Severity;

/**
 * Класс для добавления номера сообщения в общем списке
 *
 * @author a.chvarova
 */

public class OrderDecorator {
    /**
     * Метод, добавляющий к сообщению его порядковый номер в массиве полученных на вывод сообщений
     *
     * @param messageOrderNumber Номер сообщения
     * @param message            Сообщение
     * @return Итоговое сообщение, содержащее уровень его важности
     * @see Severity
     */
    public String decorate(String message, Integer messageOrderNumber) {
        return String.format("%s %s", message, messageOrderNumber);
    }
}
