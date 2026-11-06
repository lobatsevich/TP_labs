package org.example.service;

import org.example.interfaces.TripleFunction;

import java.util.Objects;
import java.util.function.ToDoubleFunction;

public final class DiscriminantService {
    private static final TripleFunction CUSTOM_DISCRIMINANT =
            (a, b, c) -> b * b - 4 * a * c;

    private static final ToDoubleFunction<double[]> STANDARD_DISCRIMINANT =
            coefficients -> {
                validateCoefficients(coefficients);
                return coefficients[1] * coefficients[1] - 4 * coefficients[0] * coefficients[2];
            };

    public static double calculateWithCustomFunction(double a, double b, double c) {
        return CUSTOM_DISCRIMINANT.apply(a, b, c);
    }

    public static double calculateWithStandardFunction(double[] coefficients) {
        return STANDARD_DISCRIMINANT.applyAsDouble(coefficients);
    }

    private static void validateCoefficients(double[] coefficients) {
        Objects.requireNonNull(coefficients, "Коэффициенты не могут быть null");
        if (coefficients.length != 3) {
            throw new IllegalArgumentException("Нужно передать ровно три коэффициента");
        }
    }
}
