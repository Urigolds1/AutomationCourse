package main.infrastructure;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Logger {
    public static void write(String message) {
        System.out.println(new SimpleDateFormat("dd.MM.yyyy 'at' hh:mm:ss")
                .format(new Date()) + " | " + message);
    }

    public static void reportStep(Exception exception) {
        String failedComponent = String.format("%s.%s",
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getClassName());

        write(String.format("%s | Failed because of %s", failedComponent, exception.getMessage()));
    }
}
