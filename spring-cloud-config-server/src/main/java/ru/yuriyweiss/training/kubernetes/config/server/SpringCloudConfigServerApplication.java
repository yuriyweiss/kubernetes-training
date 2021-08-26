package ru.yuriyweiss.training.kubernetes.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.lang.management.ManagementFactory;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication {

    public static void main( String[] args ) {
        System.out.println( "------------ PID: " + ManagementFactory.getRuntimeMXBean().getName() ); // NOSONAR
        SpringApplication.run( SpringCloudConfigServerApplication.class, args );
    }
}
