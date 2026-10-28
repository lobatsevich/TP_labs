package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerWagonTest {
    @Test
    void shouldBoardAndDisembarkPassengers() {
        PassengerWagon wagon = new PassengerWagon(
                "PS-001", "Brest", "Belarus Railway", 40000, 50
        );
        wagon.boardPassengers(20);
        assertEquals(20, wagon.getPassengerCount());
        assertEquals(30, wagon.getAvailableSeats());
        wagon.disembarkPassengers(5);
        assertEquals(15, wagon.getPassengerCount());
    }

    @Test
    void shouldRejectInvalidPassengerOperations() {
        PassengerWagon wagon = new PassengerWagon(
                "PS-001", "Brest", "Owner", 40000, 50
        );
        assertThrows(IllegalArgumentException.class,
                () -> wagon.boardPassengers(51));
        assertThrows(IllegalArgumentException.class,
                () -> wagon.boardPassengers(-1));
        wagon.boardPassengers(5);
        assertThrows(IllegalArgumentException.class,
                () -> wagon.disembarkPassengers(6));
        wagon.sendToDestination();
        assertThrows(IllegalStateException.class,
                () -> wagon.boardPassengers(1));
    }

    @Test
    void shouldRejectInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () ->
                new PassengerWagon("PS-002", "Brest", "Owner", 40000, 0));
    }
}
