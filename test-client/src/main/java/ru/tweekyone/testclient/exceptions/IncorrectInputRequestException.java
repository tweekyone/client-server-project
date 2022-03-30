package ru.tweekyone.testclient.exceptions;

public class IncorrectInputRequestException extends Exception {

    public IncorrectInputRequestException() {
        super(String.format("Incorrect input request! Must starts with \"message=...\" " +
                "or with \"find=id:...\" or with \"find=from:yyyy-MM-dd'T'HH:mm:ss to:yyyy-MM-dd'T'HH:mm:ss\""));
    }
}
