package ru.tweekyone.testclient.util;

import ru.tweekyone.testclient.entity.ResponseMessage;

import java.util.List;

public class MessagesPrinter {

    public void printSavedMessage(ResponseMessage responseMessage) {
        System.out.println("_________________________");
        System.out.println("Successful saved message:");
        System.out.println(responseMessage);
        System.out.println("_________________________");
        System.out.println("Waiting for next request:");
    }

    public void printFoundMessages(List<ResponseMessage> responseMessages) {
        System.out.println("_________________________");
        System.out.println("Found messages:");
        responseMessages.stream().forEach(System.out::println);
        System.out.println("_________________________");
        System.out.println("Waiting for next request:");
    }
}
