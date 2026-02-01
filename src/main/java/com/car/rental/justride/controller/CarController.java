package com.car.rental.justride.controller;

import com.car.rental.justride.model.Car;
import com.car.rental.justride.service.DynamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/cars")
    public List<Car> getCars() {
        return dynamoService.getAllCars();
    }

    @PostMapping("/car")
    public String addCar(Car car) {
        return dynamoService.addCar(car);
    }

}
