package com.nine.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {
    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(UUID.fromString("14ed4c50-762d-4adc-87d0-51f7a9ae6c0a"), "L-XXX1", new BigDecimal(50), Brand.TESLA, true),
                new Car(UUID.fromString("4cebe925-8f69-4ee6-b1e6-cd40c08addfb"), "L-XXX2", new BigDecimal(100), Brand.TOYOTA, false),
                new Car(UUID.fromString("39620ec0-e5db-474b-a7d3-7f1ad48ced08"), "L-XXX3", new BigDecimal(150), Brand.AUDI, false),
                new Car(UUID.fromString("c63d3aba-242e-41a4-9ce0-63685597e569"), "L-XXX4", new BigDecimal(200), Brand.MERCEDES, false)
        };
    }

    public Car getCar(UUID id) {
        for (Car car : cars) {
            if (car.id.equals(id)) {
                return car;
            }
        }
        
        return null;
    }

    public BigDecimal getRentalPricePerDay(UUID id) {
        for (Car car : cars) {
            if (car.id.equals(id)) {
                return car.rentalPricePerDay;
            }
        }

        return null;
    }

    public Car[] getCars() {
        return cars;
    }
}
