package com.nine.booking;

import com.nine.car.Car;
import com.nine.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CarBooking {
    public UUID id;
    public User user;
    public Car car;
    public LocalDate startDate;
    public LocalDate endDate;
    public BigDecimal price;

    public CarBooking(UUID id, User user, Car car, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
