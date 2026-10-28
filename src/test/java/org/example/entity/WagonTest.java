package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonTest {
    private Wagon createWagon() {
        return new FreightWagon("FR-001", "Minsk", "Owner", 20000, 60000);
    }

    @Test
    void shouldCreateWagon() {
        Wagon wagon = new FreightWagon(
                "FR-001", "Minsk", "Belarus Railway", 20000, 60000
        );
        assertEquals("FR-001", wagon.getRegistrationNumber());
        assertEquals("Minsk", wagon.getDestination());
        assertEquals("Belarus Railway", wagon.getOwner());
        assertEquals(20000, wagon.getTareWeight());
        assertEquals(WagonStatus.READY_TO_DEPART, wagon.getStatus());
    }

    @Test
    void shouldChangeDestinationAndOwner() {
        Wagon wagon = createWagon();
        wagon.setDestination("Brest");
        wagon.setOwner("New Owner");
        assertEquals("Brest", wagon.getDestination());
        assertEquals("New Owner", wagon.getOwner());
    }

    @Test
    void shouldRejectEmptyProperties() {
        Wagon wagon = createWagon();
        assertThrows(IllegalArgumentException.class,
                () -> wagon.setDestination(" "));
        assertThrows(IllegalArgumentException.class,
                () -> wagon.setOwner(""));
    }

    @Test
    void shouldSendOnlyReadyWagon() {
        Wagon wagon = createWagon();
        wagon.sendToDestination();
        assertEquals(WagonStatus.IN_TRANSIT, wagon.getStatus());
        assertThrows(IllegalStateException.class, wagon::sendToDestination);
    }

    @Test
    void shouldRepairWagon() {
        Wagon wagon = createWagon();
        wagon.requireRepair();
        assertEquals(WagonStatus.REQUIRES_REPAIR, wagon.getStatus());
        wagon.repair();
        assertEquals(WagonStatus.READY_TO_DEPART, wagon.getStatus());
    }

    @Test
    void shouldRejectInvalidTareWeight() {
        assertThrows(IllegalArgumentException.class, () ->
                new FreightWagon("FR-001", "Minsk", "Owner", 0, 60000));
    }
}
