package ru.tweekyone.testclient.handler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tweekyone.testclient.entity.RequestMessage;
import ru.tweekyone.testclient.entity.ResponseMessage;
import ru.tweekyone.testclient.exceptions.IncorrectInputRequestException;
import ru.tweekyone.testclient.exceptions.MessageNotCreatedException;
import ru.tweekyone.testclient.exceptions.MessagesNotFountException;
import ru.tweekyone.testclient.service.WebClientService;
import ru.tweekyone.testclient.util.InputMessageParser;
import ru.tweekyone.testclient.util.MessagesPrinter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    @NonNull
    @Autowired
    private WebClientService webClientService;
    private Scanner scanner;
    private DateTimeFormatter dateTimeFormatter;
    private MessagesPrinter messagesPrinter;

    @PostConstruct
    public void init() {
        scanner = new Scanner(System.in);
        dateTimeFormatter = DateTimeFormatter.ofPattern(WebClientService.DATE_TIME_PATTERN);
        messagesPrinter = new MessagesPrinter();
    }

    public void handleMessages() {
        Map<String, String> inputMessage;
        System.out.println("Enter request:");

        try {
            inputMessage = InputMessageParser.parseInputMessage(scanner.nextLine());
            Set<String> keys = inputMessage.keySet();
            while (!keys.contains("exit")) {

                if (keys.contains("message")) {
                    RequestMessage requestMessage = new RequestMessage(inputMessage.get("message"));
                    ResponseMessage result = webClientService.createMessage(requestMessage);
                    messagesPrinter.printSavedMessage(result);

                    inputMessage = InputMessageParser.parseInputMessage(scanner.nextLine());
                    keys = inputMessage.keySet();

                } else if (keys.contains("find")) {
                    List<ResponseMessage> result = new LinkedList<>();

                    if (keys.contains("id") && (keys.contains("from") || keys.contains("to"))) {
                        throw new IncorrectInputRequestException();
                    } else if (keys.contains("id")) {
                        result = webClientService.getMessageById(Long.valueOf(inputMessage.get("id")));
                    } else if (keys.contains("from") || keys.contains("to")) {
                        result = webClientService.getMessageByDate(
                                keys.contains("from") ? LocalDateTime.parse(inputMessage.get("from"), dateTimeFormatter) : null,
                                keys.contains("to") ? LocalDateTime.parse(inputMessage.get("to"), dateTimeFormatter) : null
                        );
                    }

                        messagesPrinter.printFoundMessages(result);

                        inputMessage = InputMessageParser.parseInputMessage(scanner.nextLine());
                        keys = inputMessage.keySet();
                } else {
                    throw new IncorrectInputRequestException();
                }
            }
        } catch (IncorrectInputRequestException e) {
            System.out.println(e.getMessage());
        } catch (MessageNotCreatedException e) {
            System.out.println(e.getMessage());
        } catch (MessagesNotFountException e) {
            System.out.println(e.getMessage());
        }
    }

    @PreDestroy
    public void destroy() {
        scanner.close();
    }
}
