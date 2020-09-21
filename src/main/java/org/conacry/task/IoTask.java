package org.conacry.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class IoTask implements Callable<String>, Supplier<String> {

    private static final int LINE_COUNT = 10;

    @Override
    public String call() {
        return doTaskWrapper();
    }

    @Override
    public String get() {
        return doTaskWrapper();
    }

    private String doTaskWrapper() {
        String result;

        try {
            result = doTask();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }

    private String doTask() throws IOException {
        File file = File.createTempFile("Test_file", ".txt", new File("files/tmp"));
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {

            for (int i = 0; i < LINE_COUNT; i++) {
                writer.write(String.valueOf(Math.random()));
                writer.write("\n");
            }

            writer.flush();
        }

        return String.valueOf(file.length());
    }
}
