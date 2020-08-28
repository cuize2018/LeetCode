package pratice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer {
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final Integer[] items = new Integer[100];
    private int tail, head, count;

    // 阻塞直到notFull
    public void put(Integer x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();
            }
            items[tail] = x;
            if (++tail == items.length)
                tail = 0;
            count++;
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }
    // 阻塞直到notEmpty
    public Integer take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            Integer val = items[head];
            items[head] = null;
            if (++head == items.length){
                head  = 0;
            }
            count--;
            notFull.await();
            return val;
        }
        finally {
            lock.unlock();
        }
    }
}
