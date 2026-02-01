package com.car.rental.justride.service;

import com.car.rental.justride.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.UUID;

@Service
public class DynamoService {

    private final DynamoDbTable<Car> carTable;

    public DynamoService(DynamoDbEnhancedClient enhancedClient, @Value("${aws.dynamodb.table-name}") String tableName) {
        // Pre-initialize the table reference for better performance
        this.carTable = enhancedClient.table(tableName, TableSchema.fromBean(Car.class));
    }

    public List<Car> getAllCars() {
        return carTable.scan().items().stream().toList();
    }

    public String addCar(Car car) {
        if (car.getId() == null) {
            car.setId(UUID.randomUUID().toString());
        }
        carTable.putItem(car); // Simplified putItem call
        return car.getId();
    }
}
