package com.nine.car;

import java.util.UUID;

public class CarService {
    private CarDao carDao;

    public CarService() {
        this.carDao = new CarDao();
    }

    public Car getCar(UUID id) {
        return carDao.getCar(id);
    }

    public Car[] getCars() {
        return carDao.getCars();
    }

    public Car[] getElectricCars() {
        int size = 0;

        for (Car car : carDao.getCars()) {
            if (car.isElectric()) {
                size++;
            }
        }

        Car[] electricCars = new Car[size];
        int index = 0;

        for (Car car : carDao.getCars()) {
            if (car.isElectric()) {
                electricCars[index] = car;
                index++;
            }
        }

        return electricCars;
    }
}
