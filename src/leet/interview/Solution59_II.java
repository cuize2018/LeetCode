package leet.interview;

import java.util.*;

public class Solution59_II {
}

class MaxQueue {
    private Queue<Integer> queue;
    private PriorityQueue<Integer> heap;

    public MaxQueue() {
        queue = new ArrayDeque<>();
        Comparator<Integer> comparator = (o1, o2) -> {return Integer.compare(o2,o1);};
        heap = new PriorityQueue<Integer>(comparator);
    }

    public int max_value() {
        if (!heap.isEmpty()) {
            return heap.peek();
        }
        else return -1;
    }

    public void push_back(int value) {
        queue.add(value);
        heap.add(value);
    }

    public int pop_front() {
        if (!queue.isEmpty()){
            int front = queue.remove();
            heap.remove(front);
            return front;
        }
        else return -1;
    }
}

class MaxQueue2 {
    Queue<Integer> queue;
    Deque<Integer> sortQueue;

    public MaxQueue2() {
        queue = new ArrayDeque<>();
        sortQueue = new ArrayDeque<>();
    }

    public int max_value() {
        if (queue.isEmpty())return -1;
        return sortQueue.getFirst();
    }

    public void push_back(int value) {
        while (!sortQueue.isEmpty() && sortQueue.peekLast() < value){
            sortQueue.removeLast();
        }
        queue.add(value);
        sortQueue.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty())return -1;
        int val = queue.remove();
        if (val == sortQueue.getFirst()){
            sortQueue.removeFirst();
        }
        return val;
    }
}