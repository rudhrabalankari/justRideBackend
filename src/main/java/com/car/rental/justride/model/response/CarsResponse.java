package com.car.rental.justride.model.response;

import com.car.rental.justride.model.Car;

import java.util.List;

public record CarsResponse(List<Car> carList) {

}