package leet;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class Solution1116 {
    public static void main(String[] args) {

    }
}

class ZeroEvenOdd {
    private int n;
    private Semaphore zero;
    private Semaphore even;
    private Semaphore odd;

    public ZeroEvenOdd(int n) {
        this.n = n;

        zero = new Semaphore(1);
        even = new Semaphore(0);
        odd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2; i++) {
            even.acquire();
            printNumber.accept(2 * i + 2);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int end = n / 2;
        if (n % 2 != 0) {
            end++;
        }
        for (int i = 0; i < end; i++) {
            odd.acquire();
            printNumber.accept(2 * i + 1);
            zero.release();
        }
    }
}