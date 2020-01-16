package com.linuxacademy.azurestoragedd.queue;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

public class Consumer {

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

            // Get a reference to the queue.
            CloudQueue queue = queueClient.getQueueReference(queueName);

            // Process all messages in the queue until no messages remain.
            CloudQueueMessage retrievedMessage;
            do {
                // Retrieve the next available message in the queue.
                retrievedMessage = queue.retrieveMessage();
                if (retrievedMessage != null) {
                    // Process and delete the message.
                    System.out.println(retrievedMessage.getId() + ":" + retrievedMessage.getMessageContentAsString());
                    queue.deleteMessage(retrievedMessage);
                }
            } while (retrievedMessage != null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}