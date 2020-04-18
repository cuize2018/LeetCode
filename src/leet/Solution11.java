package leet;

import java.util.Arrays;
import java.util.Map;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class Solution11 {
    public static void main(String[] args){
        int[] a = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea2(a));
    }

    /**
     * 自己的伪暴力解法
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int MaxArea = 0;

        for (int i = 0;i<height.length;i++){
            int h_old = 0;
            for (int j = height.length-1;j>i;j--){

                int r = j-i;
                int h = Math.min(height[i],height[j]);
                if (h > h_old) {
                    int tempArea = r * h;
                    h_old = h;
                    if (tempArea > MaxArea) {
                        MaxArea = tempArea;
                    }
                }
            }
        }
        return MaxArea;
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int MaxArea = 0;
        int left = 0;
        int right = height.length-1;

        while (right > left){
            int l = right - left;
            int h = Math.min(height[left],height[right]);

            int tempArea = l * h;
            if (tempArea > MaxArea) {
                MaxArea = tempArea;
            }
            if (height[left] >= height[right]){right--;}
            else {left++;}
        }
        return MaxArea;
    }


    public static int maxArea3(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length-1;

        while (left < right){
            int width = right - left;
            int heights = Math.min(height[left], height[right]);
            int area = width*heights;

            if (area > res) res = area;
            if (height[left] >= height[right]){
                right--;
            }
            else left++;
        }
        return res;
    }
}
