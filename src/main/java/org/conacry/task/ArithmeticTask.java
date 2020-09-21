package org.conacry.task;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ArithmeticTask implements Callable<String>, Supplier<String> {

    @Override
    public String call() {
        return doTask();
    }

    @Override
    public String get() {
        return doTask();
    }

    private String doTask() {
        double result = Math.random() + Math.random();
        return String.valueOf(result);
    }

}
