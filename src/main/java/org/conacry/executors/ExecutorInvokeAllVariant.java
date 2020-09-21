package org.conacry.executors;

import org.conacry.common.Constants;
import org.conacry.task.ArithmeticTask;
import org.conacry.task.IoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorInvokeAllVariant implements TestExecutor {

    public void runArithmetic() {
        List<Callable<String>> callables = new ArrayList<>(Constants.ARITHMETIC_LOOP_COUNT);

        for (int i = 0; i < Constants.ARITHMETIC_LOOP_COUNT; i++) {
            callables.add(() -> new ArithmeticTask().call());
        }

        execute(callables);
    }

    @Override
    public void runIO() {
        List<Callable<String>> callables = new ArrayList<>(Constants.IO_LOOP_COUNT);

        for (int i = 0; i < Constants.IO_LOOP_COUNT; i++) {
            callables.add(() -> new IoTask().call());
        }

        execute(callables);
    }

    private void execute(List<Callable<String>> tasks) {
        ExecutorService pool = Executors.newFixedThreadPool(Constants.CORE_COUNT);

        List<Future<String>> futures;

        try {
            futures = pool.invokeAll(tasks);
            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        pool.shutdown();
    }
}
