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

@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Setter
    private String id;

    @Setter
    private String type = "CAR";

    @Getter
    @Setter
    private String make;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private Integer year;

    @Getter
    @Setter
    private BodyTypeEnum bodyType;

    @Getter
    @Setter
    private FuelTypeEnum fuelType;

    @Getter
    @Setter
    private String description;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getType() {
        return type;
    }
}
