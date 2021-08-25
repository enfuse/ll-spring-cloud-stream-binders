package io.enfuse.springcloudstreamdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemoMessageServiceTest {
    private DemoMessageService subject;

    @Mock
    private StreamBridge streamBridge;

    @Captor
    private ArgumentCaptor<String> channelCaptor;
    @Captor
    private ArgumentCaptor<Object> payloadCaptor;

    @Test
    void send_givenDataObject_sendsWithStreamBridge() {
        when(streamBridge.send(any(), any())).thenReturn(true);
        subject = new DemoMessageService(streamBridge);

        subject.send("testData");
        verify(streamBridge).send(channelCaptor.capture(), payloadCaptor.capture());

        assertThat(channelCaptor.getValue()).isEqualTo("relay-in-0");
        assertThat(payloadCaptor.getValue().toString()).isEqualTo("testData");
    }
}