package com.example.microservicesmariadb.dto;

import java.time.ZonedDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * MessageDto
 *
 * @author alex90bar
 */

@Data
@RequiredArgsConstructor
public class MessageDto {

  private Integer id;
  private Integer sessionId;
  private ZonedDateTime MC1Timestamp;
  private ZonedDateTime MC2Timestamp;
  private ZonedDateTime MC3Timestamp;
  private ZonedDateTime endTimestamp;

}


