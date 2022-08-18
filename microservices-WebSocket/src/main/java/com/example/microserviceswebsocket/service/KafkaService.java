package com.example.microserviceswebsocket.service;

import com.example.microserviceswebsocket.dto.MessageSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * KafkaService
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

  private final KafkaTemplate<String, MessageSocket> kafkaTemplate;

  public void sendMessage(MessageSocket msg) {
    kafkaTemplate.send("microservices", msg);
  }

}


