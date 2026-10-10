package com.nine.booking;

import java.util.Arrays;

public class CarBookingDao {
    private static CarBooking[] carBookings = new CarBooking[0];

    public CarBooking[] getCarBookings() {
        //        CarBooking[] activeCarBookings = new CarBooking[carBookings.length];
        //
        //        for (CarBooking carBooking : carBookings) {
        //            if (carBooking.status.equals(BookingStatus.ACTIVE)) {
        //                activeCarBookings[0] = carBooking;
        //            }
        //        }
        //
        //        System.out.println(Arrays.toString(activeCarBookings));
        //        return activeCarBookings;
        return carBookings;
    }

    public void saveBooking(CarBooking booking) {
        CarBooking[] newBookings = new CarBooking[carBookings.length + 1];
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
            if (booking.equals(carBookings[i])) {

            } else {
                newBookings[index] = carBookings[i];
                index++;
            }
        }

        carBookings = newBookings;
    }
}
