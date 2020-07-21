package leet.interview;

import java.util.Stack;

public class Solution31 {
    public static void main(String[] args) {

    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;

        while (i < pushed.length && j <popped.length) {
            while (stack.isEmpty() || stack.peek() != popped[j]) {
                stack.push(pushed[i]);
                i++;
            }

            while (stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        if (i == pushed.length && j == popped.length)return true;
        return false;
    }
}
