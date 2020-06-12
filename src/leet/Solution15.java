package leet;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Solution15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum3(nums));
    }

    /**
     * 双指针，确定中心，双指针一左一右
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        HashSet<List<Integer>> out_set = new HashSet<>();

        Arrays.sort(nums);

        if (nums.length < 3) return out;
        if (nums[nums.length - 1] < 0) {
            return out;
        }
        if (nums[0] > 0) {
            return out;
        }
        if (nums[0] == 0 && nums[1] == 0 && nums[2] == 0) {
            List<Integer> temp_list = new ArrayList<>(Arrays.asList(0, 0, 0));
            out.add(temp_list);
            return out;
        }
        if (nums[nums.length - 1] == 0 && nums[nums.length - 2] == 0 && nums[nums.length - 3] == 0) {
            List<Integer> temp_list = new ArrayList<>(Arrays.asList(0, 0, 0));
            out.add(temp_list);
            return out;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            int left = 0;
            int right = nums.length - 1;

            int sum = -1000;
            do {
                sum = nums[left] + nums[right] + nums[i];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> temp_list = new ArrayList<>(Arrays.asList(nums[left], nums[i], nums[right]));
                    if (!out_set.contains(temp_list)) {
                        out.add(temp_list);
                        out_set.add(temp_list);
                    }
                    right--;
                    left++;
                }
            } while (right > i && left < i);
        }
        return out;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int m = i + 1, n = nums.length - 1;
            while (m < n) {
                if (n < nums.length - 1 && nums[n] == nums[n + 1] || nums[i] + nums[m] + nums[n] > 0) {
                    n--;
                } else if (m > i + 1 && nums[m] == nums[m - 1] || nums[i] + nums[m] + nums[n] < 0) {
                    m++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[m++]);
                    list.add(nums[n--]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right && left > i && right < nums.length) {
                int sum = nums[i] + nums[left] + nums[right];
                if (right < nums.length - 1 && nums[right] == nums[right + 1] || sum > 0) {
                    right--;
                } else if (left > i + 1 && nums[left] == nums[left - 1] || sum < 0) {
                    left++;
                } else {
                    List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    res.add(temp);
                }
            }
        }
        return res;
    }
}
