package com.car.rental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private int milage;
    private boolean isCarAvailable;

    public Car(){}

    public Car(Long id, String name, String model, int milage, boolean isCarAvailable) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.milage = milage;
        this.isCarAvailable = isCarAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public boolean isCarAvailable() {
        return isCarAvailable;
    }

    public void setCarAvailable(boolean carAvailable) {
        isCarAvailable = carAvailable;
    }
}
