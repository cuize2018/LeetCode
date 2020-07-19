package leet;

import java.util.concurrent.Semaphore;

public class Solution1115 {
    public static void main(String[] args) {

    }


}

class FooBar {
    private int n;
    private Semaphore semaphore;
    private Semaphore semaphore2;
    public FooBar(int n) {
        this.n = n;
        semaphore = new Semaphore(0);
        semaphore2 = new Semaphore(1);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore2.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphore.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore2.release();
        }
    }
}

//超时
class FooBar2 {
    private int n;
    private volatile int flag = 0;
    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (flag != 0){}
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            flag = 1;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (flag == 0){}
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            flag = 0;

        }
    }
}