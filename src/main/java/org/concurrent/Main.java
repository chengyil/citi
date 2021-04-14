package org.concurrent;

import org.concurrent.happenBefore.Atomic;
import org.concurrent.happenBefore.NoVisibility;
import org.concurrent.happenBefore.Volatile;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Volatile visbility = new Volatile();
        NoVisibility noVisibility = new NoVisibility();
        Atomic atomic = new Atomic();
        long begin, end;
        long MAX = 500_000_000;
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

        Thread writerTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    visbility.write();
                }
            }
        });

        Thread readerTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < MAX; i++) {
                    visbility.read();
                }
                System.out.println(visbility.read());
            }
        });
        begin = System.currentTimeMillis();
        writerTwo.start();
        readerTwo.start();
        writerTwo.join();
        readerTwo.join();
        end = System.currentTimeMillis();
        System.out.printf("visibility %d \n", end - begin);

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
