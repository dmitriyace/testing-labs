package ru.ifmo.se.lab1;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class TestPowerSeriesASinRun {
    public static final double delta = 1E-9;

    @Test
    public void ReturnsCorrect() {
        Assert.assertEquals(Math.asin(0.33), PowerSeriesASin.asin(0.33, delta), delta);
        Assert.assertEquals(Math.asin(0.88), PowerSeriesASin.asin(0.88, delta), delta);
        Assert.assertEquals(Math.asin(-0.33), PowerSeriesASin.asin(-0.33, delta), delta);
        Assert.assertEquals(Math.asin(-0.88), PowerSeriesASin.asin(-0.88, delta), delta);
    }

    @Test
    public void LowBorderReturnsCorrect() {
        Assert.assertEquals(-Math.PI/2, PowerSeriesASin.asin(-1, delta), delta);
    }

    @Test
    public void HighBorderReturnsCorrect() {
        Assert.assertEquals(Math.PI/2, PowerSeriesASin.asin(1, delta), delta);
    }

    @Test
    public void YAxisInterceptionReturnsCorrect() {
        Assert.assertEquals(Math.asin(0), PowerSeriesASin.asin(0, delta), delta);
    }
}
