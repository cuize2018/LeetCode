package leet;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution1044 {
    public static void main(String[] args) {
        String s = "banana";
        System.out.println(longestDupSubstring(s));
    }

    public static String longestDupSubstring(String S) {
        int n = S.length();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = S.charAt(i) - 'a';
        }

        int base = 26;
        long mod_value = (long) Math.pow(2, 32);

        int left = 1;
        int right = n;

        while (left != right) {
            int middle = (left + right) >>> 1;
            int idx = search(middle, base, mod_value, nums);
            if (idx != -1) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        int start = search(left-1, base, mod_value, nums);
        if (start == -1){
            return "";
        }
        else {
            return S.substring(start, start+left-1);
        }
    }

    private static int search(int window, int base, long mod_value, int[] nums) {
        long hash = 0;
        for (int i = 0; i < window; i++) {
            hash = (hash * base + nums[i]) % mod_value;
        }

        Set<Long> set = new HashSet<>();
        set.add(hash);

        long baseMiddle = 1;
        for (int i = 1; i <= window; i++) {
            baseMiddle = (baseMiddle * base) % mod_value;
        }

        for (int start = 1; start <= nums.length - window; start++) {
            hash = (hash * base - nums[start - 1] * baseMiddle % mod_value + mod_value) % mod_value;
            hash = (hash + nums[start + window - 1]) % mod_value;
            if (set.contains(hash)) {
                return start;
            }
            set.add(hash);
        }
        return -1;
    }


}
