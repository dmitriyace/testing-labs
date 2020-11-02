package ru.ifmo.se.lab1.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Highway {
    private final String name;
    private final Set<Car> traffic;

    public Highway(String name) {
        this.name = name;
        this.traffic = new HashSet<>();
    }

    public void turnOnHighway(Car car) {
        if (traffic.contains(car)) {
            var msg = String.format("Car with this person is already on the highway: [person]=%s, [car mark]=%s",
                    car.person, car.mark);
            throw new IllegalArgumentException(msg);
        }
        traffic.add(car);
        System.out.println(traffic);
    }

    public void turnOffHighway(Car car) {
        if (!traffic.remove(car)) {
            var msg = String.format("This car isn't on the highway: [person]=%s, [car mark]=%s", car.person, car.mark);
            throw new IllegalArgumentException(msg);
        }
    }

    public void changeSpeed(Message msg) {
        for (Car car : traffic) {
            car.receive(msg);
        }
    }
}