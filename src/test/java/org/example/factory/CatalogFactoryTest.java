package org.example.factory;

import org.example.service.Catalog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogFactoryTest {

    @Test
    void shouldCreateCatalog() {
        CatalogFactory factory = new CatalogFactory();

        Catalog catalog = factory.createCatalog();

        assertNotNull(catalog);
    }

    @Test
    void shouldCreateDifferentCatalogs() {
        CatalogFactory factory = new CatalogFactory();

        Catalog first = factory.createCatalog();
        Catalog second = factory.createCatalog();

        assertNotSame(first, second);
    }
}