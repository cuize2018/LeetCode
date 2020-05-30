package leet;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class Solution84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea5(heights));
    }

    /**
     * 暴力法
     */
    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i]);
            int min_height = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                min_height = Math.min(min_height, heights[j]);
                int area = (j - i + 1) * min_height;
                max = Math.max(area, max);
            }
        }
        return max;
    }

    /**
     * 分治法
     * 通过观察，可以发现，最大面积矩形存在于以下几种情况：
     * 1. 确定了最矮柱子以后，矩形的宽尽可能往两边延伸。
     * 2. 在最矮柱子左边的最大面积矩形（子问题）。
     * 3. 在最矮柱子右边的最大面积矩形（子问题）。
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) return 0;
        //找出最低高度柱子
        int min_height = heights[0];
        int min_height_idx = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] < min_height) {
                min_height = heights[i];
                min_height_idx = i;
            }
        }

        int max = min_height * (heights.length);
        //左侧子问题
        int[] left = Arrays.copyOfRange(heights, 0, min_height_idx);
        //右侧子问题
        int[] right = Arrays.copyOfRange(heights, min_height_idx + 1, heights.length);

        int left_area = largestRectangleArea2(left);
        int right_area = largestRectangleArea2(right);
        max = Math.max(max, Math.max(left_area, right_area));
        return max;
    }

    /**
     * 单调栈
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea3(int[] heights) {
        //stack单调栈中存放柱子的下标
        Stack<Integer> stack = new Stack<>();
        //在原始输入数组的两端各添加一个高度为0的柱
        int[] new_heights = new int[heights.length + 2];
        System.arraycopy(heights, 0, new_heights, 1, heights.length);

        int max = 0;
        stack.push(0);//入栈初始下标0
        for (int i = 1; i < new_heights.length; ) {
            if (new_heights[i] >= new_heights[stack.peek()]) {//当第i个柱的高度大于等于之前最大柱子的高度时入栈下标i
                stack.push(i);
                i++;
            } else {//当第i个柱的高度小于之前最大柱子的高度时
                int height = new_heights[stack.pop()];//取当前柱子高度
                //从右往左出栈，直到第一个小于height的位置
                while (new_heights[stack.peek()] == height) {
                    stack.pop();
                }
                //计算以height为高度的矩形面积，宽度为(i-stack.peek()-1)
                int area = (i - stack.peek() - 1) * (height);
                max = Math.max(max, area);
            }
        }
        return max;

    }

    public static int largestRectangleArea4(int[] heights) {
        int[] h = new int[heights.length + 2];
        System.arraycopy(heights, 0, h, 1, heights.length);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int i = 1;
        int max = 0;
        while (i < h.length) {
            if (h[i] >= h[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = h[stack.pop()];
                while (!stack.isEmpty() && h[stack.peek()] == height) {
                    stack.pop();
                }
                max = Math.max(max, height * (i - stack.peek() - 1));
            }
        }
        return max;
    }

    public static int largestRectangleArea5(int[] heights){
        Deque<Integer> stack = new ArrayDeque<>();
        int[] h = new int[heights.length+2];
        System.arraycopy(heights, 0, h, 1, heights.length);
        stack.push(0);
        int max = 0;
        int i = 1;
        while (i < h.length){
            if (!stack.isEmpty() && h[i] < h[stack.peek()]){
                int h_t = h[stack.pop()];
                while (!stack.isEmpty() && h[stack.peek()] == h_t){
                    stack.pop();
                }
                max = Math.max(max, (i-stack.peek()-1)*h_t);
            }
            else {
                stack.push(i);
                i++;
            }
        }
        return max;
    }
}
