package com.nine.booking;

import java.util.Arrays;
import java.util.Objects;

public class CarBookingDao {
    private static final CarBooking[] carBookings;
    private int size = 0;

    static {
        carBookings = new CarBooking[4];
    }

    public CarBooking[] getActiveCarBookings() {
        return Arrays.stream(carBookings)
                .filter(Objects::nonNull)
                .toArray(CarBooking[]::new);
    }

    public void saveBooking(CarBooking booking) {
        carBookings[size] = booking;
        size++;
    }

    public void deleteBooking(CarBooking booking) {
        for (int i = 0; i < size; i++) {
            if(booking.id.equals(carBookings[i].id)) {
                carBookings[i] = null;
                size--;
                break;
            }
        }
    }

    public int getSize() {
        return size;
    }
}
