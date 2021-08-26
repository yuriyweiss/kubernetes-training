package ru.yuriyweiss.training.kubernetes.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Slf4j
@Component
public class MessagesLoader {

    @Value( "${messages.loader.rate.per.second}" )
    private int messagesPerSecond;

    private final WebClient webClient;

    @Autowired
    public MessagesLoader( WebClient webClient ) {
        this.webClient = webClient;
    }

    public void run() {
        while ( true ) { // NOSONAR
            long startTime = System.currentTimeMillis();
            for ( int i = 0; i < messagesPerSecond; i++ ) {
                String message = UUID.randomUUID().toString().replace( "-", "" );
                webClient.get().uri( "http://localhost:8080/hello/" + message )
                        .retrieve().bodyToMono( String.class )
                        .subscribe( s -> log.info( "response: {}", s ) );
            }
            long finishTime = System.currentTimeMillis();
            long timeToSleep = Math.max( 1000L - ( finishTime - startTime ), 0L );
            if ( timeToSleep > 0 ) {
                try {
                    Thread.sleep( timeToSleep );
                } catch ( InterruptedException e ) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
