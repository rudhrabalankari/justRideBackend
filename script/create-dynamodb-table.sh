#!/bin/bash
set -e
export AWS_ACCESS_KEY_ID=awsAccessKey
export AWS_SECRET_ACCESS_KEY=awsSecretKey
export AWS_DEFAULT_REGION=us-east-1
ENDPOINT_URL="${ENDPOINT_URL:-http://localhost:8000}"

#docker container start backend-dynamodb-local-1

# -----------------------------
# Create Vehicles Table
# -----------------------------
aws dynamodb create-table \
  --table-name Vehicles \
  --attribute-definitions \
      AttributeName=PK,AttributeType=S \
      AttributeName=SK,AttributeType=S \
  --key-schema \
      AttributeName=PK,KeyType=HASH \
      AttributeName=SK,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --endpoint-url http://localhost:8000 || true

# -----------------------------
# Create Bookings Table + GSI1
# -----------------------------
aws dynamodb create-table \
  --table-name Bookings \
  --attribute-definitions \
      AttributeName=PK,AttributeType=S \
      AttributeName=SK,AttributeType=S \
      AttributeName=GSI1PK,AttributeType=S \
      AttributeName=GSI1SK,AttributeType=S \
  --key-schema \
      AttributeName=PK,KeyType=HASH \
      AttributeName=SK,KeyType=RANGE \
  --global-secondary-indexes '[
      {
        "IndexName": "GSI1",
        "KeySchema": [
          {"AttributeName": "GSI1PK", "KeyType": "HASH"},
          {"AttributeName": "GSI1SK", "KeyType": "RANGE"}
        ],
        "Projection": {"ProjectionType": "ALL"},
        "ProvisionedThroughput": {
          "ReadCapacityUnits": 5,
          "WriteCapacityUnits": 5
        }
      }
    ]' \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --endpoint-url http://localhost:8000 || true
