package org.concurrent;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockByClassTest {
    @SneakyThrows
    @Test
    void shouldAcquireClassLevelLock () {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                LockByClass instance = new LockByClass();
                try {
                    instance.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                LockByClass instance = new LockByClass();
                try {
                    instance.methodB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        one.start();
        two.start();
        one.join();
        two.join();
    }

}