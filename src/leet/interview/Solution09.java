package leet.interview;

import leet.ListNode;

import java.util.*;

public class Solution09 {
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());

    }


}

class CQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        stack1.addLast(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()){
            if (stack1.isEmpty())return -1;

            while (stack1.size() > 1){
                int v = stack1.removeLast();
                stack2.addLast(v);
            }
            return stack1.removeLast();
        }
        else {
            return stack2.removeLast();
        }
    }
}