package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreightWagonTest {
    private FreightWagon wagon;
    private Cargo cargo;

    @BeforeEach
    void setUp() {
        wagon = new FreightWagon(
                "FR-001", "Minsk", "Belarus Railway", 20000, 60000
        );
        cargo = new Cargo("Electronics", 5000, true, 100000);
    }

    @Test
    void shouldLoadAndUnloadCargo() {
        wagon.load(cargo);
        assertEquals(cargo, wagon.getCargo());
        assertEquals(25000, wagon.getTotalWeight());
        assertEquals(cargo, wagon.unload());
        assertNull(wagon.getCargo());
        assertEquals(20000, wagon.getTotalWeight());
    }

    @Test
    void shouldRejectInvalidLoadingOperations() {
        assertThrows(IllegalArgumentException.class, () -> wagon.load(null));
        assertThrows(IllegalStateException.class, wagon::unload);
        wagon.load(cargo);
        assertThrows(IllegalStateException.class,
                () -> wagon.load(new Cargo("Books", 1000, false, 500)));
        assertTrue(wagon.getCargo().isFragile());
        assertEquals(100000, wagon.getCargo().getValue());
    }

    @Test
    void shouldRejectCargoAboveCapacityAndInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () ->
                wagon.load(new Cargo("Equipment", 70000, false, 50000)));
        assertThrows(IllegalArgumentException.class, () ->
                new FreightWagon("FR-002", "Minsk", "Owner", 20000, 0));
    }
}
