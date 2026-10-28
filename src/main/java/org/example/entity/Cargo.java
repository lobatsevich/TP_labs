package org.example.entity;

public class Cargo {
    private final String name;
    private final double weight;
    private final boolean fragile;
    private final double value;

    public Cargo(String name, double weight, boolean fragile, double value) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название груза не может быть пустым");
        }
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException(
                    "Вес груза должен быть положительным и конечным"
            );
        }
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(
                    "Стоимость груза должна быть неотрицательной и конечной"
            );
        }
        this.name = name;
        this.weight = weight;
        this.fragile = fragile;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isFragile() {
        return fragile;
    }

    public double getValue() {
        return value;
    }
}
