package leet;

import java.util.Stack;

public class Solution946 {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
        System.out.println(validateStackSequences(pushed, popped));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0)return true;

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;

        while (i < pushed.length){
            if (stack.isEmpty() || stack.peek() != popped[j]){
                stack.push(pushed[i]);
                i++;
            }
            else {
                stack.pop();
                j++;
            }
        }
        while (!stack.isEmpty()){
            if (stack.peek() != popped[j]){
                return false;
            }
            stack.pop();
            j++;
        }
        return true;
    }
}
