package leet;

import java.util.*;
import java.util.stream.Collectors;

public class Solution15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum4(nums));
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

    public static List<List<Integer>> threeSum4(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (v > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            int sum;
            while (left < right) {
                sum = v + nums[left] + nums[right];
                if (right < nums.length - 1 && nums[right] == nums[right + 1] || sum > 0) {
                    right--;
                } else if (left > i + 1 && nums[left] == nums[left - 1] || sum < 0) {
                    left++;
                } else {
                    List<Integer> one = new ArrayList<>(Arrays.asList(v, nums[left], nums[right]));
                    res.add(one);
                    left++;
                    right--;
                }
            }
        }
        return new ArrayList<>(res);
    }
}
