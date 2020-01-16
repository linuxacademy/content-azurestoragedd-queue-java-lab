package com.linuxacademy.azurestoragedd.queue;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

public class Producer {

    public static void main(String[] args) {
        final String queueName = "incoming-items";
        // Get the storage account name and access key from environment variables.
        String storageAccountName = System.getenv("STORAGE_ACCOUNT_NAME");
        String storageAccountKey = System.getenv("STORAGE_ACCOUNT_KEY");
        // Create the connection string for the storage account.
        final String storageConnectionString = "DefaultEndpointsProtocol=http;" +
            "AccountName=" + storageAccountName + ";" +
            "AccountKey=" + storageAccountKey;
        try {
            //Retrieve the storage account.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

            // Instantiate a queue client.
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            // Get a reference to the queue, and create it if it does not already exist.
            CloudQueue queue = queueClient.getQueueReference(queueName);
            queue.createIfNotExists();

            // Add some new messages to the queue.
            queue.addMessage(new CloudQueueMessage("Item 4457: Bowling Trophy"));
            queue.addMessage(new CloudQueueMessage("Item 4458: Potato Collection"));
            queue.addMessage(new CloudQueueMessage("Item 4459: 11 Herbs and Spices Recipe"));
            queue.addMessage(new CloudQueueMessage("Item 4460: Ravenclaw's Diadem"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
