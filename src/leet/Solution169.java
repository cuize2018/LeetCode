package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution169 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        int out = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) >= Math.ceil((double) n / 2D)) {
                out = num;
                break;
            }
        }
        return out;
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法,先假设第一个数过半数并设cnt=1；
     * 遍历后面的数如果相同则cnt+1，不同则减一，当cnt为0时则更换新的数字为候选数（成立前提：有出现次数大于n/2的数存在）
     * @param nums
     * @return
     */
    public static int majorityElement3(int[] nums) {
        int res = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                res = num;
                count++;
            } else {
                if (num != res) {
                    count--;
                } else {
                    count++;
                }
            }
        }
        return res;
    }
}
