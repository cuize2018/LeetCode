package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution163 {
    public static void main(String[] args) {
//        int[] nums = {0, 1, 3, 50, 75};
//        int lower = 1;
//        int upper = 99;

        int[] nums = {-2147483648,-2147483648,0,2147483647,2147483647};
        int lower = -2147483648;
        int upper = 2147483647;


        System.out.println(findMissingRanges(nums, lower, upper));
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long l = lower, u = upper;
        for (int num : nums) {
            if (num - l == 1) {
                res.add(String.valueOf(l));
            } else if (num - l > 1) {
                res.add(l + "->" + (num - 1));
            }
            l = (long) num + 1;
        }

        if (l == u) res.add(String.valueOf(l));
        else if (l < u) res.add(l + "->" + u);
        return res;

    }


    public static List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (lower == upper){
            if (nums.length == 0){
                res.add(String.valueOf(lower));
            }
            return res;
        }

        int i = 0;
        long pre = lower - 1L;

        while (i < nums.length) {
            if (nums[i] != pre && nums[i] != pre + 1L) {
                long start = pre + 1L;
                long end = nums[i] - 1L;
                if (start != end) {
                    res.add(start + "->" + end);
                } else {
                    res.add(String.valueOf(start));
                }
            }
            pre = nums[i];
            i++;
        }
        if (pre < upper) {
            long start = pre + 1L;
            if (start != upper) {
                res.add(start + "->" + upper);
            } else {
                res.add(String.valueOf(start));
            }
        }
        return res;
    }
}
