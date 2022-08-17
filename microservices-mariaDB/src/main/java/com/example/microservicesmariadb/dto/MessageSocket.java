package com.example.microservicesmariadb.dto;

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
  private String MC3Timestamp;
  private String endTimestamp;
}


