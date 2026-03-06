#!/bin/bash

# Export credentials to match the table creation script
export AWS_DEFAULT_REGION=us-east-1
export AWS_ACCESS_KEY_ID=awsAccessKey
export AWS_SECRET_ACCESS_KEY=awsSecretKey

# Generate unique IDs
BOOKING_ID=$(uuidgen)
CUSTOMER_ID=$(uuidgen)
CAR_ID=$(uuidgen)

echo "Creating sample booking..."
echo "Booking ID: $BOOKING_ID"
echo "Customer ID: $CUSTOMER_ID"
echo "Car ID: $CAR_ID"

aws dynamodb put-item \
    --table-name Bookings \
    --item '{
        "PK": {"S": "'$BOOKING_ID'"},
        "SK": {"S": "'$CUSTOMER_ID'"},
        "bookingId": {"S": "'$BOOKING_ID'"},
        "customerId": {"S": "'$CUSTOMER_ID'"},
        "carId": {"S": "'$CAR_ID'"},
        "pickupDate": {"S": "2025-03-15"},
        "returnDate": {"S": "2025-03-22"},
        "totalPrice": {"N": "450.00"},
        "status": {"S": "CONFIRMED"},
        "customerName": {"S": "John Doe"},
        "customerEmail": {"S": "john.doe@example.com"},
        "customerPhone": {"S": "+1-555-0123"},
        "createdAt": {"S": "2025-03-05T10:30:00"}
    }' \
    --endpoint-url http://localhost:8000 \
    --region $AWS_DEFAULT_REGION

echo "Sample booking created successfully!"

