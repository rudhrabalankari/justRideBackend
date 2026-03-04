package com.car.rental.justride.controller;

import com.car.rental.justride.exception.CarNotFoundException;
import com.car.rental.justride.model.Car;
import com.car.rental.justride.model.response.CarResponse;
import com.car.rental.justride.model.response.CarsResponse;
import com.car.rental.justride.service.CarService;
import com.car.rental.justride.service.DynamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "CarController", description = "Car Controller for JustRide Application")
public class CarController {

    @Autowired
    private DynamoService dynamoService;

    @Autowired
    private CarService carService;

    @Operation(summary = "Get all cars", description = "Fetches all cars available in the DynamoDB table")
    @GetMapping("/cars")
    public ResponseEntity<CarsResponse> getCars() {
        CarsResponse allCars = dynamoService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable String id) throws CarNotFoundException {

        CarResponse car = dynamoService.getCarById(id);
        if (car == null) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping("/car")
    public String addCar(Car car) {
        return dynamoService.addCar(car);
    }

    @GetMapping("/models")
    public List<String> getAllBodyTypes() {
        return carService.getAllBodyTypes();
    }

}
