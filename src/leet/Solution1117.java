package leet;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Solution1117 {
    public static void main(String[] args) {

    }


}

class H2O {
    private final Semaphore h;
    private final Semaphore o;
    private final CyclicBarrier barrier;
    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(1);
        barrier = new CyclicBarrier(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        releaseHydrogen.run();
        h.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        o.release();
    }
}