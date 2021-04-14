package org.concurrent.happenBefore;

public class Atomic {
    private long p1,p2, p3, p4, p5, p6;
    private long a;
    private long p7,p8, p9, p10, p11, p12;
    private long b;

    public synchronized void write() {
        a++;
    }

    public synchronized long read() {
        b = a;
        return b;
    }
}
