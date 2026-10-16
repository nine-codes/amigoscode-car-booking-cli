package com.nine.booking;

import com.nine.car.Car;
import com.nine.car.CarService;
import com.nine.user.User;
import com.nine.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CarBookingService {
    private CarBookingDao carBookingDao;
    private UserService userService;
    private CarService carService;

    public CarBookingService() {
        this.carBookingDao = new CarBookingDao();
        this.userService = new UserService();
        this.carService = new CarService();
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        // 1. Look up the user by userId
        // - if not found, throw an exception
        User user = userService.getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("No user found with the id: " + userId);
        }

        // 2. Look up the car by carId
        // - if not found, throw an exception
        Car car = carService.getCar(carId);
        if (car == null) {
            throw new IllegalArgumentException("No car found with the id: " + carId);
        }

        // 3. Validate the dates:
        // - startDate must not be in the past
        // - endDate must be after startDate
        LocalDate now = LocalDate.now();
        if (startDate.isBefore(now) || !endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Invalid dates");
        }

        // 4. Get all current bookings
        CarBooking[] carBookings = carBookingDao.getCarBookings();

        // 5. Check whether an active booking already holds this car
        // - if it does, reject: the car is not available
        for (CarBooking carBooking : carBookings) {
            if (carBooking.getCar().getId().equals(carId) && carBooking.getStatus().equals(BookingStatus.ACTIVE)) {
                throw new IllegalStateException("The car is not available");
            }
        }

        // 6. Count the days with ChronoUnit.DAYS.between(startDate, endDate)
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        // 7. Calculate the price: car.getRentalPricePerDay() x numberOfDays
        BigDecimal price = car.getRentalPricePerDay().multiply(new BigDecimal(numberOfDays));

        // 8. Build a CarBooking with a UUID, user, car, dates, price and BookingStatus.ACTIVE
        CarBooking carBooking = new CarBooking(UUID.randomUUID(), user, car, startDate, endDate, BookingStatus.ACTIVE, price, LocalDateTime.now());

        // 9. Save the booking through the DAO
        carBookingDao.saveBooking(carBooking);

        // 10. Return the saved booking
        return carBooking;
    }

    public void deleteBooking(CarBooking carBooking) {
        carBookingDao.deleteBooking(carBooking);
    }

    public CarBooking[] getAllCarBookings() {
        return carBookingDao.getCarBookings();
    }

    public CarBooking[] getUserCarBookings(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("getUserCarBookings: Id is null");
        }

        CarBooking[] allBookings = carBookingDao.getCarBookings();

        int size = 0;
        for (int i = 0; i < allBookings.length; i++) {
            if (allBookings[i].getUser().getId().equals(id)) {
                size++;
            }
        }

        CarBooking[] userBookings = new CarBooking[size];
        int index = 0;
        for (int i = 0; i < allBookings.length; i++) {
            if (allBookings[i].getUser().getId().equals(id)) {
                userBookings[index] = allBookings[i];
                index++;
            }
        }

        return userBookings;
    }

    public Car[] getAvailableCars() {
        return getNotBookedCars(carService.getCars());
    }

    public Car[] getAvailableElectricCars() {
        return getNotBookedCars(carService.getElectricCars());
    }

    private Car[] getNotBookedCars(Car[] cars) {
        int size = 0;

        for (Car car : cars) {
            boolean booked = false;

            for (CarBooking carBooking : carBookingDao.getCarBookings()) {
                if (carBooking.getCar().getId().equals(car.getId()) && carBooking.getStatus().equals(BookingStatus.ACTIVE)) {
                    booked = true;
                    break;
                }
            }

            if (!booked) {
                size++;
            }
        }

        Car[] notBookedCars = new Car[size];
        int index = 0;

        for (Car car : cars) {
            boolean booked = false;

            for (CarBooking carBooking : carBookingDao.getCarBookings()) {
                if (carBooking.getCar().getId().equals(car.getId()) && carBooking.getStatus().equals(BookingStatus.ACTIVE)) {
                    booked = true;
                    break;
                }
            }

            if (!booked) {
                notBookedCars[index] = car;
                index++;
            }
        }

        return notBookedCars;
    }
}
