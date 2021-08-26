package ru.yuriyweiss.training.spring.docker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class KafkaConsumer {

    private static final String TOPIC_NAME = "spring.docker.producer.out";

    @KafkaListener( topics = TOPIC_NAME )
    public void listen( @Payload String payload,
            @Header( KafkaHeaders.RECEIVED_PARTITION_ID ) Integer partition,
            @Header( KafkaHeaders.OFFSET ) Long offset ) {
        String message = payload + " " + LocalDateTime.now().format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ) +
                " partition: " + partition + " offset: " + offset;
        log.info( message );
    }
}
