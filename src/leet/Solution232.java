package leet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution232 {
    public static void main(String[] args) {

    }
}

class MyQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.addLast(stack1.removeLast());
            }
        }
        return stack2.removeLast();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.addLast(stack1.removeLast());
            }
        }
        return stack2.getLast();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
