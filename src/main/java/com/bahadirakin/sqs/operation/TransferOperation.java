package com.bahadirakin.sqs.operation;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Transfers a SQS message to another queue.
 */
public class TransferOperation {

    private static final Logger LOGGER = LogManager.getLogger(TransferOperation.class);

    /**
     * SQS client.
     */
    private final AmazonSQS amazonSQS;

    /**
     * Destination queue url.
     */
    private final String destinationQueueUrl;

    /**
     * Constructor.
     *
     * @param amazonSQS           sqs client.
     * @param destinationQueueUrl destination queue url.
     */
    @Inject
    public TransferOperation(AmazonSQS amazonSQS,
                             @Named("destinationQueueUrl") String destinationQueueUrl) {
        this.amazonSQS = amazonSQS;
        this.destinationQueueUrl = destinationQueueUrl;
    }

    /**
     * Transfers a message to another queue.
     *
     * @param message message from lambda event.
     */
    public void transfer(SQSEvent.SQSMessage message) {
        LOGGER.info("Transferring message with id {} to {}. ", message.getMessageId(), destinationQueueUrl);

        SendMessageResult sendMessageResult = this.amazonSQS.sendMessage(new SendMessageRequest()
                .withQueueUrl(destinationQueueUrl)
                .withMessageBody(message.getBody()));

        LOGGER.info("Message with id {} transferred and now have {} id", message.getMessageId(),
                sendMessageResult.getMessageId());
    }
}
