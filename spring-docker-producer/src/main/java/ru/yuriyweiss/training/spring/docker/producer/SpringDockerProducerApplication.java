package ru.yuriyweiss.training.spring.docker.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class SpringDockerProducerApplication {

    public static void main( String[] args ) {
        TimeZone.setDefault( TimeZone.getTimeZone( "Europe/Moscow" ) );
        SpringApplication.run( SpringDockerProducerApplication.class, args );
    }
}
