package leet;

import java.util.*;

public class Solution187 {

    public static void main(String[] args) {
        String s = "AAAAAAAAAAAA";
        System.out.println(findRepeatedDnaSequences2(s));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) res.add(entry.getKey());
        }
        return res;
    }

    /**
     * Rabin-Karp算法
     *
     * @param s
     * @return
     */
    public static List<String> findRepeatedDnaSequences2(String s) {
        if (s.length() < 10) return new ArrayList<>();

        Set<String> res = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        int hash = 0;
        int a = 4;
        int l = 10;
        int al = (int) Math.pow(a, l);

        //将字符串编码为整数数组
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = map.get(s.charAt(i));
        }

        //初始化子串hash值
        for (int i = 0; i < l; i++) {
            hash += nums[i] * Math.pow(a, l - i - 1);
        }
        seen.add(hash);

        for (int start = 1; start <= s.length() - l; start++) {
            //更新字符串hash值
            hash = (hash * a - nums[start - 1] * al) + nums[start + l - 1];
            if (seen.contains(hash)) {
                res.add(s.substring(start, start + l));
            }
            seen.add(hash);
        }
        return new ArrayList<>(res);
    }


}
