package leet;

import java.util.Stack;

public class Solution42 {
    public static void main(String[] args){
        int[] h = {4,2,3};
        System.out.println(trap3(h));
    }

    public static int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int curr = 0;

        while (curr < height.length){
            while (!stack.isEmpty() && height[curr] > height[stack.peek()]){
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()){
                    break;
                }
                int min = Math.min(height[curr], height[stack.peek()]);
                sum += (min-h)*(curr-stack.peek()-1);
            }

            stack.push(curr);
            curr++;
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

    /**
     * 单调栈，入栈逐渐递减的索引，当遇到第一个大于栈顶高度的索引开始计算面积
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int res = 0;
        int i = 1;
        while (i < height.length){
            if (stack.isEmpty() || height[i] <= height[stack.peek()]){//入栈小于等于栈顶高度的索引
                stack.push(i);
                i++;
            }
            else {
                if (!stack.isEmpty()) {
                    int currHeight = height[stack.pop()];
                    while (!stack.isEmpty() && height[stack.peek()] == currHeight) {
                        stack.pop();
                    }
                    if (!stack.isEmpty()) {
                        //高度为左右两边较小的高度-当前高度
                        int h = Math.min(height[stack.peek()], height[i]) - currHeight;
                        int width = i - 1 - stack.peek();
                        res += h * width;
                    }
                }
            }
        }
        return res;
    }
}
