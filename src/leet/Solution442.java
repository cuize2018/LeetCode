package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution442 {
    public static void main(String[] args) {
        int[] a = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(a));
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> out = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = 0;i<nums.length-1;i++){
            if (nums[i] == nums[i+1]){
                out.add(nums[i]);
                i++;
            }
        }
        return out;
    }

    /**
     * 利用题目中所给信息 1 ≤ a[i] ≤ n ,将出现过的数字作为数组的index（访问元素时需要减一）
     * 如果出现一次的，将该index下的数取相反数，记录此时的状态，
     * 如果值为index的数字再出现一次（此时index索引的值为负数），那么这个数字就出现了两次
     *
     * 比如 数组 [2,2,1] , 第一次更新后 index = 2 索引的第元素取相反数 [2,-2,1],
     * 第二次更新 index = 2 , 此时数组元素已为负，所以2就是其中的一个结果
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> out = new ArrayList<>();

        for (int i = 0;i<nums.length;i++){
            int val = Math.abs(nums[i]);
            if (nums[val-1] > 0){
                nums[val - 1] = -nums[val - 1];
            }
            else {
                out.add(val);
            }
        }
        return out;
    }
}
