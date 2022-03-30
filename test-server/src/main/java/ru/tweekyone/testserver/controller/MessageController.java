package ru.tweekyone.testserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.tweekyone.testserver.service.MessageService;

@RestController
@AllArgsConstructor
public class MessageController {

    @Autowired
    private MessageService messageService;


}
