package com.nine;

import com.nine.booking.CarBookingService;
import com.nine.car.Car;
import com.nine.car.CarService;
import com.nine.user.User;
import com.nine.user.UserService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static CarBookingService carBookingService  = new CarBookingService();
    public static CarService carService  = new CarService();
    public static UserService userService  = new UserService();

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
        System.out.println("Select user: ");
        User selectedUser = getSelectedUser(scanner);

        System.out.println("Select car: ");
        Car selectedCar = getSelectedCar(scanner);

        System.out.println("Enter start date: ");
        String startDate = scanner.nextLine();

        System.out.println("Enter end date: ");
        String endDate = scanner.nextLine();

        carBookingService.bookCar(selectedUser.id, selectedCar.id, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    private static Car getSelectedCar(Scanner scanner) {
        for (int i = 0; i < carService.getAvailableCars().length; i++) {
            System.out.println(i + 1 + " - " + carService.getAvailableCars()[i].brand);
        }

        String carChoice = scanner.nextLine();

        return carService.getAvailableElectricCars()[Integer.parseInt(carChoice) - 1];
    }

    private static User getSelectedUser(Scanner scanner) {
        for (int i = 0; i < userService.getUsers().length; i++) {
            System.out.println(i + 1 + " - " + userService.getUsers()[i].name);
        }

        String userChoice = scanner.nextLine();

        return userService.getUsers()[Integer.parseInt(userChoice) - 1];
    }
}
