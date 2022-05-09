package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.service.MessageService;

class Application {
    public static void main(String[] args) {

        MessageService messageService = new MessageService();

        messageService.process(MessageOrder.ASC, new Message("Hello world!", Severity.MAJOR), null,
                new Message("And another one major hello", Severity.MAJOR));
        messageService.process(MessageOrder.ASC, new Message("Hello world!", Severity.MINOR));
        messageService.process(MessageOrder.ASC, new Message("Hello world!", Severity.REGULAR));
        messageService.process(MessageOrder.ASC, new Message(null, Severity.MAJOR));
        messageService.process(MessageOrder.ASC, new Message("null", Severity.MAJOR));
        messageService.process(MessageOrder.DESC, new Message("Hello world!", Severity.MINOR),
                new Message("Hello again", Severity.MINOR), new Message("And another one minor hello", Severity.MINOR));
        messageService.process(MessageOrder.ASC, new Message("Hello world!", Severity.REGULAR));
        messageService.process( MessageOrder.DESC, Doubling.DISTINCT, new Message("Hello world!", Severity.MINOR),
                new Message("Hello again", Severity.MINOR), new Message("Hello again", Severity.MINOR),
                new Message("Hello again3", Severity.MINOR), new Message("Hello again3", Severity.MINOR));
        messageService.process(MessageOrder.DESC, Doubling.DOUBLES, new Message("Hello world!", Severity.MINOR),
                new Message("Hello again", Severity.MINOR), new Message("Hello again", Severity.MINOR),
                new Message("Hello again2", Severity.MINOR), new Message("Hello again2", Severity.MINOR));

    }
}