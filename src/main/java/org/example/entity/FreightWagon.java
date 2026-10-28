package org.example.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.interfaces.Loadable;

public class FreightWagon extends Wagon implements Loadable {
    private static final Logger logger = LogManager.getLogger(FreightWagon.class);

    private final double maxCargoWeight;
    private Cargo cargo;

    public FreightWagon(
            String registrationNumber,
            String destination,
            String owner,
            double tareWeight,
            double maxCargoWeight
    ) {
        super(registrationNumber, destination, owner, tareWeight);
        if (!Double.isFinite(maxCargoWeight) || maxCargoWeight <= 0) {
            throw new IllegalArgumentException(
                    "Максимальный вес груза должен быть положительным и конечным"
            );
        }
        this.maxCargoWeight = maxCargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public double getTotalWeight() {
        return getTareWeight() + (cargo == null ? 0 : cargo.getWeight());
    }

    @Override
    public void load(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("Груз не может быть null");
        }
        if (getStatus() != WagonStatus.READY_TO_DEPART) {
            throw new IllegalStateException("Вагон не готов к погрузке");
        }
        if (this.cargo != null) {
            throw new IllegalStateException("В вагоне уже есть груз");
        }
        if (cargo.getWeight() > maxCargoWeight) {
            throw new IllegalArgumentException(
                    "Вес груза превышает максимально допустимый"
            );
        }
        setStatus(WagonStatus.LOADING);
        this.cargo = cargo;
        setStatus(WagonStatus.READY_TO_DEPART);
        logger.info("Груз '{}' загружен в вагон {}",
                cargo.getName(), getRegistrationNumber());
    }

    @Override
    public Cargo unload() {
        if (getStatus() != WagonStatus.READY_TO_DEPART) {
            throw new IllegalStateException("Вагон не готов к разгрузке");
        }
        if (cargo == null) {
            throw new IllegalStateException("В вагоне нет груза");
        }
        setStatus(WagonStatus.UNLOADING);
        Cargo unloadedCargo = cargo;
        cargo = null;
        setStatus(WagonStatus.READY_TO_DEPART);
        logger.info("Груз '{}' выгружен из вагона {}",
                unloadedCargo.getName(), getRegistrationNumber());
        return unloadedCargo;
    }

    @Override
    public Cargo getCargo() {
        return cargo;
    }
}
