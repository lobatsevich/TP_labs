package org.example.service;

import org.example.model.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PolygonServiceTest {
    private static final List<Point> SQUARE = List.of(
            new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4));

    static Stream<BiPredicate<Point, List<Point>>> polygonImplementations() {
        return Stream.of(
                PolygonService.CUSTOM_WAY::test,
                PolygonService.STANDARD_WAY
        );
    }

    @ParameterizedTest
    @MethodSource("polygonImplementations")
    void recognizesInsidePoint(BiPredicate<Point, List<Point>> service) {
        assertTrue(service.test(new Point(2, 2), SQUARE));
    }

    @ParameterizedTest
    @MethodSource("polygonImplementations")
    void recognizesOutsidePoint(BiPredicate<Point, List<Point>> service) {
        assertFalse(service.test(new Point(5, 2), SQUARE));
    }

    @ParameterizedTest
    @MethodSource("polygonImplementations")
    void treatsBoundaryAsInside(BiPredicate<Point, List<Point>> service) {
        assertTrue(service.test(new Point(4, 2), SQUARE));
    }

    @ParameterizedTest
    @MethodSource("polygonImplementations")
    void rejectsPolygonWithLessThanThreePoints(BiPredicate<Point, List<Point>> service) {
        assertThrows(IllegalArgumentException.class,
                () -> service.test(new Point(0, 0), List.of(new Point(0, 0), new Point(1, 1))));
    }
}
