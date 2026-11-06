package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscriminantServiceTest {
    @Test
    void calculatesDiscriminantWithCustomFunction() {
        assertEquals(1.0, DiscriminantService.calculateWithCustomFunction(1, -3, 2));
    }

    @Test
    void calculatesDiscriminantWithStandardFunction() {
        assertEquals(0.0, DiscriminantService.calculateWithStandardFunction(new double[]{1, 2, 1}));
    }

    @Test
    void rejectsWrongNumberOfCoefficients() {
        assertThrows(IllegalArgumentException.class,
                () -> DiscriminantService.calculateWithStandardFunction(new double[]{1, 2}));
    }
}
