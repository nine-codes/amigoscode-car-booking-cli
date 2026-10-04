package com.nine.booking;

public class CarBookingDao {
    private static final CarBooking[] carBookings;

    static {
        carBookings = new CarBooking[4];
    }

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

    public void saveBooking(CarBooking carBooking) {
        carBookings[carBookings.length - 1] = carBooking;
    }
}
