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
public class SinTest {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;

    private final double arg;
    private final double expected;
    private final double delta;
    private final MathFunction sinFunction;

    public SinTest(double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
        this.sinFunction = new FunctionBuilder().sin();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {-10.38, 0.8164, DEFAULT_DELTA},
                {-9.05, -0.366, DEFAULT_DELTA},

                // check round values
                {-7 * Math.PI / 2, 1, EXTENDED_DELTA},
                {-3 * Math.PI, 0, EXTENDED_DELTA},
                {-5 * Math.PI / 2, -1, EXTENDED_DELTA},

                // check special values
                {Double.NaN, Double.NaN, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
                {Double.POSITIVE_INFINITY, Double.NaN, EXTENDED_DELTA},
        });
    }

    @Test
    public void testCalculation() {
        Assert.assertEquals(expected, sinFunction.calculate(arg, delta), delta);
    }
}
