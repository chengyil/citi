package org.concurrent;

import java.time.Instant;

public class LockByInstance {
    public void methodA() throws InterruptedException {
        synchronized (this) {
            System.out.println(String.format("Current Thread %s in methodA at %d",
                    Thread.currentThread().getName(), Instant.now().getEpochSecond()));
            Thread.sleep(1000);
        }
    }

    public synchronized void methodB() throws InterruptedException {
        System.out.println(String.format("Current Thread %s in methodB at %d",
                Thread.currentThread().getName(), Instant.now().getEpochSecond()));
        Thread.sleep(1000);
    }
}