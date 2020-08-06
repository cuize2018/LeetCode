package leet;

import java.util.Arrays;

public class Solution387 {
    public static void main(String[] args) {

    }

    public static int firstUniqChar(String s) {
        int[] nums = new int[26];
        int[] idx = new int[26];
        Arrays.fill(idx, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            nums[c - 'a']++;
            if (idx[c - 'a'] == -1) {
                idx[c - 'a'] = i;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                min = Math.min(min, idx[i]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
