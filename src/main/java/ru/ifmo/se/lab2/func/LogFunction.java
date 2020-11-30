package ru.ifmo.se.lab2.func;

public class LogFunction implements MathFunction {

    /**
     * Returns the natural logarithm of a value.
     * If the argument is NaN or less than zero, then the result is NaN.
     * If the argument is positive infinity, then the result is positive infinity.
     * If the argument is zero, then the result is negative infinity.
     *
     * @throws IllegalArgumentException if the delta less than or equal to 0
     * @see <a href="https://en.wikipedia.org/wiki/Natural_logarithm#Series">
     * Expansions of the Logarithm Function </a>
     */
    @Override
    public double calculate(double arg, double delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("delta should be greater than 0");
        }

        if (Double.isNaN(arg) || arg < 0) {
            return Double.NaN;
        }

        if (arg==Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        if (arg==0) {
            return Double.NEGATIVE_INFINITY;
        }

        return 2 * (arg - 1) / (arg + 1) * sum(n -> Math.pow(Math.pow(arg - 1, 2) / Math.pow(arg + 1, 2), n)
                / (2 * n + 1), delta);
    }
}
