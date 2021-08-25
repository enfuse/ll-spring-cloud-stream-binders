package io.enfuse.springcloudstreamdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class StreamFunctions {
    @Bean
    public Function<String,String> relay() {
        return message -> {
            log.info("Message transforming!");
            return message + " - Transformed!";
        };
    }

    @Bean
    public Function<String,String> receive() {
        return message -> {
            log.info("Final message: " + message);
            return message;
        };
    }
}
