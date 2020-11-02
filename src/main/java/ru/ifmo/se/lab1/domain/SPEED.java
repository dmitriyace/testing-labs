package ru.ifmo.se.lab1.domain;

public enum SPEED {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5);

    public final Integer speedLevel;

    private SPEED(Integer speedLevel) {
        this.speedLevel = speedLevel;
    }
}
