package leet;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Solution1226 {
    public static void main(String[] args) {

    }


}

class DiningPhilosophers {
    volatile Semaphore[] semaphores= new Semaphore[]{
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };
    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {


        int leftForkNo = philosopher;
        int rightForkNo = (philosopher+1)%5;

        if (philosopher % 2 == 0){
            semaphores[leftForkNo].acquire();
            semaphores[rightForkNo].acquire();
        }else {
          semaphores[rightForkNo].acquire();
          semaphores[leftForkNo].acquire();
        }

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        semaphores[leftForkNo].release();
        semaphores[rightForkNo].release();
    }
}
