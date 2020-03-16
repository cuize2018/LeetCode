package leet.interview;

import java.util.Arrays;
import java.util.Collections;

public class Solution01_06 {
    public static void main(String[] args) {
        String s = "abbccd";
        System.out.println(compressString(s));
    }

    public static String compressString(String S) {
        int origin_len = S.length();
        if (origin_len <= 1){
            return S;
        }

        char[] c = S.toCharArray();

        StringBuilder final_str = new StringBuilder();

        char last = c[0];
        int count = 1;
        for (int i = 1; i < c.length; i++) {
            if (c[i] != last){
                final_str.append(last);
                final_str.append(count);
                if (final_str.length() >= origin_len){
                    return S;
                }

                count = 1;
                last = c[i];
            }
            else {
                count++;
            }
        }
        final_str.append(last);
        final_str.append(count);
        if (final_str.length() >= origin_len){
            return S;
        }

        return final_str.toString();
    }
}
