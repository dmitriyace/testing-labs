package ru.ifmo.se.lab2;

import org.apache.commons.cli.*;
import ru.ifmo.se.lab2.func.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private final static String MODULE_OPTION = "module";
    private final static String LOW_ARG_OPTION = "low";
    private final static String HIGH_ARG_OPTION = "high";
    private final static String STEP_OPTION = "step";
    private final static String DELTA_OPTION = "delta";

    private final static String SIN_MODULE = "sin";
    private final static String COS_MODULE = "cos";
    private final static String COT_MODULE = "cot";
    private final static String TAN_MODULE = "tan";
    private final static String LOG_MODULE = "log";
    private final static String FUNC_MODULE = "func";

    private final static String DEFAULT_DELTA = "1e-3";

    public static void main(String[] args) throws IOException {
        var parser = new DefaultParser();
        var formatter = new HelpFormatter();

        var options = getOptions();
        var writers = new ArrayList<Writer>();
        try {
            var cmd = parser.parse(options, args);

            var modules = cmd.getOptionValues(MODULE_OPTION);
            var func = getTargetFunction(modules, writers);

            var lowArg = ((Number) cmd.getParsedOptionValue(LOW_ARG_OPTION)).doubleValue();
            var highArg = ((Number) cmd.getParsedOptionValue(HIGH_ARG_OPTION)).doubleValue();
            var step = ((Number) cmd.getParsedOptionValue(STEP_OPTION)).doubleValue();
            var delta = Double.parseDouble(cmd.getOptionValue(DELTA_OPTION, DEFAULT_DELTA));

            for (var i = lowArg; i <= highArg; i += step) {
                func.calculate(i, delta);
            }

        } catch (ParseException exc) {
            formatter.printHelp("lab2", options);
            System.exit(1);
        } finally {
            for (var writer : writers) {
                writer.close();
            }
        }
    }

    private static Options getOptions() {
        var options = new Options();

        var moduleOption = new Option("m", MODULE_OPTION, true, "Comma separated names of " +
                "modules, which results will be logged");
        moduleOption.setValueSeparator(',');
        moduleOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(moduleOption);

        var lowArgOption = new Option("l", LOW_ARG_OPTION, true, "Low value of argument");
        lowArgOption.setRequired(true);
        lowArgOption.setType(Number.class);
        options.addOption(lowArgOption);

        var highArgOption = new Option("h", HIGH_ARG_OPTION, true, "High value of argument");
        highArgOption.setRequired(true);
        highArgOption.setType(Number.class);
        options.addOption(highArgOption);

        var stepOption = new Option("s", STEP_OPTION, true, "Step value");
        highArgOption.setRequired(true);
        stepOption.setType(Number.class);
        options.addOption(stepOption);

        var deltaOption = new Option("d", DELTA_OPTION, true, "Delta value; Default: "
                + DEFAULT_DELTA);
        deltaOption.setType(Number.class);
        options.addOption(deltaOption);

        return options;
    }

    private static MathFunction getTargetFunction(String[] logModules, List<Writer> writers) throws IOException {
        var modules = new HashSet<>(Set.of(logModules));

        MathFunction sin = new SinFunction();
        if (modules.remove(SIN_MODULE)) {
            var file = new FileWriter(SIN_MODULE);
            writers.add(file);
            sin = new LoggingFunction(sin, file);
        }

        MathFunction log = new LogFunction();
        if (modules.remove(LOG_MODULE)) {
            var file = new FileWriter(LOG_MODULE);
            writers.add(file);
            log = new LoggingFunction(log, file);
        }

        var builder = new FunctionBuilder(sin, log);
        var tan = builder.tan();
        var cos = builder.cos();
        var cot = builder.cot();

        var logFunc = false;
        for (var module : modules) {
            switch (module) {
                case COS_MODULE: {
                    var file = new FileWriter(COS_MODULE);
                    writers.add(file);
                    cos = builder.cos(file);
                    break;
                }
                case TAN_MODULE: {
                    var file = new FileWriter(TAN_MODULE);
                    writers.add(file);
                    tan = builder.tan(file);
                    break;
                }
                case COT_MODULE: {
                    var file = new FileWriter(COT_MODULE);
                    writers.add(file);
                    cot = builder.cot(file);
                    break;
                }
                case FUNC_MODULE: {
                    logFunc = true;
                    break;
                }
                default: {
                    var msg = String.format("unknown module: %s", module);
                    throw new RuntimeException(msg);
                }
            }
        }

        MathFunction func = new TargetFunction(new Calculator(cos, tan, cot, log));
        if (logFunc) {
            var file = new FileWriter(FUNC_MODULE);
            writers.add(file);
            func = new LoggingFunction(func, file);
        }
        return func;
    }
}
