package ru.ifmo.se.lab1;


import java.util.function.BiFunction;

public class PowerSeriesASin {
    /**
     * Returns the arcsin of a value. If the argument is NaN, then the result is NaN.
     *
     * @throws IllegalArgumentException if the delta less than or equal to 0
     * @see <a href="https://proofwiki.org/wiki/Power_Series_Expansion_for_Real_Arcsine_Function">
     * Power Series Expansion for Real Arcsine Function </a>
     */
    public static double asin(double x, double delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("delta should be greater than 0");
        }

        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (x == 1.0) {
            return Math.PI/2;
        } else if (x == -1.0) {
            return -Math.PI/2;
        } else {
            return sum((i, previousValue) -> (
                Double.compare(previousValue, 0.0) == 1
                    ? calculateFormula(x, x, i)
                    : calculateFormula(previousValue, x, i)
            ) , delta);
        }
    }

    private static double calculateFormula(double currentValue, double x, double i) {
        return currentValue * x * x * (2 * i - 1) * (2 * i - 1) / ((2 * i) * (2 * i + 1));
    }

    private static double sum(BiFunction<Integer, Double, Double> func, double delta) {
        var result = 0.0;
        var prevResult = 0.0;
        var newValue = 0.0;

        var i = 0;
        do {
            prevResult = result;
            newValue = func.apply(i++, newValue);
            result += newValue;
        } while (Math.abs(prevResult - result) > delta);

        return result;
    }
}
