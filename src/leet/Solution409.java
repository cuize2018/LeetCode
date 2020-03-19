package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution409 {
    public static void main(String[] args) {
        String s = "ababababa";
        System.out.println(longestPalindrome(s));
    }

    public static int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        if (map.keySet().size() == 1){
            return s.length();
        }

        int out = 0;
        int num1 = 0;
        boolean getNum = true;
        while (getNum) {
            getNum = false;
            for (Character character : map.keySet()) {
                if (map.get(character) != 0) {
                    int count = map.get(character);
                    getNum = true;
                    if (count >= 2 && count % 2 == 0) {
                        out += count;
                        map.put(character, 0);
                    } else if (count != 1 && count % 2 != 0) {
                        out += count - 1;
                        map.put(character, 1);
                    } else {
                        num1++;
                        map.put(character, 0);
                    }
                }
            }
        }
        if (num1 != 0)return out+1;
        else return out;
    }

    //字符有偶数个就全算上，奇数个就减1全算上，最终若有数量为1的字符则加1
    //A-Z的ascii和a-z的ascii不连续,所以需要补齐中间的6个字符。
    public static int longestPalindrome2(String s) {
        int[] map = new int[26*2+6];
        for (char c : s.toCharArray()) {
            map[c-'A']++;
        }

        int out = 0;
        int num1 = 0;
        boolean getNum = true;
        while (getNum) {
            getNum = false;
            for (int i = 0;i<map.length;i++) {
                if (map[i] != 0) {
                    getNum = true;
                    int count = map[i];
                    if (count >= 2 && count % 2 == 0) {
                        out += count;
                        map[i] = 0;
                    } else if (count != 1 && count % 2 != 0) {
                        out += count - 1;
                        map[i] = 1;
                    } else {
                        num1++;
                        map[i] = 0;
                    }
                }
            }
        }
        if (num1 != 0)return out+1;
        else return out;
    }
}
