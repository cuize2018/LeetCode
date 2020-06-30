package leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Solution225 {
    public static void main(String[] args) {
    }
}


class MyStack {

    private Deque<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.addLast(x);
        int sz = queue.size();
        while (sz > 1) {
            queue.addLast(queue.removeFirst());
            sz--;
        }
     }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        return queue.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyStack2 {
    Queue<Integer> queue1;
    /** Initialize your data structure here. */
    public MyStack2() {
        queue1 = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
        int size = queue1.size();
        while (size > 1){
            queue1.add(queue1.remove());
            size--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.remove();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}