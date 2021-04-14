package org.concurrent.wait;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Buffer<T> {
    private T[] buffer;
    private int count;
    private int index;
    public Buffer (Class<T> clazz, int count) {
        this.buffer = (T[])Array.newInstance(clazz, count);
        this.count = count;
        this.index = -1;
    }

    public synchronized boolean isFull() {
        return index >= count -1;
    }

    public synchronized boolean isEmpty () {
        return index == -1;
    }

    public synchronized void push(T item) throws InterruptedException {
        if(isFull()) {
            wait();
        }
        index += 1;
        buffer[index] = item;
        notifyAll();
    }

    public synchronized T pop() throws InterruptedException {
        if(isEmpty()) {
            wait();
        }
        T item = buffer[index];
        index -= 1;
        notifyAll();
        return item;
    }

    @Override
    public String toString() {
        List<T> items = new ArrayList<>();
        for(int i = 0; i <= index; i++) {
            items.add(buffer[i]);
        }
        return "Index " + index + " : " +items.stream().map(i -> i.toString()).collect(Collectors.joining(","));
    }
}
