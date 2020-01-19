package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution424 {
    public static void main(String[] args) {
    }

    /**
     * 字符表来表示窗口，窗口的大小与字符的最大出现次数之间的差值表示可以替换的字符个数，
     * 当可以替换的字符个数大于k时，我们需要缩小窗口，也就是left右移，
     * 直到可以替换的字符个数等于k时，我们可以得到结果。
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        // 用字典保存字母出现的次数，需要替换的字符数目＝窗口字符数目－数量最多的字符数目
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxCount = 0;
        int result = 0;

        for (int i = 0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
            else {
                map.put(s.charAt(i), 1);
            }

            // 找到字符最多的出现次数
            maxCount = Math.max(maxCount, map.get(s.charAt(i)));
            // 如果替换的字符数目超过给定的k，则移动left
            while ((i - left + 1) - maxCount > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;

                // 需要更新最多个数
                // left是maxCount,此时更新maxCount;否则不更新maxCount
                if (!map.containsValue(maxCount)){
                    maxCount = maxCount - 1;
                }
            }
            result = Math.max(result, i-left+1);
        }
        return result;
    }

}
