package com.nine;

import com.nine.booking.CarBooking;
import com.nine.booking.CarBookingService;
import com.nine.car.Car;
import com.nine.user.User;
import com.nine.user.UserService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static CarBookingService carBookingService = new CarBookingService();
    private static UserService userService = new UserService();

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

            switch (input.trim()) {
                case "1" -> bookCarPrompt(scanner);
                case "2" -> deleteCarPrompt(scanner);
                case "3" -> getUserBookingsPrompt(scanner);
                case "4" -> getAllBookings();
                case "5" -> getAvailableCars();
                case "6" -> getAvailableElectricCars();
                case "7" -> getAllUsers();
                case "8" -> {
                    System.out.println("Exiting");
                    return;
                }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void getAvailableElectricCars() {
        System.out.println(Arrays.toString(carBookingService.getAvailableElectricCars()));
    }

    private static void getAvailableCars() {
        System.out.println(Arrays.toString(carBookingService.getAvailableCars()));
    }

    private static void getAllUsers() {
        System.out.println(Arrays.toString(userService.getUsers()));
    }

    private static void getAllBookings() {
        System.out.println(Arrays.toString(carBookingService.getAllCarBookings()));
    }

    private static void getUserBookingsPrompt(Scanner scanner) {
        User selectedUser = getSelectedUser(scanner);

        System.out.println(Arrays.toString(carBookingService.getUserCarBookings(selectedUser.getId())));
    }

    public static void bookCarPrompt(Scanner scanner) {
        try {
            System.out.println("Select user: ");
            User selectedUser = getSelectedUser(scanner);

            System.out.println("Select car: ");
            Car selectedCar = getSelectedCar(scanner);

            System.out.println("Enter start date: ");
            String startDate = scanner.nextLine();

            System.out.println("Enter end date: ");
            String endDate = scanner.nextLine();

            carBookingService.bookCar(selectedUser.getId(), selectedCar.getId(), LocalDate.parse(startDate), LocalDate.parse(endDate));
        } catch (IllegalArgumentException | DateTimeException | IllegalStateException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static Car getSelectedCar(Scanner scanner) {
        Car[] availableCars = carBookingService.getAvailableCars();

        for (int i = 0; i < availableCars.length; i++) {
            System.out.println(i + 1 + " - " + availableCars[i].getBrand());
        }

        String carChoice = scanner.nextLine();

        return availableCars[Integer.parseInt(carChoice) - 1];
    }

    private static User getSelectedUser(Scanner scanner) {
        User[] users = userService.getUsers();

        for (int i = 0; i < users.length; i++) {
            System.out.println(i + 1 + " - " + users[i].getName());
        }

        String userChoice = scanner.nextLine();

        return users[Integer.parseInt(userChoice) - 1];
    }

    public static void deleteCarPrompt(Scanner scanner) {
        System.out.println("Select booking: ");

        CarBooking[] activeCarBookings = carBookingService.getAllCarBookings();

        if (activeCarBookings.length == 0) {
            System.out.println("No bookings to delete!");
            return;
        }

        for (int i = 0; i < activeCarBookings.length; i++) {
            System.out.printf(
                    "%d - %s %s %s/%s %n",
                    i + 1,
                    activeCarBookings[i].getCar().getBrand(),
                    activeCarBookings[i].getUser().getName(),
                    activeCarBookings[i].getStartDate(),
                    activeCarBookings[i].getEndDate()
            );
        }

        try {
            String userChoice = scanner.nextLine();
            if (Integer.parseInt(userChoice) > 0 && Integer.parseInt(userChoice) <= activeCarBookings.length) {
                carBookingService.deleteBooking(activeCarBookings[Integer.parseInt(userChoice) - 1]);
            } else {
                System.out.println("No booking with that index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
        }
    }
}
