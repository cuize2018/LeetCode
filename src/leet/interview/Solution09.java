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

    static class CQueue {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        public CQueue() {

        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()){
               while (!stack1.isEmpty()){
                   stack2.push(stack1.pop());
               }
            }
            if (stack2.isEmpty())return -1;
            return stack2.pop();
        }
    }
}