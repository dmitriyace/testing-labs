package ru.ifmo.se.lab1.domain;

public class Person {
    public final String name;
    public MOOD mood;
    private final Runnable reaction;

    public Person(String name, MOOD mood, Runnable reaction) {
        this.name = name;
        this.mood = mood;
        this.reaction = reaction;
    }

    public void react(Boolean speedIncreased) {
        if (speedIncreased) {
            if (this.mood == MOOD.GOOD) {
                return;
            }

            reaction.run();
            System.out.printf("Person %s don't feel himself %s no more. His mood is good now!", this.name, this.mood);
            this.mood = MOOD.GOOD;

            return;
        }

        if (this.mood == MOOD.GOOD) {
            reaction.run();
            System.out.printf("Person %s doesn't feel himself good no more. He feels himself now like Ford Prefect at the moment of that remark...", this.name);
            this.mood = MOOD.FORD_PREFECT_ON_REMARK;
            return;
        }

        if (this.mood == MOOD.FORD_PREFECT_ON_REMARK) {
            reaction.run();
            System.out.printf("Person %s doesn't feel himself like For Prefect no more. He feels himself now bad AFA he has to speed down accidentally...", this.name);
            this.mood = MOOD.BAD;
        }
    }
}
