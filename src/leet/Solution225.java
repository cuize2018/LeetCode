package leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution225 {
    public static void main(String[] args) {

    }

    static class MyStack {
        Queue<Integer> queue = new LinkedList<>();
        /** Initialize your data structure here. */
        public MyStack() {

        }
        /** Push element x onto stack. */
        public void push(int x) {
            queue.add(x);
            int size = queue.size();
            while (size > 1){
                queue.add(queue.remove());
                size--;
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (queue.isEmpty())return -1;
            return queue.remove();
        }

        /** Get the top element. */
        public int top() {
            if (queue.isEmpty())return -1;
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}

class MyStack2 {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int top = -1;
    /** Initialize your data structure here. */
    public MyStack2() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue1.isEmpty())return -1;

        while (queue1.size() > 1){
            top = queue1.remove();
            queue2.add(top);
        }
        int v = queue1.remove();
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return v;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}