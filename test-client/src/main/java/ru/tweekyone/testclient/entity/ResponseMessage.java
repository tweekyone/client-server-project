package ru.tweekyone.testclient.entity;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private Long id;
    private String message;
    private LocalDateTime dateTime;
}
