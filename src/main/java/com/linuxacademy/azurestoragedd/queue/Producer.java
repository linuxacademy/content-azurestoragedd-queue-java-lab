package com.linuxacademy.azurestoragedd.queue;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

public class Producer {

    public static void main(String[] args) {
        final String queueName = "myqueue";
        // Create the connection string for the storage account.
        final String storageConnectionString = "DefaultEndpointsProtocol=http;" +
            "AccountName=webconfigtvb7zm6l4lloo;" +
            "AccountKey=***REMOVED***";
        try {
            //Retrieve the storage account.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

            // Instantiate a queue client.
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            // Get a reference to the queue, and create it if it does not already exist.
            CloudQueue queue = queueClient.getQueueReference(queueName);
            queue.createIfNotExists();

            // Add a new message to the queue.
            CloudQueueMessage message = new CloudQueueMessage("Hello, World!");
            queue.addMessage(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
