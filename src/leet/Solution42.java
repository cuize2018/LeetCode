package leet;

import java.util.Stack;

public class Solution42 {
    public static void main(String[] args){
        int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(h));
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
}
