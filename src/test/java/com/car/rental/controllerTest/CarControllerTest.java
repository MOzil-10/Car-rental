package com.car.rental.controllerTest;

import com.car.rental.controller.CarController;
import com.car.rental.model.Car;
import com.car.rental.service.interfaces.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = new Car(1L, "Toyota", "Camry", 15000, true);
    }

    @Test
    void createCar_ShouldReturnCreatedCar() throws Exception {
        when(carService.createCar(any(Car.class))).thenReturn(testCar);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Toyota"));

        verify(carService, times(1)).createCar(any(Car.class));
    }

    @Test
    void getCarById_ShouldReturnCar_WhenCarExists() throws Exception {
        when(carService.getCarByCarId(1L)).thenReturn(testCar);

        mockMvc.perform(get("/api/v1/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Toyota"));

        verify(carService, times(1)).getCarByCarId(1L);
    }

    @Test
    void getAllCars_ShouldReturnListOfCars() throws Exception {
        List<Car> cars = Arrays.asList(testCar);
        when(carService.getCars()).thenReturn(cars);

        mockMvc.perform(get("/api/v1/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(carService, times(1)).getCars();
    }

    @Test
    void updateCar_ShouldReturnUpdatedCar() throws Exception {
        Car updatedCar = new Car(1L, "Honda", "Civic", 20000, false);
        when(carService.updateCar(any(Car.class), eq(1L))).thenReturn(updatedCar);

        mockMvc.perform(put("/api/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Honda"));

        verify(carService, times(1)).updateCar(any(Car.class), eq(1L));
    }
}
