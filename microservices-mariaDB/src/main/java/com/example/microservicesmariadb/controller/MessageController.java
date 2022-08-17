package com.example.microservicesmariadb.controller;

import com.example.microservicesmariadb.dto.MessageDto;
import com.example.microservicesmariadb.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  @PostMapping("/postMessage")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody MessageDto dto){
    messageService.postMessage(dto);
  }

  @GetMapping("/start")
  public void startMessaging(){
    messageService.startMessaging();
  }

}


