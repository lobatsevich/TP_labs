package org.example.stone.factory;

import org.example.stone.entity.PreciousStone;
import org.example.stone.entity.SemiPreciousStone;
import org.example.stone.entity.Stone;

public class StoneFactory {
    private static final String PRECIOUS = "precious";
    private static final String SEMIPRECIOUS = "semiprecious";
    public static Stone createStone(String type, String name, double weight, double pricePerCarat, int transparency, String specialParameter) {
        if (PRECIOUS.equalsIgnoreCase(type)) {
            int clarity = Integer.parseInt(specialParameter);
            return new PreciousStone(name, weight, pricePerCarat, transparency, clarity);
        } else if (SEMIPRECIOUS.equalsIgnoreCase(type)) {
            return new SemiPreciousStone(name, weight, pricePerCarat, transparency, specialParameter);
        }
        throw new IllegalArgumentException("Неизвестный тип камня: " + type);
    }
}
