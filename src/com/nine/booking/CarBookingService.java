package com.nine.booking;

import com.nine.car.Car;
import com.nine.car.CarDao;
import com.nine.user.User;
import com.nine.user.UserDao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
        if (user == null) {
            throw new IllegalArgumentException("No user found with the id: " + userId);
        }

        // 2. Look up the car by carId
        // - if not found, throw an exception or print an error
        Car car = carDao.getCar(carId);
        if (car == null) {
            throw new IllegalArgumentException("No car found with the id: " + carId);
        }

        // 3. Validate the dates:
        // - startDate must not be in the past
        // - endDate must be after startDate
        // - if invalid, throw IllegalArgumentException or print an error
        LocalDate now = LocalDate.now();
        if (startDate.isBefore(now) || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid dates");
        }

        // 4. Get all current bookings
        CarBooking[] carBookings = carBookingDao.getCarBookings();

        // 5. Check whether an active booking already holds this car
        // - if it does, reject: the car is not available
        if (Arrays.stream(carBookings).anyMatch(carBooking -> carBooking.car.id.equals(carId))) {
            System.out.println("The car is not available");
            return null;
        }

        // 6. Count the days with ChronoUnit.DAYS.between(startDate, endDate)
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        // 7. Calculate the price: car.getRentalPricePerDay() x numberOfDays
        BigDecimal price = car.rentalPricePerDay.multiply(new BigDecimal(numberOfDays));

        // 8. Build a CarBooking with a UUID, user, car, dates, price,
        // BookingStatus.ACTIVE and bookedAt = LocalDateTime.now()
        CarBooking carBooking = new CarBooking(UUID.randomUUID(), user, car, startDate, endDate, BookingStatus.ACTIVE, price);

        // 9. Save the booking through the DAO
        carBookingDao.saveBooking(carBooking);

        // 10. Return the saved booking
        return carBooking;
    }

    public void deleteBooking(CarBooking carBooking) {
        carBookingDao.deleteBooking(carBooking);
    }

    public CarBooking[] getActiveCarBookings() {
        return carBookingDao.getCarBookings();
    }

    public CarBooking[] getUserCarBookings(UUID id) {
        if(id == null) {
            throw new NullPointerException("getUserCarBookings: Id is null");
        }

        int size = 0;
        for (int i = 0; i < carBookingDao.getCarBookings().length; i++) {
            if (carBookingDao.getCarBookings()[i].user.id.equals(id)) {
                size++;
            }
        }

        CarBooking[] userBookings = new CarBooking[size];
        int index = 0;
        for (int i = 0; i < carBookingDao.getCarBookings().length; i++) {
            if (carBookingDao.getCarBookings()[i].user.id.equals(id)) {
                userBookings[index] = carBookingDao.getCarBookings()[i];
                index++;
            }
        }

        return userBookings;
    }
}
