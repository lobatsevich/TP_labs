package org.example.stone.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.stone.entity.Stone;
import org.example.stone.factory.StoneFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoneDataParser {
    private static final Logger logger = LogManager.getLogger(StoneDataParser.class);

    public List<Stone> parseStonesFromFile(String filePath) {
        List<Stone> stones = new ArrayList<>();
        logger.info("Начало чтения файла с камнями: {}", filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    String[] parts = line.split(";");
                    if (parts.length < 6) {
                        logger.warn("Пропущена поврежденная строка (мало колонок): {}", line);
                        continue;
                    }

                    String type = parts[0].trim();
                    String name = parts[1].trim();
                    double weight = Double.parseDouble(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    int transparency = Integer.parseInt(parts[4].trim());
                    String specialParam = parts[5].trim();

                    if (weight <= 0 || price <= 0 || transparency < 0 || transparency > 100) {
                        logger.error("Заведомо нереальные параметры камня: {}. Строка проигнорирована.", line);
                        continue;
                    }

                    Stone stone = StoneFactory.createStone(type, name, weight, price, transparency, specialParam);
                    stones.add(stone);
                    logger.debug("Успешно создан объект камня: {}", name);

                } catch (Exception e) {
                    logger.error("Ошибка распознавания строки '{}'. Причина: {}", line, e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.fatal("Не удалось открыть файл данных: {}", e.getMessage());
        }

        logger.info("Парсинг завершен. Успешно собрано камней: {}", stones.size());
        return stones;
    }
}
