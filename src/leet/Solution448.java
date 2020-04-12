package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution448 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));
    }

    /**
     * # 将所有正数作为数组下标，置对应数组值为负值。那么，仍为正数的位置即为（未出现过）消失的数字。
     *   # 举个例子：
     *   # 原始数组：[4,3,2,7,8,2,3,1]
     *   # 重置后为：[-4,-3,-2,-7,8,2,-3,-1]
     *   # 结论：[8,2] 分别对应的index为[5,6]（消失的数字）
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums.length == 0)return new ArrayList<>();
        List<Integer> out = new ArrayList<>();

        for (int i = 0; i < nums.length;i++) {
            int idx = Math.abs(nums[i]) -1;
             //始终保持nums[index]为负数
            if (nums[idx] > 0) nums[idx] = -nums[idx];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)out.add(i+1);
        }
        return out;
    }
}
