package com.car.rental.justride.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Profile("!test") // Ensure this doesn't run during tests
public class DynamoDBInitializer implements ApplicationRunner {

    private static final Logger LOGGER = Logger.getLogger(DynamoDBInitializer.class.getName());
    private final DynamoDbClient dynamoDbClient;

    public DynamoDBInitializer(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Checking if DynamoDB table needs to be created...");

        try {
            ListTablesResponse response = dynamoDbClient.listTables(ListTablesRequest.builder().limit(10).build());
            boolean tableExists = response.tableNames().stream().anyMatch(name -> name.equals("Car"));

            if (!tableExists) {
                LOGGER.info("'Car' table not found. Initializing database...");
                executeScript("create-dynamodb-table.sh");
                // Wait a moment for the table to become active
                TimeUnit.SECONDS.sleep(2);
                executeScript("add-car-item.sh");
                LOGGER.info("Database initialization complete.");
            } else {
                LOGGER.info("'Car' table already exists. Skipping initialization.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to check or initialize DynamoDB", e);
        }
    }

    private void executeScript(String scriptName) {
        try {
            File scriptFile = new File(System.getProperty("user.dir"), scriptName);
            if (!scriptFile.exists()) {
                LOGGER.warning("Script not found: " + scriptFile.getAbsolutePath());
                return;
            }

            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", scriptFile.getAbsolutePath());
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Capture and log output
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LOGGER.info(scriptName + ": " + line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LOGGER.warning("Script " + scriptName + " finished with non-zero exit code: " + exitCode);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to execute script: " + scriptName, e);
        }
    }
}
