package org.example.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleFunctionTest {
    @Test
    void appliesFunctionToThreeArguments() {
        TripleFunction sum = (first, second, third) -> first + second + third;

        assertEquals(6.0, sum.apply(1, 2, 3));
    }
}
