package org.example.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassengerWagon extends Wagon {
    private static final Logger logger =
            LogManager.getLogger(PassengerWagon.class);

    private final int passengerCapacity;
    private int passengerCount;

    public PassengerWagon(
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight,
            int passengerCapacity
    ) {
        super(registrationNumber, destination, owner, tareWeight);
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Вместимость вагона должна быть положительной"
            );
        }
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getAvailableSeats() {
        return passengerCapacity - passengerCount;
    }

    public void boardPassengers(int count) {
        if (getStatus() != WagonStatus.READY_TO_DEPART) {
            throw new IllegalStateException(
                    "Вагон не готов к посадке пассажиров"
            );
        }
        if (count <= 0) {
            throw new IllegalArgumentException(
                    "Количество пассажиров должно быть положительным"
            );
        }
        if (passengerCount + count > passengerCapacity) {
            throw new IllegalArgumentException("Вместимость вагона превышена");
        }
        passengerCount += count;
        logger.info("{} пассажиров сели в вагон {}",
                count, getRegistrationNumber());
    }

    public void disembarkPassengers(int count) {
        if (getStatus() != WagonStatus.READY_TO_DEPART) {
            throw new IllegalStateException(
                    "Вагон не готов к высадке пассажиров"
            );
        }
        if (count <= 0 || count > passengerCount) {
            throw new IllegalArgumentException("Недопустимое количество пассажиров");
        }
        passengerCount -= count;
        logger.info("{} пассажиров вышли из вагона {}",
                count, getRegistrationNumber());
    }
}
