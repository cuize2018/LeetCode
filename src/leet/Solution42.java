package leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution42 {
    public static void main(String[] args){
        int[] h = {4,2,3};
        System.out.println(trap(h));
    }

    /**
     * 单调栈，入栈逐渐递减的索引，当遇到第一个大于栈顶高度的索引开始计算面积
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height.length == 0)return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        int i = 0;
        while (i < height.length){
            if (stack.isEmpty() || height[i] <= height[stack.peek()]){
                stack.push(i);
                i++;
            }
            else {
                int downHeight = height[stack.peek()];
                while (!stack.isEmpty() && height[stack.peek()] == downHeight){
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int h = Math.min(height[stack.peek()], height[i]) - downHeight;
                    int l = i - stack.peek() - 1;
                    sum += l * h;
                }
            }
        }
        return sum;
    }

    /**
     * 动态规划
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1;i<height.length;i++){
            max_left[i] = Math.max(max_left[i-1], height[i-1]);
        }
        for (int i = height.length-2;i>=0;i--){
            max_right[i] = Math.max(max_right[i+1], height[i+1]);
        }

        for (int i = 1;i<height.length;i++){
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]){
                sum += min-height[i];
            }
        }
        return sum;
    }


}
