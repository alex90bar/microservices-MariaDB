package com.example.microserviceskafka.client;

/**
 * FeignClient
 *
 * @author alex90bar
 */

import com.example.microserviceskafka.dto.MessageSocket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-maria", url =  "${feign-client.maria-host}")
public interface FeignMariaClient {

  @PostMapping("/postFromKafka")
  void postMessage(@RequestBody MessageSocket messageSocket);

}


