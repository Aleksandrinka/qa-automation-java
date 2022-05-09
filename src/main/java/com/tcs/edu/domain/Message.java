package com.tcs.edu.domain;

import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;

public class Message {
    private final String body;
    private final Severity severity;

    public Message(String body, Severity severity) {
        this.body = body;
        this.severity = severity;
    }

    public String getBody() {
        return body;
    }

    public Severity getSeverity() {
        return severity;
    }

}
