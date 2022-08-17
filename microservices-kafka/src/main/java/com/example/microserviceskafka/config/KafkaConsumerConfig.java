package com.example.microserviceskafka.config;


import com.example.microserviceskafka.dto.MessageSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * KafkaConsumerConfig
 *
 * @author alex90bar
 */

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Bean
  public ConsumerFactory<String, MessageSocket> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        "messaging");
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        JsonDeserializer.class);
    props.put(
        JsonDeserializer.TRUSTED_PACKAGES,
        "com.example.microserviceswebsocket.dto");
    props.put(
        JsonDeserializer.USE_TYPE_INFO_HEADERS,
        false);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
        "com.example.microserviceskafka.dto.MessageSocket");
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, MessageSocket>
  kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, MessageSocket> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}


