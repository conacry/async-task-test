package org.conacry;

import org.conacry.executors.TestExecutor;
import org.conacry.executors.ExecutorInvokeAllVariant;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        TestExecutor testExecutor = new ExecutorInvokeAllVariant();

        long start = System.currentTimeMillis();

        testExecutor.runArithmetic();

        long stop = System.currentTimeMillis();
        System.out.println(stop - start);

    }
}
