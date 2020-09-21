package org.conacry.executors;

import org.conacry.common.TestConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class ParallelStreamVariantTest {

    @Test
    void run_arithmetic_benchmark() {
        assertThatCode(() -> {
                    for (int i = 0; i < TestConstants.ARITHMETIC_TEST_COUNT; i++) {
                        TestExecutor testExecutor = new ParallelStreamVariant();
                        long start = System.nanoTime();
                        testExecutor.runArithmetic();
                        long stop = System.nanoTime();
                        System.out.println(stop - start);
                    }
                }
        ).doesNotThrowAnyException();
    }

    @Test
    void run_io_benchmark() {
        assertThatCode(() -> {
                    for (int i = 0; i < TestConstants.IO_TEST_COUNT; i++) {
                        TestExecutor testExecutor = new ParallelStreamVariant();
                        long start = System.nanoTime();
                        testExecutor.runIO();
                        long stop = System.nanoTime();
                        System.out.println(stop - start);
                    }
                }
        ).doesNotThrowAnyException();
    }
}