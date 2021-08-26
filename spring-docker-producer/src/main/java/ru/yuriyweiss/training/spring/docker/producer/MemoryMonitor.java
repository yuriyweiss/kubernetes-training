package ru.yuriyweiss.training.spring.docker.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemoryMonitor {

    @Scheduled(cron="*/10 * * * * *")
    public void logMemory() {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        log.info("memory used: {}, total: {}", usedMemory, Runtime.getRuntime().totalMemory());
    }
}
