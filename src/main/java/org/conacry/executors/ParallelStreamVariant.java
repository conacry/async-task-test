package org.conacry.executors;

import org.conacry.common.Constants;
import org.conacry.task.ArithmeticTask;
import org.conacry.task.IoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ParallelStreamVariant implements TestExecutor {

    @Override
    public void runArithmetic() {
        List<Supplier<String>> tasks = new ArrayList<>(Constants.ARITHMETIC_LOOP_COUNT);

        for (int i = 0; i < Constants.ARITHMETIC_LOOP_COUNT; i++) {
            tasks.add(new ArithmeticTask());
        }

        tasks.parallelStream().map(Supplier::get).collect(Collectors.toList());
    }

    @Override
    public void runIO() {
        List<Supplier<String>> tasks = new ArrayList<>(Constants.IO_LOOP_COUNT);

        for (int i = 0; i < Constants.IO_LOOP_COUNT; i++) {
            tasks.add(new IoTask());
        }

        tasks.parallelStream().map(Supplier::get).collect(Collectors.toList());
    }
}
