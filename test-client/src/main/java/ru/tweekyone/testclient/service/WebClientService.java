package ru.tweekyone.testclient.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tweekyone.testclient.entity.RequestMessage;
import ru.tweekyone.testclient.entity.ResponseMessage;
import ru.tweekyone.testclient.exceptions.MessageNotCreatedException;
import ru.tweekyone.testclient.exceptions.MessagesNotFountException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class WebClientService {

    @Autowired
    private WebClient webClient;
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public ResponseMessage createMessage(RequestMessage message) throws MessageNotCreatedException {
        return webClient.post()
                .uri("/message")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(message), RequestMessage.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .blockOptional(Duration.ofMillis(1500L)).orElseThrow(() ->
                    new MessageNotCreatedException(message.getMessage())
                );
    }

    public List<ResponseMessage> getMessageById(Long id) throws MessagesNotFountException{
        return webClient.get()
                .uri(String.format("/message/%d/id", id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ResponseMessage.class)
                .collectList()
                .blockOptional(Duration.ofMillis(1500l)).orElseThrow(() ->
                        new MessagesNotFountException(id)
                );
    }

    public List<ResponseMessage> getMessageByDate(LocalDateTime from, LocalDateTime to) throws MessagesNotFountException {
        return webClient.get()
                .uri(String.format("/message/period?from=%s&to=%s",
                        from == null ? null : from.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)),
                        to == null ? null : to.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ResponseMessage.class)
                .collectList()
                .blockOptional(Duration.ofMillis(1500l)).orElseThrow(() ->
                        new MessagesNotFountException(from, to)
                );
    }
}
