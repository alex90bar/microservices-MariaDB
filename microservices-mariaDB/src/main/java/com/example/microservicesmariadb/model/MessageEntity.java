package com.example.microservicesmariadb.model;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message
 *
 * @author alex90bar
 */

@Getter
@Setter
@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "session_id", nullable = false)
  private Integer sessionId;

  @Column(name = "MC1_timestamp", nullable = false)
  private ZonedDateTime MC1Timestamp;

  @Column(name = "MC2_timestamp", nullable = false)
  private ZonedDateTime MC2Timestamp;

  @Column(name = "MC3_timestamp", nullable = false)
  private ZonedDateTime MC3Timestamp;

  @Column(name = "end_timestamp", nullable = false)
  private ZonedDateTime endTimestamp;

}


