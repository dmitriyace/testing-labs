package ru.ifmo.se.lab2.func;

import java.util.function.Function;

@FunctionalInterface
public interface MathFunction {
    double calculate(double arg, double delta);

    default double sum(Function<Integer, Double> func, double delta) {
        var result = 0.0;
        var prevResult = 0.0;

        var i = 0;
        do {
            prevResult = result;
            result += func.apply(i++);
        } while (Math.abs(prevResult - result) > delta);

        return result;
    }

    default double factorial(int n) {
        var fact = 1.0;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
