package com.example.microservicesmariadb.websocket;

import com.example.microservicesmariadb.dto.MessageSocket;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

  private Integer sessionId;

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return MessageSocket.class;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    MessageSocket msg = (MessageSocket) payload;
  }

  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    session.subscribe("/topic/messages", this);
    session.send("/app/chat", getSampleMessage(sessionId));
  }

  @Override
  public void handleException(StompSession session, StompCommand command, StompHeaders headers,
      byte[] payload, Throwable exception) {
    log.error("Exception: " + exception);
  }

  private MessageSocket getSampleMessage(Integer sessionId){
    MessageSocket msg = new MessageSocket();
    msg.setSessionId(sessionId);
    msg.setMC1Timestamp(ZonedDateTime.now().toString());
    return msg;
  }
}


