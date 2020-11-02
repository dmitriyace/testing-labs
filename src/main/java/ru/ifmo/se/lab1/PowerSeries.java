package ru.ifmo.se.lab1;

public class PowerSeries {
    public static final double eps = 1E-9;

    /**
     * Returns the arcsin of a value. If the argument is NaN, then the result is NaN.
     *
     * @throws IllegalArgumentException if the delta less than or equal to 0
     * @see <a href="https://proofwiki.org/wiki/Power_Series_Expansion_for_Real_Arcsine_Function">
     * Power Series Expansion for Real Arcsine Function </a>
     */
    public static double arcsin(double x) {
        double curr = x;
        double result = 0.0;
        int n = 1;
        if (x == 1.0) {
            return Math.PI/2;
        } else if (x == -1.0) {
            return -Math.PI/2;
        } else if (Math.abs(x) < 1) {
            while (Math.abs(curr) >= eps/10) {
                result += curr;
                curr = curr * x * x * (2 * n - 1) * (2 * n - 1) / ((2 * n) * (2 * n + 1));
                n++;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
