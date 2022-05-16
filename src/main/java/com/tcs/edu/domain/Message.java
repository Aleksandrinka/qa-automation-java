package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;

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

    @Override
    public String toString() {
        return severity + " message: " + body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return body.equals(message.body) && severity == message.severity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, severity);
    }
}
