package com.nine.booking;

public class CarBookingDao {
    private static CarBooking[] carBookings = new CarBooking[0];

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

    public void saveBooking(CarBooking booking) {
        CarBooking[] newBookings = new CarBooking[carBookings.length + 1];

        for (int i = 0; i < carBookings.length; i++) {
            newBookings[i] = carBookings[i];
        }

        newBookings[carBookings.length] = booking;
        carBookings = newBookings;
    }

    public void deleteBooking(CarBooking booking) {
        if (carBookings.length == 0 || booking == null) {
            return;
        }

        CarBooking[] newBookings = new CarBooking[carBookings.length - 1];
        int index = 0;

        for (int i = 0; i < carBookings.length; i++) {
            if (!booking.equals(carBookings[i])) {
                newBookings[index] = carBookings[i];
                index++;
            }
        }

        carBookings = newBookings;
    }
}
