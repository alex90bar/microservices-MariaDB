package com.example.microservicesmariadb.websocket;

import com.example.microservicesmariadb.dto.MessageSocket;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * MyStompSessionHandler
 *
 * @author alex90bar
 */

@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return MessageSocket.class;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    MessageSocket msg = (MessageSocket) payload;
    log.info("Received " + msg.getId() + " date " + msg + msg.getMC1Timestamp());
  }

  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    log.info("New session established : " + session.getSessionId());
    session.subscribe("/topic/messages", this);
    log.info("Subscribed to /topic/messages");
    session.send("/app/chat", getSampleMessage());
    log.info("Message sent to websocket server");
  }

  @Override
  public void handleException(StompSession session, StompCommand command, StompHeaders headers,
      byte[] payload, Throwable exception) {
    log.error("Exception: " + exception);
  }

  private MessageSocket getSampleMessage(){
    MessageSocket msg = new MessageSocket();
    msg.setId(1);
    msg.setSessionId(1);
    msg.setMC1Timestamp(ZonedDateTime.now().toString());
    return msg;
  }
}


