package com.car.rental.repository;

import com.car.rental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
