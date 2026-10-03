package com.nine.car;

public class CarService {
    public CarDao carDao;

    public CarService() {
        this.carDao = new CarDao();
    }
}
