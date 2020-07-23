package leet;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Solution1188 {

}


class BoundedBlockingQueue {
    private final Deque<Integer> queue;
    private final int max;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int size;

    public BoundedBlockingQueue(int capacity) {
        queue = new LinkedList<>();
        max = capacity;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        size = 0;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size == max) {
                notFull.await();
            }
            queue.addFirst(element);
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            int v = queue.removeLast();
            size--;
            notFull.signal();
            return v;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}


class BoundedBlockingQueue2 {
    private final Deque<Integer> queue;
    private final int max;
    private int size;

    public BoundedBlockingQueue2(int capacity) {
        queue = new LinkedList<>();
        max = capacity;
        size = 0;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (this) {
            while (size == max) {
                this.wait();
            }
            queue.addFirst(element);
            size++;
            this.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                this.wait();
            }
            int v = queue.removeLast();
            size--;
            this.notifyAll();
            return v;
        }

    }

    public int size() {
        synchronized (this) {
            return size;
        }
    }
}