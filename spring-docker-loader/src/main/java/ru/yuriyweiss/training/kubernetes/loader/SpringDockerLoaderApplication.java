package ru.yuriyweiss.training.kubernetes.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@SpringBootApplication( scanBasePackages = { "ru.yuriyweiss.training.kubernetes" } )
@EnableScheduling
public class SpringDockerLoaderApplication {

    @Autowired
    private MessagesLoader messagesLoader;

    public static void main( String[] args ) {
        SpringApplication.run( SpringDockerLoaderApplication.class, args );
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            log.info( "started" );
            if ( args.length < 2 ) {
                log.info( "Usage: <run messageLoader command> <messagesPerSecond> <producerUrl>" );
                return;
            }
            // producerUrl: "http://localhost:8080/hello/"
            messagesLoader.run( Integer.parseInt( args[0] ), args[1] );
        };
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
