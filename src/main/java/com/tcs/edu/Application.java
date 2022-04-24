package com.tcs.edu;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.service.MessageService;

class Application {
    public static void main(String[] args) {

        MessageService.process(Severity.MAJOR, MessageOrder.ASC, "Hello world!", null, "And another one major hello");
        MessageService.process(Severity.MINOR, MessageOrder.ASC, "Hello world!");
        MessageService.process(Severity.REGULAR, MessageOrder.ASC, "Hello world!");
        MessageService.process(Severity.MAJOR, MessageOrder.ASC, null);
        MessageService.process(Severity.MAJOR, MessageOrder.ASC, "null");
        MessageService.process(Severity.MINOR, MessageOrder.DESC, "Hello world!", "Hello again", "And another one minor hello");
        MessageService.process(Severity.REGULAR, MessageOrder.ASC, "Hello world!");

        MessageService.process(Severity.MINOR, MessageOrder.DESC, Doubling.DISTINCT, "Hello world!", "Hello again", "Hello again", "Hello again3", "Hello again3");
        MessageService.process(Severity.MINOR, MessageOrder.DESC, Doubling.DOUBLES, "Hello world!", "Hello again", "Hello again", "Hello again2", "Hello again2");

    }
}