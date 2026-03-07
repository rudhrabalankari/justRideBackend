package com.car.rental.justride.service;

import com.car.rental.justride.model.BodyTypeEnum;
import com.car.rental.justride.model.Car;
import com.car.rental.justride.model.response.CarResponse;
import com.car.rental.justride.model.response.CarsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private final DynamoDbTable<Car> carTable;

    public CarService(DynamoDbEnhancedClient enhancedClient, @Value("${aws.dynamodb.table-name}") String tableName) {
        this.carTable = enhancedClient.table(tableName, TableSchema.fromBean(Car.class));
    }

    public List<String> getAllBodyTypes() {
        return Arrays.stream(BodyTypeEnum.values())
                .map(bodyTypeEnum -> bodyTypeEnum.displayName)
                .toList();
    }

    public CarsResponse getAllCars() {
        return new CarsResponse(carTable.scan().items().stream().toList());
    }

    public CarResponse addCar(Car car) {
        if (car.getId() == null) {
            car.setId(UUID.randomUUID().toString());
        }
        // Ensure the type is set to "CAR" as it is the sort key
        car.setType("CAR");
        carTable.putItem(car);
        return new CarResponse(car);
    }

    public CarResponse getCarById(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .sortValue("CAR")
                .build();
        Car car = carTable.getItem(r -> r.key(key));
        return new CarResponse(car);
    }

    public CarResponse updateCar(String id, Car car) {
        Car carToUpdate = getCarById(id).car();
        if (carToUpdate != null) {
            if (car.getMake() != null) carToUpdate.setMake(car.getMake());
            if (car.getModel() != null) carToUpdate.setModel(car.getModel());
            if (car.getYear() != null) carToUpdate.setYear(car.getYear());
            if (car.getBodyType() != null) carToUpdate.setBodyType(car.getBodyType());
            if (car.getFuelType() != null) carToUpdate.setFuelType(car.getFuelType());
            if (car.getDescription() != null) carToUpdate.setDescription(car.getDescription());

            carTable.updateItem(carToUpdate);
        }
        return new CarResponse(carToUpdate);
    }

    public void deleteCar(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .sortValue("CAR")
                .build();
        carTable.deleteItem(key);
    }
}
