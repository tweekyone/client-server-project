package ru.tweekyone.testserver.entity.dto.request;

public class RequestMessage {
    private String message;

    public RequestMessage() {
    }

    public RequestMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
