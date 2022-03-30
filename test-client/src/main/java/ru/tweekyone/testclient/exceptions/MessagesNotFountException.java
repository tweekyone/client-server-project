package ru.tweekyone.testclient.exceptions;

import java.time.LocalDateTime;

public class MessagesNotFountException extends Exception{
    public MessagesNotFountException(Long id) {
        super(String.format("Messages with id %d not found!", id));
    }
    public MessagesNotFountException(LocalDateTime from, LocalDateTime to) {
        super(String.format("Messages with period from %s to %s not found!", from.toString(), to.toString()));
    }
}
