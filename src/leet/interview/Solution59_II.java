package leet.interview;

import java.util.*;

public class Solution59_II {
    public static void main(String[] args) {

    }
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
    private Queue<Integer> queue;
    //单调辅助队列
    private Deque<Integer> sort_queue;

    public MaxQueue2() {
        queue = new ArrayDeque<>();
        sort_queue = new ArrayDeque<>();
    }

    public int max_value() {
        if (!queue.isEmpty()) {
            return sort_queue.getFirst();
        }
        else return -1;
    }

    public void push_back(int value) {
        while (!sort_queue.isEmpty() && sort_queue.getLast() < value){
            sort_queue.removeLast();
        }
        sort_queue.addLast(value);
        queue.add(value);
    }

    public int pop_front() {
        if (!queue.isEmpty()){
            int res = queue.remove();
            if (res == sort_queue.getFirst()){
                sort_queue.removeFirst();
            }
            return res;
        }
        else return -1;
    }
}