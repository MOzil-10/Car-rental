package com.car.rental.service.interfaces;

import com.car.rental.model.Car;

import java.util.List;

public interface CarService {
    Car createCar(Car car);
    Car getCarByCarId(Long id);
    List<Car> getCars();
    Car updateCar(Car car, Long id);
}
