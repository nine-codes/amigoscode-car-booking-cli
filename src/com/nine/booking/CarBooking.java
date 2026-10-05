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
    public BookingStatus status;
    public BigDecimal price;

    public CarBooking(UUID id, User user, Car car, LocalDate startDate, LocalDate endDate, BookingStatus status, BigDecimal price) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
