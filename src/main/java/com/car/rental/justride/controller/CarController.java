package com.car.rental.justride.controller;

import com.car.rental.justride.exception.CarNotFoundException;
import com.car.rental.justride.model.Car;
import com.car.rental.justride.model.response.CarResponse;
import com.car.rental.justride.model.response.CarsResponse;
import com.car.rental.justride.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Tag(name = "CarController", description = "Car Controller for JustRide Application")
public class CarController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Get all cars", description = "Fetches all cars available in the DynamoDB table")
    @GetMapping
    public ResponseEntity<CarsResponse> getCars() {
        CarsResponse allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable String id) throws CarNotFoundException {
        CarResponse car = carService.getCarById(id);
        if (car == null) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarResponse> addCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable String id, @RequestBody Car car) {
        return new ResponseEntity<>(carService.updateCar(id, car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/body-types")
    public List<String> getAllBodyTypes() {
        return carService.getAllBodyTypes();
    }
}
