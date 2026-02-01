package com.car.rental.justride.model;

public enum BodyTypeEnum {

    SEDAN("Sedan", 5, 3, 4),
    SUV("SUV", 7, 4, 5),
    HATCHBACK("Hatchback", 5, 2, 3),
    WAGON("Wagon", 5, 4, 4),
    PICKUP("Pickup", 5, 5, 2),
    CONVERTIBLE("Convertible", 4, 2, 2),
    COUPE("Coupe", 4, 2, 2),
    VAN("Van", 8, 6, 4),
    TRUCK("Truck", 3, 10, 2);

    BodyTypeEnum(String displayName, Integer passengerCapacity, Integer luggageCapacity, Integer doorCount) {
    }
}
