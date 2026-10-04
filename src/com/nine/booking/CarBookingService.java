package com.nine.booking;

import com.nine.car.Car;
import com.nine.car.CarDao;
import com.nine.user.User;
import com.nine.user.UserDao;

import java.time.LocalDate;
import java.util.UUID;

public class CarBookingService {
    public CarBookingDao carBookingDao;
    public UserDao userDao;
    public CarDao carDao;

    public CarBookingService() {
        this.carBookingDao = new CarBookingDao();
        this.userDao = new UserDao();
        this.carDao = new CarDao();
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        // 1. Look up the user by userId
        // - if not found, throw an exception or print an error
        User user = userDao.getUser(userId);
        // 2. Look up the car by carId
        // - if not found, throw an exception or print an error
        Car car = carDao.getCar(carId);
        // 3. Validate the dates:
        // - startDate must not be in the past
        // - endDate must be after startDate
        // - if invalid, throw IllegalArgumentException or print an error
        // 4. Get all current bookings
        CarBooking[] carBookings = carBookingDao.getCarBookings();
        // 5. Check whether an active booking already holds this car
        // - if it does, reject: the car is not available
        // 6. Count the days with ChronoUnit.DAYS.between(startDate, endDate)
        // 7. Calculate the price: car.getRentalPricePerDay() x numberOfDays
        // 8. Build a CarBooking with a UUID, user, car, dates, price,
        // BookingStatus.ACTIVE and bookedAt = LocalDateTime.now()
        CarBooking carBooking = new CarBooking(UUID.fromString("123"), user, car, LocalDate.parse("2026-01-01"), LocalDate.parse("2026-01-01"));
        // 9. Save the booking through the DAO
        carBookingDao.saveBooking(carBooking);
        // 10. Return the saved booking
        return carBooking;
    }
}
