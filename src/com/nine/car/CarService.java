package com.nine.car;

import java.util.Arrays;
import java.util.stream.Stream;

public class CarService {
    public CarDao carDao;

    public CarService() {
        this.carDao = new CarDao();
    }


    public Car[] getAvailableElectricCars() {
        return carDao.getCars();
    }

    public Car[] getAvailableCars() {
        return carDao.getCars();
    }
}
