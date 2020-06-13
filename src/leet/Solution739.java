package leet;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class Solution739 {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures2(temperatures)));
    }

    /**
     * 单调栈，只入栈小于等于栈顶元素的元素；
     * 当遇到比栈顶元素大的元素时，开始出栈比当前元素小的元素，并记录下标差，最后入栈当前元素
     *
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {
        if (T.length == 0) return new int[0];
        int[] out = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int idx = stack.pop();
                out[idx] = i - idx;
            }
            stack.push(i);
        }
        return out;
    }

    public static int[] dailyTemperatures2(int[] T) {
        int n = T.length;
        if (n == 0) {
            return new int[0];
        }
        int[] res = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int k = stack.pop();
                    res[k] = i - k;
                }
            }
            stack.push(i);
        }
        return res;
    }
}
