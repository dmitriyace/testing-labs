package ru.ifmo.se.lab2.func;

import org.junit.Test;
import org.mockito.Mockito;
import ru.ifmo.se.lab2.func.LoggingFunction;
import ru.ifmo.se.lab2.func.MathFunction;

import java.io.IOException;
import java.io.Writer;

public class LoggingFunctionTest {
    @Test
    public void testLogging() throws IOException {
        var arg = 42.0;
        var delta = 0.001;

        var func = Mockito.mock(MathFunction.class);
        Mockito.when(func.calculate(arg, delta)).thenReturn(42.0);

        var writer = Mockito.mock(Writer.class);
        var logFunc = new LoggingFunction(func, writer);

        logFunc.calculate(arg, delta);
        Mockito.verify(writer, Mockito.times(1)).write("42.0,42.0\n");
    }
}
