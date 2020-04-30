package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution202 {
    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
    }

    public static boolean isHappy(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        while (n != 1) {
            int val = 0;
            int nOld = n;

            if (map.containsKey(n))return false;

            while (n != 0) {
                int a = n % 10;
                val += a * a;
                n = n / 10;
            }
            n = val;
            map.put(nOld, val);
        }
        return true;
    }
}
