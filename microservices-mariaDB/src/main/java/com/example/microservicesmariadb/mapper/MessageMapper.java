package com.example.microservicesmariadb.mapper;

import com.example.microservicesmariadb.dto.MessageDto;
import com.example.microservicesmariadb.model.MessageEntity;
import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MessageMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface MessageMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "MC1Timestamp", expression = "java(newTime())")
  @Mapping(target = "MC2Timestamp", expression = "java(newTime())")
  @Mapping(target = "MC3Timestamp", expression = "java(newTime())")
  MessageEntity toEntity(MessageDto dto);

  default ZonedDateTime newTime() {
    return ZonedDateTime.now();
  }
}
