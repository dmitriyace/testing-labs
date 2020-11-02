package ru.ifmo.se.lab1.domain;

import java.util.Objects;

public class Car {
    public final Person person;
    public final String mark;
    public SPEED speed;

    public Car(Person person, String mark, SPEED speed) {
        this.person = person;
        this.mark = mark;
        this.speed = speed;
    }

    public void receive(Message msg) {
        Boolean speedIncreased = msg.speed.speedLevel > this.speed.speedLevel;
        if (msg.car == this) {
            this.speed = msg.speed;
            this.person.react(speedIncreased);
            return;
        }

        this.person.react(false);

        System.out.printf("[CAR] There is no news for %s for now...", this.person.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }

        if (o==null || getClass()!=o.getClass()) {
            return false;
        }

        Car car = (Car) o;
        return person==car.person &&
                speed==car.speed &&
                mark.equals(car.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, mark, speed);
    }
}
