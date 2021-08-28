package ru.yuriyweiss.training.kubernetes.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class PerformanceLogger {

    private final AtomicLong messagesTotal = new AtomicLong( 0L );
    private final AtomicLong messagesInInterval = new AtomicLong( 0L );
    private long prevLogTime = System.currentTimeMillis();

    @Scheduled( cron = "*/10 * * * * *" )
    public void logMemory() {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info( "memory used: {}, total: {}", usedMemory, Runtime.getRuntime().totalMemory() );
    }

    public void onMessage() {
        messagesTotal.getAndIncrement();
        messagesInInterval.getAndIncrement();
    }

    @Scheduled( cron = "*/5 * * * * *" )
    public void logMessagesProcessed() {
        long currentTime = System.currentTimeMillis();
        if ( currentTime - prevLogTime > 0L ) {
            double processedPerSecond = messagesInInterval.get() / ( ( currentTime - prevLogTime ) / 1000.0 );
            log.info( "messages per second: {}, total: {}", processedPerSecond, messagesTotal.get() );
            prevLogTime = System.currentTimeMillis();
            messagesInInterval.set( 0L );
        }
    }
}
