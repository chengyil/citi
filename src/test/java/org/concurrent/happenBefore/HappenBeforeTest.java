package org.concurrent.happenBefore;

import org.junit.jupiter.api.Test;

public class HappenBeforeTest {
    long MAX = 500_000_000;

    @Test
    void benchmark() throws InterruptedException {
        long begin, end;

        Volatile visibility = new Volatile();
        Thread writeVolatile = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    visibility.write();
                }
            }
        });

        Thread readerVolatile = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    visibility.read();
                }
                System.out.println(visibility.read());
            }
        });

        begin = System.currentTimeMillis();
        writeVolatile.start();
        readerVolatile.start();
        writeVolatile.join();
        readerVolatile.join();
        end = System.currentTimeMillis();
        System.out.printf("volatile %d \n", end - begin);


        NoVisibility noVisibility = new NoVisibility();
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    noVisibility.write();
                }
            }
        });
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    noVisibility.read();
                }
                System.out.println(noVisibility.read());
            }
        });

        begin = System.currentTimeMillis();
        writer.start();
        reader.start();
        writer.join();
        reader.join();
        end = System.currentTimeMillis();
        System.out.printf("No visibility %d \n", end - begin);

        Atomic atomic = new Atomic();
        Thread writerAtomic = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    atomic.write();
                }
            }
        });

        Thread readerAtomic = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    atomic.read();
                }
                System.out.println(atomic.read());
            }
        });
        begin = System.currentTimeMillis();
        writerAtomic.start();
        readerAtomic.start();
        writerAtomic.join();
        readerAtomic.join();
        end = System.currentTimeMillis();
        System.out.printf("atomic %d \n", end - begin);
    }
}
