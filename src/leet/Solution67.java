package leet;

import javax.management.StringValueExp;

public class Solution67 {
    public static void main(String[] args) {
        String a = "111";
        String b = "111";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        if (a.charAt(0) == '0') return b;
        if (b.charAt(0) == '0') return a;

        StringBuilder res = new StringBuilder();
        int len = Math.min(a.length(), b.length());
        int add = 0;
        int p = a.length() - 1;
        int q = b.length() - 1;
        for (int i = 0; i < len; i++) {
            int x = a.charAt(p--) - '0';
            int y = b.charAt(q--) - '0';

            int val = x + y + add;
            if (val >= 2) {
                res.append(val - 2);
                add = 1;
            } else {
                res.append(val);
                add = 0;
            }
        }

        int startIdx = a.length() < b.length() ? q : p;
        String rest = a.length() < b.length() ? b : a;
        for (int i = startIdx; i >= 0; i--) {
            int x = rest.charAt(i) - '0';
            int val = x + add;
            if (val >= 2) {
                res.append(val - 2);
                add = 1;
            } else {
                res.append(val);
                add = 0;
            }
        }

        if (add == 1) {
            res.append('1');
        }
        return res.reverse().toString();
    }
}
