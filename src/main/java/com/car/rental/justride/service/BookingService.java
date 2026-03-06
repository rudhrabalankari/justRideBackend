package com.car.rental.justride.service;

import com.car.rental.justride.model.Booking;
import com.car.rental.justride.model.request.BookingRequest;
import com.car.rental.justride.model.response.BookingResponse;
import com.car.rental.justride.model.response.BookingsResponse;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Service class for managing booking operations in DynamoDB.
 */
@Service
public class BookingService {

    private final DynamoDbTable<Booking> bookingTable;
    private static final String BOOKINGS_TABLE_NAME = "Bookings";
    private static final String DEFAULT_BOOKING_STATUS = "PENDING";

    public BookingService(DynamoDbEnhancedClient enhancedClient) {
        // Initialize the booking table reference
        this.bookingTable = enhancedClient.table(BOOKINGS_TABLE_NAME, TableSchema.fromBean(Booking.class));
    }

    /**
     * Create a new booking from the request.
     *
     * @param bookingRequest The booking request containing booking details
     * @return BookingResponse containing the created booking
     */
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCustomerId(bookingRequest.getCustomerId());
        booking.setCarId(bookingRequest.getCarId());
        booking.setPickupDate(bookingRequest.getPickupDate());
        booking.setReturnDate(bookingRequest.getReturnDate());
        booking.setTotalPrice(bookingRequest.getTotalPrice());
        booking.setStatus(DEFAULT_BOOKING_STATUS);
        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setCustomerEmail(bookingRequest.getCustomerEmail());
        booking.setCustomerPhone(bookingRequest.getCustomerPhone());
        booking.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        bookingTable.putItem(booking);
        return new BookingResponse(booking);
    }

    /**
     * Retrieve all bookings from the table.
     *
     * @return BookingsResponse containing all bookings
     */
    public BookingsResponse getAllBookings() {
        return new BookingsResponse(bookingTable.scan().items().stream().toList());
    }

    /**
     * Retrieve a booking by its ID and customer ID.
     *
     * @param bookingId The booking ID (partition key)
     * @param customerId The customer ID (sort key)
     * @return BookingResponse containing the booking if found
     */
    public BookingResponse getBookingById(String bookingId, String customerId) {
        if (bookingId == null || bookingId.trim().isEmpty() ||
            customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("bookingId and customerId must not be null or empty");
        }

        Key key = Key.builder()
                .partitionValue(bookingId)
                .sortValue(customerId)
                .build();

        Booking booking = bookingTable.getItem(r -> r.key(key));
        return new BookingResponse(booking);
    }

    /**
     * Update an existing booking.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     * @param bookingRequest The updated booking details
     * @return BookingResponse containing the updated booking
     */
    public BookingResponse updateBooking(String bookingId, String customerId, BookingRequest bookingRequest) {
        if (bookingId == null || bookingId.trim().isEmpty() ||
            customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("bookingId and customerId must not be null or empty");
        }

        Key key = Key.builder()
                .partitionValue(bookingId)
                .sortValue(customerId)
                .build();

        Booking booking = bookingTable.getItem(r -> r.key(key));

        if (booking != null) {
            if (bookingRequest.getPickupDate() != null) {
                booking.setPickupDate(bookingRequest.getPickupDate());
            }
            if (bookingRequest.getReturnDate() != null) {
                booking.setReturnDate(bookingRequest.getReturnDate());
            }
            if (bookingRequest.getTotalPrice() != null) {
                booking.setTotalPrice(bookingRequest.getTotalPrice());
            }
            if (bookingRequest.getCustomerName() != null) {
                booking.setCustomerName(bookingRequest.getCustomerName());
            }
            if (bookingRequest.getCustomerEmail() != null) {
                booking.setCustomerEmail(bookingRequest.getCustomerEmail());
            }
            if (bookingRequest.getCustomerPhone() != null) {
                booking.setCustomerPhone(bookingRequest.getCustomerPhone());
            }

            bookingTable.putItem(booking);
        }

        return new BookingResponse(booking);
    }

    /**
     * Cancel a booking by changing its status to CANCELLED.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     * @return BookingResponse containing the cancelled booking
     */
    public BookingResponse cancelBooking(String bookingId, String customerId) {
        if (bookingId == null || bookingId.trim().isEmpty() ||
            customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("bookingId and customerId must not be null or empty");
        }

        Key key = Key.builder()
                .partitionValue(bookingId)
                .sortValue(customerId)
                .build();

        Booking booking = bookingTable.getItem(r -> r.key(key));

        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingTable.putItem(booking);
        }

        return new BookingResponse(booking);
    }

    /**
     * Delete a booking.
     *
     * @param bookingId The booking ID
     * @param customerId The customer ID
     */
    public void deleteBooking(String bookingId, String customerId) {
        if (bookingId == null || bookingId.trim().isEmpty() ||
            customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("bookingId and customerId must not be null or empty");
        }

        Key key = Key.builder()
                .partitionValue(bookingId)
                .sortValue(customerId)
                .build();

        bookingTable.deleteItem(r -> r.key(key));
    }
}

