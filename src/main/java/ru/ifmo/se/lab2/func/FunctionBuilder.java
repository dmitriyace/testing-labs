package ru.ifmo.se.lab2.func;

import java.io.Writer;

public class FunctionBuilder {

    private final MathFunction sin;
    private final MathFunction log;

    public FunctionBuilder() {
        this(new SinFunction(), new LogFunction());
    }

    public FunctionBuilder(MathFunction sin, MathFunction log) {
        this.sin = sin;
        this.log = log;
    }

    public MathFunction sin() {
        return sin;
    }

    public MathFunction log() {
        return log;
    }

    public MathFunction cos() {
        return (arg, delta) -> sin().calculate(Math.PI / 2 - arg, delta);
    }

    public MathFunction cos(Writer writer) {
        return new LoggingFunction(cos(), writer);
    }

    public MathFunction tan() {
        return (arg, delta) -> sin().calculate(arg, delta) / cos().calculate(arg, delta);
    }

    public MathFunction tan(Writer writer) {
        return new LoggingFunction(tan(), writer);
    }

    public MathFunction cot() {
        return (arg, delta) -> 1 / tan().calculate(arg, delta);
    }

    public MathFunction cot(Writer writer) {
        return new LoggingFunction(cot(), writer);
    }
}
