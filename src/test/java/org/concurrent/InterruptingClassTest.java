package org.concurrent;

import com.sun.tools.jdi.InternalEventHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterruptingClassTest {
    @SneakyThrows
    @Test
    void shouldRespectInterrupt() {
        Thread thread = new Thread(InterruptingClass.whileNotInterrupted());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @SneakyThrows
    @Test
    void shouldThrowInterruptedWhenSleep() {
        Thread thread = new Thread(InterruptingClass.whileOnSleep(5000));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}