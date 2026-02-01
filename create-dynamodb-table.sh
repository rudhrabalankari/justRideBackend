#!/bin/bash
set -e
export AWS_ACCESS_KEY_ID=awsAccessKey
export AWS_SECRET_ACCESS_KEY=awsSecretKey
export AWS_DEFAULT_REGION=us-east-1
ENDPOINT_URL="${ENDPOINT_URL:-http://localhost:8000}"

aws dynamodb create-table \
    --table-name Car \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
    --provisioned-throughput \
        ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url $ENDPOINT_URL \
    --region $AWS_DEFAULT_REGION