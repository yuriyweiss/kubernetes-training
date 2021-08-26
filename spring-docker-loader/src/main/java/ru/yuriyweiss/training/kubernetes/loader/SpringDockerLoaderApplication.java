package ru.yuriyweiss.training.kubernetes.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@SpringBootApplication
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
            messagesLoader.run();
        };
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
