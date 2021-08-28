package ru.yuriyweiss.training.kubernetes.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class MessagesLoader {

    private final WebClient webClient;

    private final AtomicLong messagesProcessed = new AtomicLong( 0L );
    private final AtomicLong messagesInInterval = new AtomicLong( 0L );

    @Autowired
    public MessagesLoader( WebClient webClient ) {
        this.webClient = webClient;
    }

    public void run(final int messagesPerSecond) {
        long logTime = System.currentTimeMillis();
        while ( true ) { // NOSONAR
            long startTime = System.currentTimeMillis();
            for ( int i = 0; i < messagesPerSecond; i++ ) {
                String message = UUID.randomUUID().toString().replace( "-", "" );
                webClient.get().uri( "http://localhost:8080/hello/" + message )
                        .retrieve().bodyToMono( String.class )
                        .subscribe( s -> {
                            log.debug( "response: {}", s );
                            messagesProcessed.getAndIncrement();
                            messagesInInterval.getAndIncrement();
                        } );
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
            long currentTime = System.currentTimeMillis();
            if ( currentTime - logTime > 5000L ) {
                double processedPerSecond = messagesInInterval.get() / ( ( currentTime - logTime ) / 1000.0 );
                log.info( "messages per second: {}, total: {}", processedPerSecond, messagesProcessed.get() );
                logTime = System.currentTimeMillis();
                messagesInInterval.set( 0L );
            }
        }
    }
}
