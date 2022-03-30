package ru.tweekyone.testclient.exceptions;

public class MessageNotCreatedException extends Exception{
    public MessageNotCreatedException(String message) {
        super(String.format("Message \"%s\" not created!", message));
    }
}
