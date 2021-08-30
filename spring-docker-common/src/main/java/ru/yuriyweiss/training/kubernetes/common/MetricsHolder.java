package ru.yuriyweiss.training.kubernetes.common;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MetricsHolder {

    private final MeterRegistry meterRegistry;

    private Counter messagesTotal;

    public MetricsHolder( MeterRegistry meterRegistry ) {
        this.meterRegistry = meterRegistry;
        initMetrics();
    }

    public void onMessage() {
        messagesTotal.increment();
    }

    private void initMetrics() {
        messagesTotal = Counter.builder( "messages.total" )
                .description( "Total count of messages, processed by application" )
                .register( meterRegistry );
    }
}
