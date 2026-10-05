package com.nine.booking;

public class CarBookingDao {
    private static final CarBooking[] carBookings;
    private static int size = 0;

    static {
        carBookings = new CarBooking[size];
    }

    public CarBooking[] getActiveCarBookings() {
        CarBooking[] activeCarBookings = new CarBooking[size];

        for (CarBooking carBooking : carBookings) {
            if(carBooking.status.equals(BookingStatus.ACTIVE)) {
                activeCarBookings[0] = carBooking;
            }
        }

        return activeCarBookings;
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
