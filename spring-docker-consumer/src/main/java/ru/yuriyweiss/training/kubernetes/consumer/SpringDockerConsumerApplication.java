package ru.yuriyweiss.training.kubernetes.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.TimeZone;

@SpringBootApplication( scanBasePackages = { "ru.yuriyweiss.training.kubernetes" } )
@EnableKafka
public class SpringDockerConsumerApplication {

    public static void main( String[] args ) {
        TimeZone.setDefault( TimeZone.getTimeZone( "Europe/Moscow" ) );
        SpringApplication.run( SpringDockerConsumerApplication.class, args );
    }
}
