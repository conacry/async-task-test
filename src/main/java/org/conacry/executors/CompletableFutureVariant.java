package org.conacry.executors;

import org.conacry.common.Constants;
import org.conacry.task.ArithmeticTask;
import org.conacry.task.IoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureVariant implements TestExecutor {

    public void runArithmetic() {
        List<CompletableFuture<String>> futures = new ArrayList<>(Constants.ARITHMETIC_LOOP_COUNT);

        ExecutorService pool = Executors.newFixedThreadPool(Constants.CORE_COUNT);

        for (int i = 0; i < Constants.ARITHMETIC_LOOP_COUNT; i++) {
            futures.add(CompletableFuture.supplyAsync(new ArithmeticTask(), pool));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[Constants.ARITHMETIC_LOOP_COUNT]))
                .thenAccept(f -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .join();

        pool.shutdown();
    }

    @Override
    public void runIO() {
        List<CompletableFuture<String>> futures = new ArrayList<>(Constants.IO_LOOP_COUNT);

        ExecutorService pool = Executors.newFixedThreadPool(Constants.CORE_COUNT);

        for (int i = 0; i < Constants.IO_LOOP_COUNT; i++) {
            futures.add(CompletableFuture.supplyAsync(new IoTask(), pool));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[Constants.IO_LOOP_COUNT]))
                .thenAccept(f -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .join();

        pool.shutdown();
    }
}
