package com.bahadirakin.sqs.operation;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Creates operations for different use cases.
 */
@Singleton
@Component(modules = OperationsModule.class)
public interface OperationFactory {

    /**
     * Creates transfer operation with all dependencies.
     *
     * @return a transfer operation instance.
     */
    TransferOperation createTransferOperation();
}
