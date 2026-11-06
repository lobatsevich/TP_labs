package org.example.service;

import org.example.interfaces.CustomCounter;
import java.util.function.ToIntBiFunction;

public final class DigitCounterService {

    public static final CustomCounter CUSTOM_COUNTER =
            DigitCounterService::calculate;

    public static final ToIntBiFunction<Integer, Integer> STANDARD_COUNTER =
            DigitCounterService::calculate;

    private static int calculate(int number, int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Искомая цифра должна быть от 0 до 9");
        }

        long remaining = Math.abs((long) number);
        if (remaining == 0) {
            return digit == 0 ? 1 : 0;
        }

        int count = 0;
        while (remaining > 0) {
            if (remaining % 10 == digit) {
                count++;
            }
            remaining /= 10;
        }
        return count;
    }
}
