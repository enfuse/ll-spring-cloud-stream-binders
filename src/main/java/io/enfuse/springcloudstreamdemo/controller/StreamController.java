package io.enfuse.springcloudstreamdemo.controller;

import io.enfuse.springcloudstreamdemo.dto.ResponseData;
import io.enfuse.springcloudstreamdemo.service.DemoMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StreamController {

    private final DemoMessageService demoMessageService;

    public StreamController(DemoMessageService demoMessageService) {
        this.demoMessageService = demoMessageService;
    }

    @RequestMapping(
            value = "/sendMessage/{payload}",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseData sendMessage(@PathVariable(value = "payload") String payload) {
        if (payload != null && !payload.isEmpty()) {
            log.debug("payload is not null");
            demoMessageService.send(payload);
            return new ResponseData(payload);
        }
        return null;
    }
}
