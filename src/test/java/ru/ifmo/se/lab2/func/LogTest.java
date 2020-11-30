package ru.ifmo.se.lab2.func;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ifmo.se.lab2.func.FunctionBuilder;
import ru.ifmo.se.lab2.func.MathFunction;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LogTest {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;

    private final double arg;
    private final double expected;
    private final double delta;
    private final MathFunction logFunction;

    public LogTest(double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
        this.logFunction = new FunctionBuilder().log();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {0.53, -0.6348, DEFAULT_DELTA},
                {2.53, 0.9282, DEFAULT_DELTA},

                // check round values
                {1, 0, EXTENDED_DELTA},

                // check special values
                {-1, Double.NaN, EXTENDED_DELTA},
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {0, Double.NEGATIVE_INFINITY, EXTENDED_DELTA},
        });
    }

    @Test
    public void testCalculation() {
        Assert.assertEquals(expected, logFunction.calculate(arg, delta), delta);
    }
}
