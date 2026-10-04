package com.nine;

import com.nine.booking.CarBookingService;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "1 - Book Car\n" +
                    "2 - Delete Booking\n" +
                    "3 - View All User Booked Cars\n" +
                    "4 - View All Bookings\n" +
                    "5 - View Available Cars\n" +
                    "6 - View Available Electric Cars\n" +
                    "7 - View All Users\n" +
                    "8 - Exit\n"
            );
            String input = scanner.nextLine();

            if (input.trim().equals("8")) {
                break;
            }

            System.out.printf("You entered: %s%n", input);
        }
    }
}
