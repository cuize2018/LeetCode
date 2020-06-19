package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution125 {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 0)return true;

//        s = s.toLowerCase();
//        统一转成大写：ch & 0b11011111 简写：ch & 0xDF
//        统一转成小写：ch | 0b00100000 简写：ch | 0x20
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c);
        }

        List<Character> characters = list.stream().map((x)->((char)(x | 0x20)))
                .filter((x) -> (x >= 'a' && x <= 'z' || Character.isDigit(x)))
                .collect(Collectors.toList());

        List<Character> re = new ArrayList<>(characters);
        Collections.reverse(re);

        return characters.equals(re);
    }
}
