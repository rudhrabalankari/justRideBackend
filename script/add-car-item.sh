#!/bin/bash

# Export credentials to match the table creation script
export AWS_DEFAULT_REGION=us-east-1

# Generate a unique ID
ITEM_ID=$(uuidgen)

aws dynamodb put-item \
    --table-name Car \
    --item '{
        "id": {"S": "'$ITEM_ID'"},
        "make": {"S": "Honda"},
        "model": {"S": "CRV Hybrid"},
        "year": {"N": "2025"},
        "bodyType": {"S": "SUV"},
        "fuelType": {"S": "GASOLINE"},
        "description": {"S": "I like this Red car!"}
    }' \
    --endpoint-url http://localhost:8000 \
    --region $AWS_DEFAULT_REGION