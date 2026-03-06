package com.car.rental.justride.model.response;

import com.car.rental.justride.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response wrapper for a single booking.
 */
@AllArgsConstructor
@Getter
public class BookingResponse {
    private Booking booking;
}

