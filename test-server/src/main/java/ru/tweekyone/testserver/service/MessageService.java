package ru.tweekyone.testserver.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tweekyone.testserver.repository.MessageRepository;

@Service
@AllArgsConstructor
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


}
