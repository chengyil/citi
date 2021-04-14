package org.concurrent.happenBefore;

public class NoVisibility {
    private long p1,p2, p3, p4, p5, p6;
    private long a;
    private long p7,p8, p9, p10, p11, p12;
    private long b;

    public void write() {
        a++;
    }

    public long read() {
        b = a;
        return b;
    }
}
