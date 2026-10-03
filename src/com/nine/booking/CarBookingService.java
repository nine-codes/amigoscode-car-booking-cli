package com.nine.booking;

import java.time.LocalDate;
import java.util.UUID;

public class CarBookingService {
    public CarBookingDao carBookingDao;

    public CarBookingService() {
        this.carBookingDao = new CarBookingDao();
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        return new CarBooking();
    }
}
