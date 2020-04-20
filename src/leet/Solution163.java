package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution163 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 1;
        int upper = 99;
        System.out.println(findMissingRanges2(nums, lower, upper));
    }

    public static List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long l = lower, u = upper;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - l == 1) {
                res.add(String.valueOf(l));
            } else if (nums[i] - l > 1) {
                res.add(l + "->" + (nums[i] - 1));
            }
            l = (long) nums[i] + 1;
        }

        if (l == u) res.add(String.valueOf(l));
        else if (l < u) res.add(l + "->" + u);
        return res;

    }
}
