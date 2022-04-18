package com.tcs.edu;

import com.tcs.edu.enums.Severity;
import com.tcs.edu.service.MessageService;

class Application {
    public static void main(String[] args) {

        MessageService.process(Severity.MAJOR, "Hello world!", "Hello again", "And another one major hello");
        MessageService.process(Severity.MINOR, "Hello world!");
        MessageService.process(Severity.REGULAR, "Hello world!");
        MessageService.process(Severity.MAJOR, "Hello world!");
        MessageService.process(Severity.MINOR, "Hello world!", "Hello again", "And another one minor hello");
        MessageService.process(Severity.REGULAR, "Hello world!");

    }
}