package com.bahadirakin.sqs;

import com.bahadirakin.sqs.operation.OperationFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SQSHandlerTest {

    @Mock
    private OperationFactory operationFactory;

    @Test
    void handleRequestShouldReturnNull() {
        // Given
        SQSHandler handler = new SQSHandler(operationFactory);

        // When
        Void aVoid = handler.handleRequest(null, null);

        // Then
        assertNull(aVoid);
        then(operationFactory).shouldHaveZeroInteractions();
    }
}