package com.bahadirakin.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.bahadirakin.sqs.operation.DaggerOperationFactory;
import com.bahadirakin.sqs.operation.OperationFactory;
import com.bahadirakin.sqs.operation.TransferOperation;

/**
 * SQS Handler for source queue.
 */
public class SQSHandler implements RequestHandler<SQSEvent, Void> {

    private final OperationFactory operationFactory;

    public SQSHandler() {
        this(DaggerOperationFactory.create());
    }

    SQSHandler(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    @Override
    public Void handleRequest(SQSEvent event, Context context) {

        if (event == null) {
            System.out.println("Event is null!");
            return null;
        }

        if (event.getRecords() == null) {
            System.out.println("Records are null");
            return null;
        }

        TransferOperation transferOperation = operationFactory.createTransferOperation();
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            transferOperation.transfer(msg);
        }

        return null;
    }
}
