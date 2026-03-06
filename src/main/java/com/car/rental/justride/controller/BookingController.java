package com.car.rental.justride.controller;

import com.car.rental.justride.model.request.BookingRequest;
import com.car.rental.justride.model.response.BookingResponse;
import com.car.rental.justride.model.response.BookingsResponse;
import com.car.rental.justride.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing car rental bookings.
 */
@RestController
@RequestMapping("/bookings")
@Tag(name = "BookingController", description = "Booking Controller for JustRide Application")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Create a new booking.
     *
     * @param bookingRequest The booking request containing booking details
     * @return ResponseEntity containing the created booking
     */
    @Operation(summary = "Create a new booking", description = "Creates a new car rental booking")
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse booking = bookingService.createBooking(bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    /**
     * Get all bookings.
     *
     * @return ResponseEntity containing all bookings
     */
    @Operation(summary = "Get all bookings", description = "Fetches all bookings from the system")
    @GetMapping
    public ResponseEntity<BookingsResponse> getAllBookings() {
        BookingsResponse bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Get a specific booking by ID.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID (sort key)
     * @return ResponseEntity containing the booking
     */
    @Operation(summary = "Get booking by ID", description = "Fetches a specific booking by its ID and customer ID")
    @GetMapping("/{bookingId}/{customerId}")
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable String bookingId,
            @PathVariable String customerId) {
        BookingResponse booking = bookingService.getBookingById(bookingId, customerId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * Update an existing booking.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     * @param bookingRequest The updated booking details
     * @return ResponseEntity containing the updated booking
     */
    @Operation(summary = "Update a booking", description = "Updates an existing booking")
    @PutMapping("/{bookingId}/{customerId}")
    public ResponseEntity<BookingResponse> updateBooking(
            @PathVariable String bookingId,
            @PathVariable String customerId,
            @RequestBody BookingRequest bookingRequest) {
        BookingResponse booking = bookingService.updateBooking(bookingId, customerId, bookingRequest);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * Cancel a booking.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     * @return ResponseEntity containing the cancelled booking
     */
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking")
    @PatchMapping("/{bookingId}/{customerId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable String bookingId,
            @PathVariable String customerId) {
        BookingResponse booking = bookingService.cancelBooking(bookingId, customerId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * Delete a booking.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     * @return ResponseEntity with no content status
     */
    @Operation(summary = "Delete a booking", description = "Deletes a booking from the system")
    @DeleteMapping("/{bookingId}/{customerId}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable String bookingId,
            @PathVariable String customerId) {
        bookingService.deleteBooking(bookingId, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

