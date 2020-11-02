package ru.ifmo.se.lab1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPowerSeries {
    @Test
    public void LowBorderReturnsCorrect() {
        assertEquals(-Math.PI/2, PowerSeries.arcsin(-1), PowerSeries.eps);
    }

    @Test
    public void HighBorderReturnsCorrect() {
        assertEquals(Math.PI/2, PowerSeries.arcsin(1), PowerSeries.eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RightOutOfBoundsThrowsException() {
        PowerSeries.arcsin(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void LeftOutOfBoundsThrowsException() {
        PowerSeries.arcsin(-2);
    }
    @Test
    public void ReturnsCorrect() {
        assertEquals(Math.asin(0.33), PowerSeries.arcsin(0.33), PowerSeries.eps);
        assertEquals(Math.asin(0.88), PowerSeries.arcsin(0.88), PowerSeries.eps);
        assertEquals(Math.asin(-0.33), PowerSeries.arcsin(-0.33), PowerSeries.eps);
        assertEquals(Math.asin(-0.88), PowerSeries.arcsin(-0.88), PowerSeries.eps);
    }

    @Test
    public void YAxisInterceptionReturnsCorrect() {
        assertEquals(Math.asin(0), PowerSeries.arcsin(0), PowerSeries.eps);
    }
}
