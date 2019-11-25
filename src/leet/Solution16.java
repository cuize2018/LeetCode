package leet;

import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class Solution16 {
    public static void main(String[] args){
        int[] num = {1,1,1,0};
        System.out.println(threeSumClosest(num,100));
    }

    /**
     * 双指针
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int out_val = nums[0] + nums[1] + nums[2];
        int dis = Math.abs(target - out_val);

        for (int i = 0;i<nums.length;i++){
            int left = i+1;
            int right = nums.length-1;

            while (right > left){
                int val = nums[i] + nums[left] + nums[right];
                int tmp_dis = Math.abs(target-val);

                if (tmp_dis < dis){
                    dis = tmp_dis;
                    out_val = val;
                }
                if (val > target){
                    right--;
                }
                else if (val < target){
                    left++;
                }
                else {
                    return val;
                }
            }
        }

        return out_val;
    }
}
