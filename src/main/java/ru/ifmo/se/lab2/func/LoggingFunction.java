package ru.ifmo.se.lab2.func;

import java.io.IOException;
import java.io.Writer;

public class LoggingFunction implements MathFunction {
    private final MathFunction mathFunction;
    private final Writer writer;

    public LoggingFunction(MathFunction mathFunction, Writer writer) {
        this.mathFunction = mathFunction;
        this.writer = writer;
    }

    @Override
    public double calculate(double arg, double delta) {
        var result = mathFunction.calculate(arg, delta);
        writeResult(arg, result);
        return result;
    }

    private void writeResult(double arg, double result) {
        try {
            var line = String.format("%s,%s\n", arg, result);
            writer.write(line);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
