// java
package com.car.rental.justride.controller;

import com.car.rental.justride.model.Car;
import com.car.rental.justride.service.DynamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarController carController;

    @MockitoBean
    private DynamoService dynamoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads_controllerPresent() {
        assertThat(carController).isNotNull();
    }

    @Test
    void getAllCars_returnsList() throws Exception {
        Car car = new Car();
        car.setId("car-1");
        car.setMake("Toyota");
        car.setModel("Corolla");

        when(dynamoService.getAllCars()).thenReturn(List.of(car));

        mockMvc.perform(get("/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("car-1"))
                .andExpect(jsonPath("$[0].make").value("Toyota"))
                .andExpect(jsonPath("$[0].model").value("Corolla"));
    }
}
