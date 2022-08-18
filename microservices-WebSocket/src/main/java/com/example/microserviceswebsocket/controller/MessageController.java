package com.example.microserviceswebsocket.controller;

import com.example.microserviceswebsocket.dto.MessageSocket;
import com.example.microserviceswebsocket.service.KafkaService;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * MessageController
 *
 * @author alex90bar
 */

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

  private final KafkaService kafkaService;

  @MessageMapping("/chat")
  public void send(MessageSocket message) throws Exception {
    message.setMC2Timestamp(ZonedDateTime.now().toString());
    kafkaService.sendMessage(message);
  }

}


