package leet.interview;

import leet.Pair;
import org.omg.CORBA.MARSHAL;

import java.util.*;

public class Solution50 {
    public static void main(String[] args) {
        String s = "aadadaad";
        System.out.println(firstUniqChar2(s));
    }

    public static char firstUniqChar(String s) {
        if (s.length() == 0) return ' ';
        int[] counts = new int[256];
        char[] chars = s.toCharArray();

        for (char c : chars) counts[c]++;
        for (char c : chars) {
            if (counts[c] == 1) return c;
        }
        return ' ';
    }

    public static char firstUniqChar2(String s) {
        if (s.length() == 0) return ' ';
        int[] counts = new int[256];
        char[] chars = s.toCharArray();
        int i = 0;
        char firstChar = chars[i];

        for (char c : chars) {
            counts[c]++;
            if (counts[firstChar] > 1) {
                while (i < s.length() && (chars[i] == firstChar || counts[chars[i]] > 1)) {
                    i++;
                }
                if (i == s.length()) return ' ';
                firstChar = chars[i];
            }
        }
        return firstChar;
    }
}
