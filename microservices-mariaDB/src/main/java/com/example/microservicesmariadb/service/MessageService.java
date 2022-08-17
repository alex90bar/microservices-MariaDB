package com.example.microservicesmariadb.service;

import com.example.microservicesmariadb.dto.MessageDto;
import com.example.microservicesmariadb.dto.MessageSocket;
import com.example.microservicesmariadb.mapper.MessageMapper;
import com.example.microservicesmariadb.repository.MessageRepository;
import com.example.microservicesmariadb.websocket.MyStompSessionHandler;
import java.time.ZonedDateTime;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * MessageService
 *
 * @author alex90bar
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository repository;
  private final MessageMapper mapper;
  private Integer sessionId;
  private StompSessionHandler sessionHandler;
  private WebSocketStompClient stompClient;

  {
    sessionId = 0;
  }

  public void postMessage(MessageDto message) {
    message.setEndTimestamp(ZonedDateTime.now());
    repository.save(mapper.toEntity(message));
    log.info("Message: " + message);
  }

  public void startMessaging() {

    String URL = "ws://websocket-app:8085/chat";

    WebSocketClient client = new StandardWebSocketClient();
    stompClient = new WebSocketStompClient(client);

    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    sessionHandler = new MyStompSessionHandler(sessionId);
    stompClient.connect(URL, sessionHandler);

  }

  public Page<MessageDto> getAll(Pageable page) {
    sessionId++;
    stompClient.stop();
    return repository.findAll(page).map(mapper::toDto);
  }
}


