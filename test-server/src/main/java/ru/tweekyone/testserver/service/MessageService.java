package ru.tweekyone.testserver.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tweekyone.testserver.entity.Message;
import ru.tweekyone.testserver.entity.dto.request.RequestMessage;
import ru.tweekyone.testserver.repository.MessageRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message save(RequestMessage requestMessage) {
        Message result = Message.builder().message(requestMessage.getMessage()).dateTime(LocalDateTime.now()).build();
        return messageRepository.save(result);
    }
}
