package org.concurrent;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.*;

class LockByInstanceTest {
    @Test
    @SneakyThrows
    public void shouldRunConcurrently(){
        Thread threadA = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                LockByInstance instance = new LockByInstance();
                instance.methodA();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                LockByInstance instance = new LockByInstance();
                instance.methodB();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

    @SneakyThrows
    @Test
    public void shouldWaitForLock() {
        LockByInstance instance = new LockByInstance();
        Thread threadA = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                instance.methodA();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                instance.methodB();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

    // Future
    // Executor
    // Timeout
    // Concurrent Map
    // read write test
    // List to Map in lamda
    // TreeSet
    // Setting Heap Memory
    // Callable
    // Tree Set
    // volatile

}