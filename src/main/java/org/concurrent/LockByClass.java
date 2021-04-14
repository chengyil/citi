package org.concurrent;

import java.time.Instant;

public class LockByClass {
    public final static Object lock = new Object();

    public void methodA() throws InterruptedException {
        synchronized (lock) {
            System.out.println(String.format("Current Thread %s in methodA at %d",
                    Thread.currentThread().getName(), Instant.now().getEpochSecond()));
            Thread.sleep(1000);
        }
    }

    public void methodB() throws InterruptedException {
        synchronized (lock) {
            System.out.println(String.format("Current Thread %s in methodB at %d",
                    Thread.currentThread().getName(), Instant.now().getEpochSecond()));
            Thread.sleep(1000);
        }
    }
}
