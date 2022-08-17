package com.example.microserviceskafka.service;

import com.example.microserviceskafka.client.FeignMariaClient;
import com.example.microserviceskafka.dto.MessageSocket;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * KafkaService
 *
 * @author alex90bar
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaService {

  private final FeignMariaClient feignMariaClient;

  @KafkaListener(topics = "microservices", groupId = "messaging")
  public void listenGroupFoo(MessageSocket message) {
    log.info("Received Message in group messaging: " + message.toString());
    message.setMC3Timestamp(ZonedDateTime.now().toString());
    feignMariaClient.postMessage(message);
  }

}


