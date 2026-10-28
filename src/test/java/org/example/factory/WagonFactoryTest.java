package org.example.factory;

import org.example.entity.FreightWagon;
import org.example.entity.PassengerWagon;
import org.example.entity.Wagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonFactoryTest {
    private final WagonFactory factory = new WagonFactory();

    @Test
    void shouldCreateSpecificWagons() {
        assertEquals("FR-001", factory.createFreightWagon(
                "FR-001", "Minsk", "Owner", 20000, 60000
        ).getRegistrationNumber());
        assertEquals(50, factory.createPassengerWagon(
                "PS-001", "Brest", "Owner", 40000, 50
        ).getPassengerCapacity());
    }

    @Test
    void shouldCreateWagonByType() {
        Wagon wagon = factory.createWagon(
                "freight", "FR-002", "Minsk", "Owner", 20000, 60000
        );
        assertInstanceOf(FreightWagon.class, wagon);
        assertInstanceOf(PassengerWagon.class, factory.createWagon(
                "passenger", "PS-002", "Minsk", "Owner", 40000, 50
        ));
    }

    @Test
    void shouldRejectInvalidTypesAndCapacities() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createWagon(
                        "unknown", "XX-001", "Minsk", "Owner", 20000, 60000));
        assertThrows(IllegalArgumentException.class, () ->
                factory.createWagon(
                        "passenger", "PS-003", "Minsk", "Owner", 40000, 50.5));
    }
}
