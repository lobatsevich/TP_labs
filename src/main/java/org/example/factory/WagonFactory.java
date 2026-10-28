package org.example.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.FreightWagon;
import org.example.entity.PassengerWagon;
import org.example.entity.Wagon;

public class WagonFactory {
    private static final Logger logger = LogManager.getLogger(WagonFactory.class);

    public FreightWagon createFreightWagon(
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight,
            double maxCargoWeight
    ) {
        logger.info("Создание грузового вагона {}", registrationNumber);
        return new FreightWagon(
                registrationNumber, destination, owner,
                tareWeight, maxCargoWeight
        );
    }

    public PassengerWagon createPassengerWagon(
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight,
            int passengerCapacity
    ) {
        logger.info("Создание пассажирского вагона {}", registrationNumber);
        return new PassengerWagon(
                registrationNumber, destination, owner,
                tareWeight, passengerCapacity
        );
    }

    public Wagon createWagon(
            String type,
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight,
            double capacity
    ) {
        if (type == null) {
            throw new IllegalArgumentException("Тип вагона не может быть null");
        }
        return switch (type.toLowerCase()) {
            case "freight" -> createFreightWagon(
                    registrationNumber, destination, owner, tareWeight, capacity
            );
            case "passenger" -> {
                if (!Double.isFinite(capacity)
                        || capacity != Math.floor(capacity)
                        || capacity > Integer.MAX_VALUE) {
                    throw new IllegalArgumentException(
                            "Вместимость пассажирского вагона должна быть целым числом"
                    );
                }
                yield createPassengerWagon(
                        registrationNumber, destination, owner, tareWeight,
                        (int) capacity
                );
            }
            default -> throw new IllegalArgumentException(
                    "Неизвестный тип вагона: " + type
            );
        };
    }
}
