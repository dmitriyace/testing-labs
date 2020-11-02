package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;


public class TestPowerSeriesASinFail {
    @Test
    public void testReturnNanWhenNanArgument() {
        Assert.assertTrue(Double.isNaN(PowerSeriesASin.asin(Double.NaN, 0.1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowingExceptionWhenDeltaIsZero() {
        PowerSeriesASin.asin(0.5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowingExceptionWhenDeltaLessThanZero() {
        PowerSeriesASin.asin(0.5, -1);
    }
}
