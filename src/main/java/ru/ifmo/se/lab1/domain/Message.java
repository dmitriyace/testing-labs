package ru.ifmo.se.lab1.domain;

public class Message {
    public final SPEED speed;
    public final Car car;

    public Message(SPEED speed, Car car) {
        this.speed = speed;
        this.car = car;
    }
}
