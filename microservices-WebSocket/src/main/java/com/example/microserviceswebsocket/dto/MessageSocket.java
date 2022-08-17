package com.example.microserviceswebsocket.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * MessageSocket
 *
 * @author alex90bar
 */

@Data
@RequiredArgsConstructor
public class MessageSocket {

  private Integer id;
  private Integer sessionId;
  private String MC1Timestamp;
  private String MC2Timestamp;
}


