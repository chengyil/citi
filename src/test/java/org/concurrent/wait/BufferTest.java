package org.concurrent.wait;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {
    @Test
    void shouldBeAbleToConsumeAllItems () throws InterruptedException {
        Buffer<Integer> buffer = new Buffer<Integer>(Integer.class,5);
        Runnable consumerTask = new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 33; i++) {
                        System.out.println(String.format("Current Thread %s, %d", Thread.currentThread().getName(), buffer.pop()));
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        };
        Runnable producerTask = () -> {
            try {
                for(int i = 0; i < 100; ++i) {
                    buffer.push(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        List<Thread> consumerPool = List.of(new Thread(consumerTask), new Thread(consumerTask),  new Thread(consumerTask));
        List<Thread> producerPool = List.of(new Thread(producerTask));
        consumerPool.forEach(Thread::start);
        producerPool.forEach(Thread::start);
        producerPool.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerPool.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Buffer currently" + buffer);
    }

}