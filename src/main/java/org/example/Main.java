package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;
import org.example.service.DigitCounterService;
import org.example.service.DiscriminantService;
import org.example.service.PolygonService;

import java.util.List;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        double customDiscriminant = DiscriminantService.calculateWithCustomFunction(1, -3, 2);
        double standardDiscriminant =
                DiscriminantService.calculateWithStandardFunction(new double[]{1, -3, 2});

        int number = -122320;
        int targetDigit = 2;
        int digitCountCustom = DigitCounterService.CUSTOM_COUNTER.count(number, targetDigit);
        int digitCountStandard = DigitCounterService.STANDARD_COUNTER.applyAsInt(number, targetDigit);

        List<Point> polygon = List.of(
                new Point(0, 0), new Point(5, 0), new Point(5, 5), new Point(0, 5));
        boolean insideCustom  = PolygonService.CUSTOM_WAY.test(new Point(2, 3), polygon);
        boolean insideStandard = PolygonService.STANDARD_WAY.test(new Point(2, 3), polygon);

        LOGGER.info("Дискриминант (Свой интерфейс): {}", customDiscriminant);
        LOGGER.info("Дискриминант (Стандартный интерфейс): {}", standardDiscriminant);
        LOGGER.info("Количество цифр {} (Свой интерфейс): {}", targetDigit, digitCountCustom);
        LOGGER.info("Количество цифр {} (Стандартный интерфейс): {}", targetDigit, digitCountStandard);
        LOGGER.info("Точка находится внутри многоугольника (Свой интерфейс): {}", insideCustom);
        LOGGER.info("Точка находится внутри многоугольника (Свой интерфейс): {}", insideStandard);
    }
}
