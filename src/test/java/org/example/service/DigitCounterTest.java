package org.example.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DigitCounterServiceTest {

    static Stream<ToIntBiFunction<Integer, Integer>> counterImplementations() {
        return Stream.of(
                DigitCounterService.CUSTOM_COUNTER::count,
                DigitCounterService.STANDARD_COUNTER::applyAsInt
        );
    }

    @ParameterizedTest
    @MethodSource("counterImplementations")
    void countsDigitsInPositiveNumber(ToIntBiFunction<Integer, Integer> counter) {
        assertEquals(3, counter.applyAsInt(122320, 2));
        assertEquals(1, counter.applyAsInt(456, 5));
    }

    @ParameterizedTest
    @MethodSource("counterImplementations")
    void handlesNegativeNumberAndZero(ToIntBiFunction<Integer, Integer> counter) {
        assertEquals(3, counter.applyAsInt(-10001, 0));
        assertEquals(1, counter.applyAsInt(0, 0));
    }

    @ParameterizedTest
    @MethodSource("counterImplementations")
    void handlesIntegerMinValueProblem(ToIntBiFunction<Integer, Integer> counter) {
        // Тот самый хитрый тест на Integer.MIN_VALUE (-2147483648)
        // В этом числе две четверки (на позициях 3 и 7 с конца)
        assertEquals(3, counter.applyAsInt(Integer.MIN_VALUE, 4));
    }

    @ParameterizedTest
    @MethodSource("counterImplementations")
    void rejectsNonDigit(ToIntBiFunction<Integer, Integer> counter) {
        assertThrows(IllegalArgumentException.class, () -> counter.applyAsInt(123, 10));
        assertThrows(IllegalArgumentException.class, () -> counter.applyAsInt(123, -1));
    }
}
