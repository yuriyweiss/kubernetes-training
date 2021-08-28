package ru.yuriyweiss.training.kubernetes.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yuriyweiss.training.kubernetes.common.PerformanceLogger;

import java.util.UUID;

@Slf4j
@Component
public class MessagesLoader {

    private final WebClient webClient;
    private final PerformanceLogger performanceLogger;

    @Autowired
    public MessagesLoader(
            WebClient webClient,
            PerformanceLogger performanceLogger ) {
        this.webClient = webClient;
        this.performanceLogger = performanceLogger;
    }

    public void run( int messagesPerSecond, String producerUrl ) {
        while ( true ) { // NOSONAR
            long startTime = System.currentTimeMillis();
            for ( int i = 0; i < messagesPerSecond; i++ ) {
                String message = UUID.randomUUID().toString().replace( "-", "" );
                webClient.get().uri( producerUrl + message )
                        .retrieve().bodyToMono( String.class )
                        .subscribe( s -> {
                            log.debug( "response: {}", s );
                            performanceLogger.onMessage();
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
        }
    }
}
