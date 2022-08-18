package com.example.microservicesmariadb.controller;

import com.example.microservicesmariadb.dto.MessageDto;
import com.example.microservicesmariadb.dto.MessageSocket;
import com.example.microservicesmariadb.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController
 *
 * @author alex90bar
 */

@RestController
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping("/postFromKafka")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody MessageDto message){
    messageService.postMessage(message);
  }

  @GetMapping("/start")
  public void startMessaging(){
    messageService.startMessaging();
  }

  @GetMapping("/stop")
  @ResponseStatus(HttpStatus.OK)
  public void stop(){
    messageService.pushStop();
  }

  @GetMapping("/messages")
  public ResponseEntity<List<MessageDto>> getAll(){
    return ResponseEntity.ok(messageService.getAll());
  }



}


