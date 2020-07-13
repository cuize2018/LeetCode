package leet;

import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class Solution16 {
    public static void main(String[] args) {
        int[] num = {0, 2, 1, -3};
        int target = 1;
        System.out.println(threeSumClosest2(num, target));
    }

    /**
     * 双指针
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int resSum = -1;
        int minDis = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = start + nums[left] + nums[right];
                int distance = Math.abs(sum - target);
                if (distance < minDis) {
                    minDis = distance;
                    resSum = sum;
                }

                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else return sum;
            }
        }
        return resSum;
    }

    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int res = -1;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = start + nums[left] + nums[right];
                int distance = Math.abs(sum - target);

                if (distance <= minLen) {
                    res = sum;
                    minLen = distance;

                    int a = Math.abs(nums[left + 1] + nums[right] + start - target);
                    int b = Math.abs(nums[left] + nums[right - 1] + start - target);
                    if (a < b) {
                        left++;
                    } else {
                        right--;
                    }
                } else {
                    if (sum - target > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }

    public static int threeSumClosest3(int[] nums, int target) {
        Arrays.sort(nums);
        int minDis = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int dis = Math.abs(sum - target);
                if (dis < minDis) {
                    minDis = dis;
                    res = sum;
                }

                if (right < nums.length - 1 && nums[right] == nums[right + 1] || sum > target) {
                    right--;
                } else if (left > i + 1 && nums[left] == nums[left - 1] || sum < target) {
                    left++;
                } else {
                    return sum;
                }
            }
        }
        return res;
    }
}
