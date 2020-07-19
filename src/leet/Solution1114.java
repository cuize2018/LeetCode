package leet;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution1114 {
    public static void main(String[] args) {

    }


}

class Foo {
    CountDownLatch countDownLatch2;
    CountDownLatch countDownLatch3;

    public Foo() {
        countDownLatch2 = new CountDownLatch(1);
        countDownLatch3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatch2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatch2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatch3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatch3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo2 {
    BlockingQueue blockingQueue2 = new LinkedBlockingQueue();
    BlockingQueue blockingQueue3 = new LinkedBlockingQueue();
    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        blockingQueue2.offer(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        blockingQueue2.take();
        printSecond.run();
        blockingQueue3.add(1);

    }

    public void third(Runnable printThird) throws InterruptedException {
        blockingQueue3.take();
        printThird.run();
    }
}

class Foo3 {
    private volatile boolean firstFinished;
    private volatile boolean secondFinished;

    public Foo3() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstFinished = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!firstFinished){ }
        printSecond.run();
        secondFinished= true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!secondFinished){}
        printThird.run();
    }
}