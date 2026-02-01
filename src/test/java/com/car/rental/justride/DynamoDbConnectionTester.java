package com.car.rental.justride;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@Component
public class DynamoDbConnectionTester implements CommandLineRunner {

    private final DynamoDbClient dynamoDbClient;

    // Spring injects the client we configured in Step 3
    public DynamoDbConnectionTester(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("--- Attempting to connect to DynamoDB Local ---");
            // Try an actual operation against the database
            ListTablesResponse response = dynamoDbClient.listTables();
            System.out.println("--- SUCCESS! Connected to DynamoDB Local ---");
            System.out.println("Found tables: " + response.tableNames());
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            System.err.println("--- FAILURE! Could not connect to DynamoDB Local ---");
            e.printStackTrace();
        }
    }
}
