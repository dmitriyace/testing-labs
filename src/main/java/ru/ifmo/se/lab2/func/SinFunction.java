package ru.ifmo.se.lab2.func;

public class SinFunction implements MathFunction {

    /**
     * Returns the trigonometric sine of a value.
     * If the argument is NaN or an infinity, then the result is NaN.
     *
     * @throws IllegalArgumentException if the delta less than or equal to 0
     * @see <a href="https://people.math.sc.edu/girardi/m142/handouts/10sTaylorPolySeries.pdf">
     * Commonly Used Taylor Series </a>
     */
    @Override
    public double calculate(double arg, double delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("delta should be greater than 0");
        }
        return sum(n -> Math.pow(-1, n) * Math.pow(arg, 2 * n + 1) / factorial(2 * n + 1), delta);
    }
}
