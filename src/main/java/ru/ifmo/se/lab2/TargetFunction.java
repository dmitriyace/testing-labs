package ru.ifmo.se.lab2;

import ru.ifmo.se.lab2.func.Calculator;
import ru.ifmo.se.lab2.func.MathFunction;

import java.util.function.Supplier;

public class TargetFunction implements MathFunction {
    private final Calculator calc;

    public TargetFunction(Calculator calc) {
        this.calc = calc;
    }

    @Override
    public double calculate(double arg, double delta) {
        var extendedDelta = delta * delta;

        Supplier<Double> sup;
        if (arg <= 0) {
//            sup = () -> Math.pow(Math.pow(calc.tan(arg, extendedDelta) / calc.csc(arg, extendedDelta), 3) *
//                    calc.cos(arg, extendedDelta) / calc.csc(arg, extendedDelta), 3);
            sup = () -> (
                    (
                            (Math.pow(Math.pow((calc.cos(arg, extendedDelta) + calc.tan(arg, extendedDelta)), 3), 3))
                            + calc.cot(arg, extendedDelta)
                    )
                    + calc.cot(arg, extendedDelta)
            );
        } else {
            if (arg==Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            }

//            sup = () -> Math.pow(((calc.log(5, arg, extendedDelta) / calc.log(10, arg, extendedDelta)) +
//                    Math.pow(calc.log(10, arg, extendedDelta), 3)) * calc.log(arg, extendedDelta), 2) -
//                    (calc.log(2, arg, extendedDelta) - calc.log(3, arg, extendedDelta) -
//                            (calc.log(arg, extendedDelta) / calc.log(5, arg, extendedDelta)));
            sup = () -> (
                    (
                            (
                                    (
                                            (
                                                    calc.log(10, arg, extendedDelta) * calc.log(arg, extendedDelta)
                                            ) * calc.log(3, arg, extendedDelta)
                                    ) + calc.log(3, arg, extendedDelta)
                            )
                            -
                            ((
                                    (calc.log(3, arg, extendedDelta) / calc.log(5, arg, extendedDelta))
                                            - calc.log(10, arg, extendedDelta)
                            ) - calc.log(2, arg, extendedDelta)
                            )
                    )
                    -
                    (
                            ((
                                    (calc.log(arg, extendedDelta) - calc.log(5, Math.pow(arg, 3), extendedDelta))
                                    /
                                    (calc.log(3, arg, extendedDelta))
                            ) * calc.log(2, arg, extendedDelta))
                    )
            );

        }
        return calculateUntilDelta(sup, delta);
    }

    private double calculateUntilDelta(Supplier<Double> sup, double delta) {
        var result = 0.0;
        var prevResult = 0.0;

        do {
            prevResult = result;
            result = sup.get();
        } while (Math.abs(prevResult - result) > delta);

        return result;
    }
}
