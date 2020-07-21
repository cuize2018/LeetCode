package leet.interview;

import java.util.Stack;

public class Solution31 {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};

        System.out.println(validateStackSequences(pushed, popped));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;

        while (i < pushed.length && j <popped.length) {
            while (i < pushed.length && (stack.isEmpty() || stack.peek() != popped[j])) {
                stack.push(pushed[i]);
                i++;
            }

            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return i == pushed.length && j == popped.length;
    }
}
