package com.car.rental.justride.model.response;

import com.car.rental.justride.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Response wrapper for multiple bookings.
 */
@AllArgsConstructor
@Getter
public class BookingsResponse {
    private List<Booking> bookings;
}

