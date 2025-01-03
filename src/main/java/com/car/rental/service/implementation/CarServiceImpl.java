package com.car.rental.service.implementation;

import com.car.rental.exception.CarNotAvailableException;
import com.car.rental.model.Car;
import com.car.rental.repository.CarRepository;
import com.car.rental.service.interfaces.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car createCar(Car car) {
        return repository.save(car);
    }

    @Override
    public Car getCarByCarId(Long id) {
        try {
            return repository.findById(id).
                    orElseThrow(()-> new RuntimeException("Car with id + " + id + "does not exist"));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Car> getCars() {
        return repository.findAll();
    }

    @Override
    public Car updateCar(Car updatedCar, Long id) {
        Car existingCar = repository.findById(id)
                .orElseThrow(() -> new CarNotAvailableException("Car with ID " + id + " not found."));

        existingCar.setName(updatedCar.getName());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setMilage(updatedCar.getMilage());
        existingCar.setCarAvailable(updatedCar.isCarAvailable());

        return repository.save(existingCar);
    }

}
