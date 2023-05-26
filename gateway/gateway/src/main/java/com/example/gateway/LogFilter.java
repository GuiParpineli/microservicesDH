package com.example.gateway;

import org.apache.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    private static Logger log = Logger.getLogger(LogFilter.class.getName());
    private static AtomicInteger COUNT_CALL_GATEWAY = new AtomicInteger(0);

    public LogFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("TOTAL DE REQUESTS: " + COUNT_CALL_GATEWAY.incrementAndGet());
        }));
    }

    public static class Config {
        //Put the configuration properties
    }
}
