package leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution1371 {
    public static void main(String[] args) {
        String s = "a";
        System.out.println(findTheLongestSubstring(s));
    }

    /**
     * 状态压缩＋哈希表。类似的题出现好几次了。
     * 如1124。 状态压缩后，哈希表的用处是求最长的连续子串，满足子数组的和为k。 此题k为0， 1124题k为1.
     * <p>
     * 遇到奇偶个数校验，想到XOR
     * 遇到有限的参数（小于20个）表状态， 想到状态压缩 （bitmask）
     * 遇到求最长的连续子串使得和为k（maximum continuous subarray(substring) with sum equal to k）想到 前缀和
     * 加哈希表记录第一次出现某一状态的位置。
     *
     * @param s
     * @return
     */
    public static int findTheLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        String vowels = "aeiou";
        int state = 0;//(00000)
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < vowels.length(); j++) {
                if (c == vowels.charAt(j)) {
                    state = state ^ (1 << (vowels.length() - j - 1));//状态转移
                    break;
                }
            }
            if (map.containsKey(state)) {
                res = Math.max(res, i - map.get(state));
            }
            map.putIfAbsent(state, i);
        }
        return res;
    }
}
