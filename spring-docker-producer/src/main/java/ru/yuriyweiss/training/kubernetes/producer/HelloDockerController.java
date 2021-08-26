package ru.yuriyweiss.training.kubernetes.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Slf4j
@RestController
public class HelloDockerController {

    private static final String TOPIC_NAME = "spring.docker.producer.out";

    private final KafkaProducer kafkaProducer;

    @Autowired
    public HelloDockerController( KafkaProducer kafkaProducer ) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping( "/hello/{name}" )
    public String hello( @PathVariable String name ) {
        log.info( "incoming get request: " + name );
        String message = "request: " + name + " " + LocalDateTime.now().format( ISO_LOCAL_DATE_TIME );
        kafkaProducer.send( TOPIC_NAME, message );
        return "processed " + name;
    }
}
