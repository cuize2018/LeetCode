package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution340 {
    public static void main(String[] args) {
        String s = "aba";
        int k = 1;
        System.out.println(lengthOfLongestSubstringKDistinct(s, k));
    }

    /**
     * 滑动窗口法
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() == 0) return 0;
        if (k == 0) return 0;

        //滑动窗口内字符的数量信息
        Map<Character, Integer> windowInfo = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;

        while (right < s.length()) {
            //如果窗口内不同字符数量 <= k时
            if (windowInfo.keySet().size() <= k) {
                char c = s.charAt(right);
                windowInfo.put(c, windowInfo.getOrDefault(c, 0) + 1);
                if (windowInfo.keySet().size() <= k) {
                    //更新结果
                    res = Math.max(res, right - left + 1);
                    right++;
                }
            } else {
                //当窗口内不同字符数量大于k时，移动左指针，直到小于等于k为止
                while (windowInfo.keySet().size() > k) {
                    char excludeChar = s.charAt(left);
                    if (windowInfo.get(excludeChar) == 1) windowInfo.remove(excludeChar);
                    else {
                        windowInfo.put(excludeChar, windowInfo.get(excludeChar) - 1);
                    }
                    left++;
                }
                right++;
            }
        }
        return res;
    }
}
