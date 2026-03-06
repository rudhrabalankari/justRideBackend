package com.car.rental.justride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * Booking model class representing a car rental booking.
 */
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Setter
    private String bookingId;

    @Setter
    private String customerId;

    @Getter
    @Setter
    private String carId;

    @Getter
    @Setter
    private String pickupDate;

    @Getter
    @Setter
    private String returnDate;

    @Getter
    @Setter
    private Double totalPrice;

    @Getter
    @Setter
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED

    @Getter
    @Setter
    private String customerName;

    @Getter
    @Setter
    private String customerEmail;

    @Getter
    @Setter
    private String customerPhone;

    @Getter
    @Setter
    private String createdAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getBookingId() {
        return bookingId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getCustomerId() {
        return customerId;
    }
}
