package org.example.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.interfaces.Manageable;
import org.example.interfaces.Repairable;
import org.example.interfaces.Serviceable;
import org.example.interfaces.Transportable;

public abstract class Wagon implements
        Manageable, Transportable, Repairable, Serviceable {

    private static final Logger logger = LogManager.getLogger(Wagon.class);

    private final String registrationNumber;
    private String destination;
    private String owner;
    private final double tareWeight;
    private WagonStatus status = WagonStatus.READY_TO_DEPART;

    protected Wagon(
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight
    ) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Регистрационный номер не может быть пустым"
            );
        }
        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Пункт назначения не может быть пустым");
        }
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Владелец не может быть пустым");
        }
        if (!Double.isFinite(tareWeight) || tareWeight <= 0) {
            throw new IllegalArgumentException(
                    "Вес тары должен быть положительным и конечным"
            );
        }
        this.registrationNumber = registrationNumber;
        this.destination = destination;
        this.owner = owner;
        this.tareWeight = tareWeight;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Пункт назначения не может быть пустым");
        }
        this.destination = destination;
        logger.info("Пункт назначения вагона {} изменён на {}",
                registrationNumber, destination);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Владелец не может быть пустым");
        }
        this.owner = owner;
        logger.info("Владелец вагона {} изменён на {}", registrationNumber, owner);
    }

    @Override
    public double getTareWeight() {
        return tareWeight;
    }

    public WagonStatus getStatus() {
        return status;
    }

    protected void setStatus(WagonStatus status) {
        this.status = status;
    }

    @Override
    public void sendToDestination() {
        if (status != WagonStatus.READY_TO_DEPART) {
            throw new IllegalStateException("Вагон не готов к отправлению");
        }
        status = WagonStatus.IN_TRANSIT;
        logger.info("Вагон {} отправился в {}", registrationNumber, destination);
    }

    @Override
    public void service() {
        if (status == WagonStatus.IN_TRANSIT) {
            throw new IllegalStateException(
                    "Нельзя обслуживать вагон в пути"
            );
        }
        if (status == WagonStatus.IN_REPAIR
                || status == WagonStatus.REQUIRES_REPAIR) {
            throw new IllegalStateException(
                    "Перед обслуживанием вагон требует ремонта"
            );
        }
        status = WagonStatus.READY_TO_DEPART;
        logger.info("Вагон {} обслужен", registrationNumber);
    }

    @Override
    public void requireRepair() {
        if (status == WagonStatus.IN_TRANSIT) {
            throw new IllegalStateException(
                    "Нельзя изменить статус ремонта вагона в пути"
            );
        }
        status = WagonStatus.REQUIRES_REPAIR;
        logger.warn("Вагон {} требует ремонта", registrationNumber);
    }

    @Override
    public void repair() {
        if (status != WagonStatus.REQUIRES_REPAIR
                && status != WagonStatus.IN_REPAIR) {
            throw new IllegalStateException("Вагон не требует ремонта");
        }
        status = WagonStatus.IN_REPAIR;
        logger.info("Ремонт вагона {} начат", registrationNumber);
        status = WagonStatus.READY_TO_DEPART;
        logger.info("Вагон {} отремонтирован", registrationNumber);
    }
}
