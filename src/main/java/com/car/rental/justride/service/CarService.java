package com.car.rental.justride.service;

import java.util.Arrays;
import java.util.List;

import com.car.rental.justride.model.BodyTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    public List<String> getAllBodyTypes() {
        return Arrays.stream(BodyTypeEnum.values())
                .map(bodyTypeEnum -> bodyTypeEnum.displayName)
                .toList();
    }

}