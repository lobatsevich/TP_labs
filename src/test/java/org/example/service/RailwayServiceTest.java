package org.example.service;

import org.example.entity.FreightWagon;
import org.example.entity.Wagon;
import org.example.entity.WagonStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailwayServiceTest {
    private RailwayService service;
    private Wagon wagon;

    @BeforeEach
    void setUp() {
        service = new RailwayService();
        wagon = new FreightWagon(
                "FR-001", "Minsk", "Belarus Railway", 20000, 60000
        );
        service.addWagon(wagon);
    }

    @Test
    void shouldAddAndFindWagon() {
        assertEquals(1, service.getAllWagons().size());
        assertSame(wagon, service.getWagon("FR-001"));
    }

    @Test
    void shouldRejectNullDuplicatesAndUnknownWagons() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addWagon(null));
        assertThrows(IllegalArgumentException.class,
                () -> service.addWagon(new FreightWagon(
                        "FR-001", "Brest", "Owner", 20000, 60000)));
        assertThrows(IllegalArgumentException.class,
                () -> service.getWagon("UNKNOWN"));
    }

    @Test
    void shouldManageWagonAndProtectList() {
        service.sendWagon("FR-001");
        assertEquals(WagonStatus.IN_TRANSIT, wagon.getStatus());
        assertThrows(UnsupportedOperationException.class,
                () -> service.getAllWagons().clear());
    }

    @Test
    void shouldRepairWagon() {
        service.markWagonForRepair("FR-001");
        service.repairWagon("FR-001");
        assertEquals(WagonStatus.READY_TO_DEPART, wagon.getStatus());
    }
}
