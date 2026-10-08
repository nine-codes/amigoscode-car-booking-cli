package com.nine.booking;

import java.util.Arrays;

public class CarBookingDao {
    private static CarBooking[] carBookings = new CarBooking[0];

    public CarBooking[] getActiveCarBookings() {
        CarBooking[] activeCarBookings = new CarBooking[carBookings.length];

        for (CarBooking carBooking : carBookings) {
            if (carBooking.status.equals(BookingStatus.ACTIVE)) {
                activeCarBookings[0] = carBooking;
            }
        }

        System.out.println(Arrays.toString(activeCarBookings));
        return activeCarBookings;
    }

    public void saveBooking(CarBooking booking) {
        CarBooking[] newBookings = new CarBooking[carBookings.length + 1];
        newBookings[carBookings.length] = booking;
        carBookings = newBookings;
    }

    public void deleteBooking(CarBooking booking) {
        System.out.println("Delete " + booking);
    }
}
