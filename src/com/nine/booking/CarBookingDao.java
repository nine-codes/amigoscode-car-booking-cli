package com.nine.booking;

public class CarBookingDao {
    private CarBooking[] carBookings = new CarBooking[0];

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
        if (booking == null) {
            return;
        }

        boolean found = false;
        for (CarBooking carBooking : carBookings) {
            if (booking.equals(carBooking)) {
                found = true;
            }
        }

        if (!found) {
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
