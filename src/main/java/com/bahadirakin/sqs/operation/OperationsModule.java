package com.bahadirakin.sqs.operation;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Modules which defines dependencies of operations.
 */
@Module
public class OperationsModule {

    /**
     * While creating functions serverless stores destination queue url as an environment parameter.
     *
     * @return destination queue name.
     */
    @Provides
    @Singleton
    @Named("destinationQueueUrl")
    String destinationQueue() {
        return System.getenv("destinationQueueUrl");
    }


    /**
     * Amazon SQS client.
     * <p>
     * It has very minimal connections because there will be single handler per function.
     *
     * @return amazon sqs client.
     */
    @Provides
    @Singleton
    AmazonSQS sqsClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withMaxConnections(1)
                .withMaxErrorRetry(10);
        return AmazonSQSClientBuilder.standard()
                .withClientConfiguration(clientConfiguration)
                .build();
    }
}
