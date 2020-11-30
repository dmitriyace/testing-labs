package ru.ifmo.se.lab2.func;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.ifmo.se.lab2.func.Calculator;
import ru.ifmo.se.lab2.func.FunctionBuilder;

public class CalculatorTest {
    private final static int ARGUMENT = 0;
    private final static int EXPECTED = 1;
    private final static double DEFAULT_DELTA = 1e-3;

    private static Calculator calc;

    @BeforeClass
    public static void setUp() {
        var builder = new FunctionBuilder();
        calc = new Calculator(builder.cos(), builder.tan(), builder.cot(), builder.log());
    }

    @Test
    public void testCosine() {
        double[][] params = {
                {-0.83, 0.6748},
                {0.42, 0.913},

                // round values
                {0, 1},
                {-Math.PI / 2, 0},
                {Math.PI / 2, 0},

                // special values
                {Double.NaN, Double.NaN},
                {Double.NEGATIVE_INFINITY, Double.NaN},
                {Double.POSITIVE_INFINITY, Double.NaN},

        };

        for (var param : params) {
            Assert.assertEquals(param[EXPECTED], calc.cos(param[ARGUMENT], DEFAULT_DELTA), DEFAULT_DELTA);
        }
    }

    @Test
    public void testCotangent() {
        double[][] params = {
                {0.71, 1.1634},
                {2.69, -2.0617},

                // extremum
                {Math.PI / 2, 0},

                // special values
                {0, Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY, Double.NaN},
                {Double.POSITIVE_INFINITY, Double.NaN},

        };

        for (var param : params) {
            Assert.assertEquals(param[EXPECTED], calc.cot(param[ARGUMENT], DEFAULT_DELTA), DEFAULT_DELTA);
        }
    }

    @Test
    public void testTan() {
        double[][] params = {
                {0.78, 0.9892},
                {-1.19, -2.4978},
                {0, 0},

                // special values
                {Math.PI / 2, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.NaN},
                {Double.NEGATIVE_INFINITY, Double.NaN},
        };

        for (var param : params) {
            Assert.assertEquals(param[EXPECTED], calc.tan(param[ARGUMENT], DEFAULT_DELTA), DEFAULT_DELTA);
        }
    }

    @Test
    public void testDecimalLog() {
        double[][] params = {
                {0.42, -0.3767},
                {1.53, 0.1846},
                {1, 0},

                // special values
                {0, Double.NEGATIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {-1, Double.NaN},
                {Double.NaN, Double.NaN},
        };

        for (var param : params) {
            Assert.assertEquals(param[EXPECTED], calc.log(10, param[ARGUMENT], DEFAULT_DELTA), DEFAULT_DELTA);
        }
    }
}
