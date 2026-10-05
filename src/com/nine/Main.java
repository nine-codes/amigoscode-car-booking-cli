package com.nine;

import com.nine.booking.CarBookingService;
import com.nine.car.CarService;
import com.nine.user.UserService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static CarBookingService carBookingService;
    public static CarService carService;
    public static UserService userService;

    public Main() {
        this.carBookingService = new CarBookingService();
        this.carService = new CarService();
        this.userService = new UserService();
    }

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
                case "2" -> carBookingService.deleteBooking(null);
                case "3" -> carBookingService.getActiveCarBookings();
                case "4" -> carBookingService.getActiveCarBookings();
                case "5" -> carService.getAvailableCars();
                case "6" -> carService.getAvailableElectricCars();
                case "7" -> userService.getUsers();
                case "8" -> System.out.println("Exiting");
                default -> System.out.println("Invalid");
            }

            System.out.printf("You entered: %s%n", input);
        }
    }


    public static void bookCarPrompt(Scanner scanner) {
        System.out.println("Enter user id: ");
        String userId = scanner.nextLine();

        System.out.println("Enter car id: ");
        String carId = scanner.nextLine();

        System.out.println("Enter start date: ");
        String startDate = scanner.nextLine();

        System.out.println("Enter end date: ");
        String endDate = scanner.nextLine();

        carBookingService.bookCar(UUID.fromString(userId), UUID.fromString(carId), LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
