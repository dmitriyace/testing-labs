package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.ifmo.se.lab2.func.Calculator;
import ru.ifmo.se.lab2.func.FunctionBuilder;
import ru.ifmo.se.lab2.func.MathFunction;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TargetFunctionTest {
    private final static double DEFAULT_DELTA = 1e-3;
    private final static double EXTENDED_DELTA = 1e-6;

    private static Calculator calc;

    private final double arg;
    private final double expected;
    private final double delta;
    private final MathFunction targetFunction;

    public TargetFunctionTest(double arg, double expected, double delta) {
        this.arg = arg;
        this.expected = expected;
        this.delta = delta;
        this.targetFunction = new TargetFunction(calc);
    }

    @BeforeClass
    public static void setUpCalculator() {
        var builder = new FunctionBuilder();
        calc = new Calculator(builder.cos(), builder.tan(), builder.cot(), builder.log());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {-10.459, -1171.829, DEFAULT_DELTA},
                {-3 * Math.PI, -1.4711759877839594E13, EXTENDED_DELTA},
                {-8.43, 2.2546, DEFAULT_DELTA},

                {-7.307, -4.036, DEFAULT_DELTA},
                {-2 * Math.PI, 9.604132030246383E13, EXTENDED_DELTA},
                {-5.233, 1429.395, DEFAULT_DELTA},

                {0, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
                {Double.NEGATIVE_INFINITY, Double.NaN, EXTENDED_DELTA},

                {0.055, -23.163, DEFAULT_DELTA},
                {0.076, -18.9411, DEFAULT_DELTA},
                {0.129, -13.3727, DEFAULT_DELTA},
                {0.184, -10.419, DEFAULT_DELTA},
                {0.47, -4.7734, DEFAULT_DELTA},
                {1.149, -0.8865, DEFAULT_DELTA},
                {4.86, 6.6691, DEFAULT_DELTA},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, EXTENDED_DELTA},
        });
    }

    @Test
    public void testCalculation() {
        Assert.assertEquals(expected, targetFunction.calculate(arg, delta), delta);
    }
}
