package com.tcs.edu.util;

/**
 * Класс для вспомогательных операций с массивом строк
 *
 * @author a.chvarova
 */

public class MessageUtils {

    /**
     * Метод, превращающий все дубли сообщений в null
     *
     * @param messages Массив сообщений
     */
    public static void changeDoublesToNull(String... messages) {
        for (int i = 0; i < messages.length - 1; i++) {
            for (int j = i + 1; j < messages.length; j++) {
                if (messages[i] == null) {
                    break;
                }
                if (messages[i].equals(messages[j])) {
                    messages[j] = null;
                }
            }
        }
    }

    /**
     * Метод, проверяющий, есть ли строка в массиве
     *
     * @param message  Строка
     * @param messages Массив сообщений
     */

    public static boolean isMessageAlreadyInArray(String message, String... messages) {
        for (String currentMessage : messages) {
            if (currentMessage!=null){
                if (currentMessage.equals(message)) {
                    return true;
                }
            }
        }
        return false;
    }
}

