package org.concurrent;

import lombok.SneakyThrows;

import java.time.Instant;
public class InterruptingClass {

    public static Runnable whileNotInterrupted () {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("Starting at %d", Instant.now().getEpochSecond()));
                while(!Thread.currentThread().isInterrupted()) {
                }
                System.out.println(String.format("Interrupted at %d", Instant.now().getEpochSecond()));
            }
        };
    }

    public static Runnable whileOnSleep (long sleepFor) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("Starting at %d", Instant.now().getEpochSecond()));
                    Thread.sleep(sleepFor);
                } catch (InterruptedException e) {
                    System.out.println(String.format("Interrupted at %d", Instant.now().getEpochSecond()));
                }
            }
        };
    }
}
