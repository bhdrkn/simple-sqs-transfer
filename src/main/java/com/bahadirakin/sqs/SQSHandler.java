package com.bahadirakin.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class SQSHandler implements RequestHandler<SQSEvent, Void>{
    @Override
    public Void handleRequest(SQSEvent event, Context context) {

        System.out.println(event);

        if (event == null) {
            System.out.println("Event is null!");
            return null;
        }

        if (event.getRecords() == null) {
            System.out.println("Records are null");
            return null;
        }

        for(SQSEvent.SQSMessage msg : event.getRecords()){

            System.out.println("Message Received: " + msg.getMessageId());
        }
        return null;
    }
}
