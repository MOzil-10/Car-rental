package com.car.rental.serviceTest;

import com.car.rental.controller.CarController;
import com.car.rental.model.Car;
import com.car.rental.service.interfaces.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    private CarController carController;

    private Car testCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carController = new CarController(carService);
        testCar = new Car(1L, "Toyota", "Camry", 15000, true);
    }

    @Test
    void createCar_ShouldReturnCreatedCar() {
        when(carService.createCar(testCar)).thenReturn(testCar);

        ResponseEntity<Car> response = carController.createCar(testCar);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testCar, response.getBody());
        verify(carService, times(1)).createCar(testCar);
    }

    @Test
    void getCarById_ShouldReturnCar_WhenCarExists() {
        when(carService.getCarByCarId(1L)).thenReturn(testCar);

        ResponseEntity<Car> response = carController.getCarById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testCar, response.getBody());
        verify(carService, times(1)).getCarByCarId(1L);
    }

    @Test
    void getCarById_ShouldThrowException_WhenCarDoesNotExist() {
        when(carService.getCarByCarId(1L)).thenThrow(new RuntimeException("Car not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carController.getCarById(1L));
        assertEquals("Car not found", exception.getMessage());
        verify(carService, times(1)).getCarByCarId(1L);
    }

    @Test
    void getAllCars_ShouldReturnListOfCars() {
        when(carService.getCars()).thenReturn(Arrays.asList(testCar));

        ResponseEntity<List<Car>> response = carController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(carService, times(1)).getCars();
    }

    @Test
    void updateCar_ShouldUpdateCarDetails() {
        Car updatedCar = new Car(1L, "Honda", "Civic", 20000, false);

        when(carService.updateCar(updatedCar, 1L)).thenReturn(updatedCar);

        ResponseEntity<Car> response = carController.updateCar(updatedCar, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCar, response.getBody());
        verify(carService, times(1)).updateCar(updatedCar, 1L);
    }
}

