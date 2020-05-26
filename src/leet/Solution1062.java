package leet;

import java.util.HashSet;
import java.util.Set;

public class Solution1062 {
    public static void main(String[] args) {
        String s = "aaaaa";
        System.out.println(longestRepeatingSubstring(s));
    }

    public static int longestRepeatingSubstring(String S) {
        return binarySearchLen(S);
    }

    private static int binarySearchLen(String s) {
        int left = 1;
        int right = s.length();
        int a = 26;
        int mod = (int) Math.pow(2, 24);
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        while (left != right) {
            int middle = (left + right) >>> 1;
            int idx = search(s, middle, a, mod, nums);
            if (idx != -1) {//有重复
                left = middle + 1;
            } else {//无重复
                right = middle;
            }
        }
        return left - 1;
    }

    private static int search(String s, int len, int a, int mod, int[] nums) {
        long hash = 0;
        Set<Long> seen = new HashSet<>();

        long al = 1;
        for (int i = 1; i <= len; i++) {
            al = (al * a) % mod;
        }

        for (int i = 0; i < len; i++) {
            hash += (nums[i] * Math.pow(a, len - i - 1))%mod;
        }
        seen.add(hash);

        for (int i = 1; i <= s.length() - len; i++) {
            hash = (hash * a - nums[i - 1] * al % mod + mod)%mod;
            hash += nums[i + len - 1] % mod;
            if (seen.contains(hash)) {
                return i;
            }
            seen.add(hash);
        }
        return -1;
    }
}
