package org.example.stone.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.stone.entity.Stone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NecklaceAction {
    private static final Logger logger = LogManager.getLogger(NecklaceAction.class);

    public double calculateTotalWeight(List<Stone> necklace) {
        double totalWeight = 0;
        for (Stone stone : necklace) {
            totalWeight += stone.getWeight();
        }

        logger.info("Общий вес ожерелья: {} карат", totalWeight);
        return totalWeight;
    }

    public double calculateTotalCost(List<Stone> necklace) {
        double totalCost = 0;
        for (Stone stone : necklace) {
            totalCost += stone.calculateCost();
        }

        logger.info("Общая стоимость ожерелья: {}", totalCost);
        return totalCost;
    }

    public void sortByValue(List<Stone> necklace) {
        logger.debug("Запуск сортировки ожерелья.");
        necklace.sort(Comparator.comparingDouble(Stone::getPricePerCarat).reversed());
        logger.info("Ожерелье отсортировано по ценности.");
    }

    public List<Stone> findByTransparencyRange(List<Stone> necklace, int min, int max) {
        logger.info("Поиск камней с прозрачностью от {}% до {}%", min, max);
        List<Stone> matchedStones = new ArrayList<>();
        for (Stone stone : necklace) {
            if (stone.getTransparency() >= min && stone.getTransparency() <= max) {
                matchedStones.add(stone);
            }
        }
        logger.info("Поиск завершен. Найдено камней: {}", matchedStones.size());
        return matchedStones;
    }
}
