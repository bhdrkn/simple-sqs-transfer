package com.bahadirakin.sqs.operation;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TransferOperationTest {

    private static final String TEST_QUEUE_URL = "test-queue-url";

    @Mock
    private AmazonSQS amazonSQS;

    @Mock
    private SendMessageResult mockResult;

    @Captor
    private ArgumentCaptor<SendMessageRequest> requestCaptor;

    private TransferOperation transferOperation;

    @BeforeEach
    void setUp() {
        transferOperation = new TransferOperation(amazonSQS, TEST_QUEUE_URL);
    }

    @Test
    void shouldTransferGivenMessageToDestinationQueue() {
        // Given
        SQSEvent.SQSMessage sqsMessage = new SQSEvent.SQSMessage();
        sqsMessage.setBody("Hello, World!");

        given(amazonSQS.sendMessage(any(SendMessageRequest.class))).willReturn(mockResult);

        // When
        transferOperation.transfer(sqsMessage);

        // Then
        then(amazonSQS).should().sendMessage(requestCaptor.capture());
        assertEquals(requestCaptor.getValue().getMessageBody(), "Hello, World!");
        assertEquals(requestCaptor.getValue().getQueueUrl(), TEST_QUEUE_URL);
    }
}