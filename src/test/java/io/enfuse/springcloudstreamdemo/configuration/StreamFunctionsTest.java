package io.enfuse.springcloudstreamdemo.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StreamFunctionsTest {
    private StreamFunctions subject;

    @BeforeEach
    void setUp() {
        subject = new StreamFunctions();
    }

    @Test
    void relay_givenText_returnsTransformedText() {
        String result = subject.relay().apply("FOOBAR");
        assertThat(result).isEqualTo("FOOBAR - Transformed!");
    }

    @Test
    void receive_givenAnyText_returnsText() {
        String result = subject.receive().apply("FIZZBUZZ");
        assertThat(result).isEqualTo("FIZZBUZZ");
    }
}