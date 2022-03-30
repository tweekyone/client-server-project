package ru.tweekyone.testserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tweekyone.testserver.entity.Message;
import ru.tweekyone.testserver.entity.dto.request.RequestMessage;
import ru.tweekyone.testserver.service.MessageService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> createMessage(@Valid @RequestBody RequestMessage requestMessage) {
        Message result = messageService.save(requestMessage);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
