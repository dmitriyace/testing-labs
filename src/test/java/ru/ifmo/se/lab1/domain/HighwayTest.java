package ru.ifmo.se.lab1.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HighwayTest {
    private Highway highway;

    @Before
    public void setup() {
        highway = new Highway("test highway");
    }

    @Test
    public void testTurningOnTheHighway() {
        var c1 = Mockito.mock(Car.class);
        highway.turnOnHighway(c1);

        var c2 = Mockito.mock(Car.class);
        highway.turnOnHighway(c2);

        highway.changeSpeed(Mockito.mock(Message.class));
        Mockito.verify(c1, Mockito.times(1)).receive(Mockito.any());
        Mockito.verify(c2, Mockito.times(1)).receive(Mockito.any());

        highway.turnOffHighway(c2);
        highway.changeSpeed(Mockito.mock(Message.class));
        Mockito.verify(c1, Mockito.times(2)).receive(Mockito.any());
        Mockito.verify(c2, Mockito.times(1)).receive(Mockito.any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTurningOnTwoTimes() {
        var c1 = Mockito.mock(Car.class);
        highway.turnOnHighway(c1);
        highway.turnOnHighway(c1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void turnOffUnknownCar() {
        var c1 = Mockito.mock(Car.class);
        highway.turnOffHighway(c1);
    }
}
