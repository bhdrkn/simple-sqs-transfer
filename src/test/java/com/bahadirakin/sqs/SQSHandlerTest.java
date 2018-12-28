package com.bahadirakin.sqs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class SQSHandlerTest {

    @Test
    void handleRequestShouldReturnNull() {
        // Given
        SQSHandler handler = new SQSHandler();

        // When
        Void aVoid = handler.handleRequest(null, null);

        // Then
        assertNull(aVoid);
    }
}