package ru.ifmo.se.lab1.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;

public class CarTest {
    private final static MOOD m1 = MOOD.BAD;
    private final static MOOD m2 = MOOD.GOOD;

    private final static String name1 = "n1";
    private final static String mark1 = "m1";
    private final static String name2 = "n2";
    private final static String mark2 = "m2";

    private Car c1;
    private Car c2;

    private Runnable reaction1;
    private Runnable reaction2;

    @Before
    public void setup() {
        reaction1 = Mockito.mock(Runnable.class);
        reaction2 = Mockito.mock(Runnable.class);

        c1 = new Car(new Person(name1, m1, reaction1), mark1, SPEED.FIRST);
        c2 = new Car(new Person(name2, m2, reaction2), mark2, SPEED.FOURTH);
    }


    @Test
    public void testMockHighwayChangeSpeed() {
        var highway = Mockito.mock(Highway.class);
        highway.turnOnHighway(c1);
        highway.turnOnHighway(c2);
        highway.changeSpeed(new Message(SPEED.SECOND, c1));
        Mockito.verify(highway, Mockito.times(1)).changeSpeed(Mockito.any());
    }

    @Test
    public void testMockCarReceive() {
        var highway = new Highway("h1");
        var c3 = Mockito.mock(Car.class);
        highway.turnOnHighway(c1);
        highway.turnOnHighway(c2);
        highway.turnOnHighway(c3);
        Message msg = new Message(SPEED.SECOND, c3);
        highway.changeSpeed(msg);
        Mockito.verify(c3, Mockito.times(1)).receive(msg);
    }

    @Test
    public void testPersonReact() {
        var highway = new Highway("h1");
        highway.turnOnHighway(c1);
        highway.turnOnHighway(c2);
        var personMock = Mockito.mock(Person.class);
        highway.turnOnHighway(new Car(personMock, mark1, SPEED.SECOND));
        Message msg = new Message(SPEED.SECOND, c2);
        highway.changeSpeed(msg);
        Mockito.verify(personMock, Mockito.times(1)).react(Mockito.any());
    }

    @Test
    public void testPersonReactMood() {
        var highway = new Highway("h1");
        highway.turnOnHighway(c1);
        highway.turnOnHighway(c2);
        highway.changeSpeed(new Message(SPEED.SECOND, c2));
        Assert.assertEquals(c2.person.mood, MOOD.FORD_PREFECT_ON_REMARK);
        highway.changeSpeed(new Message(SPEED.FIRST, c2));
        Assert.assertEquals(c2.person.mood, MOOD.BAD);
    }
//    @Test
//    public void testReaction() {
//        var highway = new Highway("h1");
//        highway.turnOnHighway(c1);
//        highway.turnOnHighway(c2);
//        highway.changeSpeed(new Message(SPEED.SECOND, c2));
//        Assert.assertEquals(c2.person.mood, MOOD.FORD_PREFECT_ON_REMARK);
//        highway.changeSpeed(new Message(SPEED.FIRST, c2));
//        Assert.assertEquals(c2.person.mood, MOOD.BAD);
//    }
}
