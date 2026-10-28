package org.example.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.Catalog;

public class CatalogFactory {

    private static final Logger logger = LogManager.getLogger(CatalogFactory.class);

    public Catalog createCatalog() {
        logger.info("Создан новый каталог");

        return new Catalog();
    }
}