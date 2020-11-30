package ru.ifmo.se.lab2.func;

public class Calculator {
    private final MathFunction cosFunc;
    private final MathFunction tanFunc;
    private final MathFunction cotFunc;
    private final MathFunction logFunc;

    public Calculator(MathFunction cosFunc, MathFunction tanFunc, MathFunction cotFunc,
                      MathFunction logFunc) {
        this.cosFunc = cosFunc;
        this.tanFunc = tanFunc;
        this.cotFunc = cotFunc;
        this.logFunc = logFunc;
    }

    public double cos(double arg, double delta) {
        return cosFunc.calculate(arg, delta);
    }

    public double tan(double arg, double delta) {
        return tanFunc.calculate(arg, delta);
    }

    public double cot(double arg, double delta) {
        return cotFunc.calculate(arg, delta);
    }

    public double log(double arg, double delta) {
        return logFunc.calculate(arg, delta);
    }

    public double log(double base, double arg, double delta) {
        return logFunc.calculate(arg, delta) / logFunc.calculate(base, delta);
    }
}
