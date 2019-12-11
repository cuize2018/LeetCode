package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution395 {
    public static void main(String[] args) {
        String s = "caaaacac";
        int k = 4;
        String b = "zzzzzzzzzzaaaaaaaaabbbbbbbbhbhbhbhbhbhbhicbcbcibcbccccccccccbbbbbbbbaaaaaaaaafffaahhhhhiaahiiiiiiiiifeeeeeeeeee";
        int m = 10;
        //System.out.println(longestSubstring(s,k));
        System.out.println(longestSubstring(b,m));
    }

    public static int longestSubstring(String s, int k) {
        if (s.length() == 1){
            if (k <= 1)return 1;
            else return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = s.length()-1;
        //统计字符出现字数
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }

        while (left < right && !isSatisfied(map, k)){
            if (map.get(s.charAt(left)) < k){
                MapDecrease(map, s, left,left);
                left++;
            }
            else if (map.get(s.charAt(right)) < k){
                MapDecrease(map, s, right, right);
                right--;
            }
            else {
                int mov_r = right;
                int mov_l = left;
                //从右向左找出第一个不满足条件的字符位置
                while (map.get(s.charAt(mov_r)) >= k){
                    mov_r--;
                }
                //从左向右找出第一个不满足条件的字符位置
                while (map.get(s.charAt(mov_l)) >= k){
                    mov_l++;
                }
                //计算两种情况剩余字串长度大小
                int left_len = mov_r - left;
                int right_len = right - mov_l;

                //保留剩余长度交大的子串
                if (left_len > right_len){
                    MapDecrease(map,s,mov_r,right);
                    right = mov_r-1;
                }
                else {
                    MapDecrease(map,s,left,mov_l);
                    left = mov_l+1;
                }
            }
        }

        if (left != right) {
            return right - left + 1;
        }
        else {
            return 0;
        }
    }
    private static boolean isSatisfied(Map<Character, Integer> map, int k){
        for (Character key: map.keySet()){
            if (map.get(key) < k){
                return false;
            }
        }
        return true;
    }

    private static void MapDecrease(Map<Character, Integer> map, String s, int start, int end){
        for (int j = start;j <= end;j++){
            if (map.get(s.charAt(j)) != 1){
                map.put(s.charAt(j), map.get(s.charAt(j))-1);
            }
            else {
                map.remove(s.charAt(j));
            }
        }
    }


}
