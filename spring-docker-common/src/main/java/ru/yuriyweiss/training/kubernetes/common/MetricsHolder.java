package ru.yuriyweiss.training.kubernetes.common;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetricsHolder {

    private final MeterRegistry meterRegistry;

    private Counter messagesTotal;
    private Map<Integer, Counter> messagesByPartitions = new HashMap<>();

    public MetricsHolder( MeterRegistry meterRegistry ) {
        this.meterRegistry = meterRegistry;
        initMetrics();
    }

    public void onMessage() {
        messagesTotal.increment();
    }

    public void onMessageConsumed( Integer partition ) {
        Counter messagesByPartition = messagesByPartitions.get( partition );
        if ( messagesByPartition != null ) {
            messagesByPartition.increment();
        }
    }

    private void initMetrics() {
        messagesTotal = Counter.builder( "messages.total" )
                .description( "Total count of messages, processed by application" )
                .register( meterRegistry );
        for ( int i = 0; i < 10; i++ ) {
            Counter messagesByPartition = Counter.builder( "messages.by.partition" )
                    .tag( "partition", Integer.toString( i ) )
                    .description( "Total count of messages, consumed from partition" )
                    .register( meterRegistry );
            messagesByPartitions.put( i, messagesByPartition );
        }
    }
}
