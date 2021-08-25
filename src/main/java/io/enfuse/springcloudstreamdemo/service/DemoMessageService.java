package io.enfuse.springcloudstreamdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoMessageService {
    private StreamBridge streamBridge;

    public DemoMessageService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(Object data) {
        log.info("Sending message...");
        streamBridge.send("relay-in-0", data);
    }
}
