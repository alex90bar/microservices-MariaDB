package com.example.microservicesmariadb.service;

import com.example.microservicesmariadb.dto.MessageDto;
import com.example.microservicesmariadb.mapper.MessageMapper;
import com.example.microservicesmariadb.repository.MessageRepository;
import com.example.microservicesmariadb.websocket.MyStompSessionHandler;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  public void postMessage(MessageDto dto) {
    repository.save(mapper.toEntity(dto));
  }

  public void startMessaging() {
    String URL = "ws://localhost:8085/chat";

    WebSocketClient client = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(client);

    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    StompSessionHandler sessionHandler = new MyStompSessionHandler();
    stompClient.connect(URL, sessionHandler);


  }
}


