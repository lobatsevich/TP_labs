package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RailwayService {
    private static final Logger logger =
            LogManager.getLogger(RailwayService.class);

    private final List<Wagon> wagons = new ArrayList<>();

    public void addWagon(Wagon wagon) {
        if (wagon == null) {
            throw new IllegalArgumentException("Вагон не может быть null");
        }
        boolean exists = wagons.stream().anyMatch(item ->
                item.getRegistrationNumber().equals(
                        wagon.getRegistrationNumber()
                ));
        if (exists) {
            throw new IllegalArgumentException(
                    "Вагон с таким регистрационным номером уже существует"
            );
        }
        wagons.add(wagon);
        logger.info("Вагон {} добавлен в железнодорожную систему",
                wagon.getRegistrationNumber());
    }

    public Wagon getWagon(String registrationNumber) {
        return wagons.stream()
                .filter(wagon -> wagon.getRegistrationNumber().equals(
                        registrationNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Вагон не найден: " + registrationNumber));
    }

    public List<Wagon> getAllWagons() {
        return List.copyOf(wagons);
    }

    public void sendWagon(String registrationNumber) {
        getWagon(registrationNumber).sendToDestination();
    }

    public void serviceWagon(String registrationNumber) {
        getWagon(registrationNumber).service();
    }

    public void repairWagon(String registrationNumber) {
        getWagon(registrationNumber).repair();
    }

    public void markWagonForRepair(String registrationNumber) {
        getWagon(registrationNumber).requireRepair();
    }
}
