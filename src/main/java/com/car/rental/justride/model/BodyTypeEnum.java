package com.car.rental.justride.model;


/**
 * Body Type is used to list all the different body types of the rental cars.
 */
public enum BodyTypeEnum {

    SEDAN("Sedan", 5, 3, 4),
    SUV("SUV", 7, 4, 5),
    HATCHBACK("Hatchback", 5, 2, 3);
//    WAGON("Wagon", 5, 4, 4),
//    PICKUP("Pickup", 5, 5, 2),
//    CONVERTIBLE("Convertible", 4, 2, 2),
//    COUPE("Coupe", 4, 2, 2),
//    VAN("Van", 8, 6, 4),
//    TRUCK("Truck", 3, 10, 2);

    public final String displayName;
    public final Integer passengerCapacity;
    public final Integer luggageCapacity;
    public final Integer doorCount;

    BodyTypeEnum(String displayName, Integer passengerCapacity, Integer luggageCapacity, Integer doorCount) {
        this.displayName = displayName;
        this.passengerCapacity = passengerCapacity;
        this.luggageCapacity = luggageCapacity;
        this.doorCount = doorCount;
    }
}
