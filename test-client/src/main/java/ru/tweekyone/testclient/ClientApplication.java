package ru.tweekyone.testclient;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.tweekyone.testclient.handler.MessageHandler;

@AllArgsConstructor
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ClientApplication.class, args);

        MessageHandler messageHandler = applicationContext.getBean(MessageHandler.class);
        messageHandler.handleMessages();

        SpringApplication.exit(applicationContext);
    }

}
