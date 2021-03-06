package leet;

import java.util.Stack;

public class Solution85 {
    public static void main(String[] args) {
        char[][] mat =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}
                };

        System.out.println(maximalRectangle2(mat));
    }

    public static int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int[] heights = new int[matrix[0].length + 2];

        int max = 0;
        for (int i = 0; i < rows; i++) {
            calHeight(matrix, i, heights);
            int area = maxArea(heights);
            max = Math.max(area, max);
        }
        return max;
    }

    /**
     * 单调栈法求最大面积
     *
     * @param heights
     * @return
     */
    private static int maxArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int i = 1;

        int max = 0;
        while (i < heights.length) {
            if (heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                while (heights[stack.peek()] == height) {
                    stack.pop();
                }
                int width = i - stack.peek() - 1;
                int area = width * height;
                max = Math.max(max, area);
            }
        }
        return max;
    }

    /**
     * 计算每一层的高度, 给高度数组左右两侧补0
     *
     * @param matrix
     * @param row
     * @return
     */
    private static void calHeight(char[][] matrix, int row, int[] height) {
        for (int j = 0; j < matrix[row].length; j++) {
            if (matrix[row][j] == '1') {
                int i = row - 1;
                while (i >= 0 && matrix[i][j] != '0') {
                    i--;
                }
                height[j + 1] = row - i;
            } else {
                height[j + 1] = 0;
            }
        }
    }


    public static int maximalRectangle2(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;

        int max = 0;

        int[] height = new int[cols + 2];
        for (int j = 1; j < height.length - 1; j++) {
            if (matrix[0][j - 1] == '1') height[j] = 1;
        }
        int area = calMaxArea(height);
        max = Math.max(max, area);

        for (int i = 1; i < rows; i++) {
            calHeight2(matrix, i, height);//第i行的高度
            area = calMaxArea(height);
            max = Math.max(max, area);

        }
        return max;
    }

    private static int calMaxArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = 0;
        int i = 1;
        while (i < height.length) {
            if (height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int j = stack.pop();
                while (height[stack.peek()] == height[j]) {
                    stack.pop();
                }
                max = Math.max((i - stack.peek() - 1) * height[j], max);
            }
        }
        return max;
    }

    private static void calHeight2(char[][] matrix, int row, int[] height) {
        for (int j = 1; j < height.length - 1; j++) {
            if (matrix[row][j - 1] == '1') {
                height[j] = height[j] > 0 ? height[j] + 1 : 1;
            } else{
                height[j] = 0;
            }
        }
    }

}
