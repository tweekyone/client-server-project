package ru.tweekyone.testserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tweekyone.testserver.entity.Message;
import ru.tweekyone.testserver.entity.dto.request.RequestMessage;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @PostMapping("/message")
    public ResponseEntity<Message> receiveMessage(@RequestBody RequestMessage message) {
        Message result = new Message(1L, message.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
