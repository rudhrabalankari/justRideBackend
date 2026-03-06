package com.car.rental.justride.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request DTO for creating a new booking.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {

    private String customerId;
    private String carId;
    private String pickupDate;
    private String returnDate;
    private Double totalPrice;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}

