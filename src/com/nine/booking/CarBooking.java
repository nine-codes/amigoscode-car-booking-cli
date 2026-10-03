package com.nine.booking;

import com.nine.car.Car;
import com.nine.user.User;

import java.time.LocalDate;
import java.util.UUID;

public class CarBooking {
    public UUID id;
    public User user;
    public Car car;
    public LocalDate startDate;
    public LocalDate endDate;

    public CarBooking() {
    }
}
